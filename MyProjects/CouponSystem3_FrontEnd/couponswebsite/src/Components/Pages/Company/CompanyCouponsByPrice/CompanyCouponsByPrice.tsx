import { useNavigate } from "react-router-dom";
import "./CompanyCouponsByPrice.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import { getAllCompanyCouponsAction } from "../../../../Redux/companyReducer";
import { Button, TextField, Typography } from "@mui/material";
import FilterAltIcon from '@mui/icons-material/FilterAlt';
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";
import axiosJWT from "../../../../Utilities/axiosJWT";

export function CompanyCouponsByPrice(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    const [chosenPrice, setPrice] = useState<number>();
    const [filteredCouponList, setFilteredList] = useState<Coupon[]>([]);

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        checkData();
        getCompCoupons();
    },[]);

    function getCompCoupons() {
        // check if requested company coupons exist in redux
        if(couponStore.getState().company.companyCoupons.length !== 0){
            setList(couponStore.getState().company.companyCoupons);
        } else {    // If redux is empty
            let recivedList:Coupon[] = [];
            // Get coupons from DB
            axiosJWT.get("http://localhost:8080/Company/GetCompanyCoupons")
            .then(result=>{
                //console.log("Axios result: "+result)
            for (let index=0;index<result.data.length;index++){
                recivedList.push(new Coupon(
                    result.data[index].id,
                    result.data[index].companyId,
                    result.data[index].category,
                    result.data[index].title,
                    result.data[index].description,
                    result.data[index].start_date,
                    result.data[index].end_date,
                    result.data[index].amount,
                    result.data[index].price,
                    result.data[index].image
                ));
            };
            setList(recivedList);                   
            couponStore.dispatch(getAllCompanyCouponsAction(recivedList));
            setList(couponStore.getState().company.companyCoupons);
            })
            .catch(err=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");              
            });
        }
    }

    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        setPrice(parseInt(e.target.value));
      }
    
    function filterByPrice() {
        // Filter by price
        let myList:Coupon[] = [];
        couponList.forEach((coup)=>{
            if(coup.price <= (chosenPrice as number)){
                myList.push(coup);
            }
        })
        setFilteredList(myList);
    }

    return (
        <div>
            <Typography variant="h4" className="HeadLine">My Coupons by Max Price</Typography>
            <hr />
            <div className="CompanyCouponsByPrice">
                <div className="Grid-Child">
                    <div className="Insert Price Box" style = {{width: "20%"}}>
                        <TextField type="number" label="Insert Max Price..." variant="outlined" fullWidth
                        onChange={handleChange}/>
                        <br/><br/>
                        <Button type="button" variant="contained" color="primary" startIcon={<FilterAltIcon/>} onClick={filterByPrice} >Filter</Button>
                    </div>
                    <br/><br/>
                    <div className="Coupon List Result">
                        {filteredCouponList.map((item)=><SingleCoupon key={item.id} coupon={item}/>)}
                    </div>
                </div>
            </div>
        </div>
    );
}
