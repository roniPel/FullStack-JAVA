import { useNavigate } from "react-router-dom";
import "./CompanyCouponsByCategory.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { Category } from "../../../../Models/Category";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import { getAllCompanyCouponsAction } from "../../../../Redux/companyReducer";
import { Button, InputLabel, MenuItem, Select, SelectChangeEvent, Typography } from "@mui/material";
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";
import FilterAltIcon from '@mui/icons-material/FilterAlt';

export function CompanyCouponsByCategory(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    const [selectedCategory, setCategory] = useState<Category>();
    const [filteredCouponList, setFilteredList] = useState<Coupon[]>([]);

    const uniqueCoupCategoryList = couponList.
        filter((obj, index, self) => index === 
        self.findIndex((o) => o.category === obj.category)
    );

    const handleCatChange = (e: SelectChangeEvent<HTMLSelectElement>) => { 
        setCategory(e.target.value as Category);
        //console.log(e.target.value);
      };
    
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
            axios.get("http://localhost:8080/Company/GetCompanyCoupons")
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


    function filterByCategory() {
        // Filter by category
        let myList:Coupon[] = [];
        couponList.forEach((coup)=>{
            if(coup.category === selectedCategory){
                myList.push(coup);
            }
        })
        setFilteredList(myList);
    }

    return (
        <div>
			<Typography variant="h4" className="HeadLine">My Coupons by Category</Typography>
            <hr />
            <div className="CustomerCouponsByCategory">
                <div className="Grid-Child">
                    <div className="Select Category Box" style={{ width: "20%" }}>
                        <InputLabel id="Category-label">Select Category</InputLabel>
                        <Select labelId="Category-label" id="Category-label" label="Category" onChange={handleCatChange} fullWidth >
                            {uniqueCoupCategoryList.map((item)=><MenuItem key={item.id} value={item.category}>{item.category as string}</MenuItem>)}
                        </Select>
                        <br/><br/>
                        <Button type="submit" variant="contained" color="primary" startIcon={<FilterAltIcon/>} onClick={filterByCategory}>Filter</Button>
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
