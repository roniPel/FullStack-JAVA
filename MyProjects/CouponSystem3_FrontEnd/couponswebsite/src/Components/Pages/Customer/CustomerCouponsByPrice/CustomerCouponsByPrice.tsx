import { useNavigate } from "react-router-dom";
import "./CustomerCouponsByPrice.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import { getAllCustomerCouponsAction } from "../../../../Redux/customerReducer";
import { Button, TextField, Typography } from "@mui/material";
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";
import FilterAltIcon from '@mui/icons-material/FilterAlt';
import axiosJWT from "../../../../Utilities/axiosJWT";

export function CustomerCouponsByPrice(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    const [chosenPrice, setPrice] = useState<number>();
    const [filteredCouponList, setFilteredList] = useState<Coupon[]>([]);

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Customer){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        checkData();
        getCustCoupons();
    },[]);

    function getCustCoupons(){
        // check if requested customer coupons exist in redux
        if(couponStore.getState().customer.customerCoupons.length !== 0){
            setList(couponStore.getState().customer.customerCoupons);
        } else {    // If redux is empty
            let recivedList:Coupon[] = [];
            // Get coupons from DB
            axiosJWT.get("http://localhost:8080/Customer/GetCustomerCoupons")
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
            couponStore.dispatch(getAllCustomerCouponsAction(recivedList));
            setList(couponStore.getState().customer.customerCoupons);
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
            <div className="CustomerCouponsByPrice">
                <div className="Grid-Child">
                    <div className="Insert Price Box" style = {{width: "20%"}}>
                        <TextField type="number" label="Insert Max Price..." variant="outlined" fullWidth
                        onChange={handleChange}/><br/><br/>
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
