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
import { Button, ButtonGroup, Typography } from "@mui/material";
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";
import { SubmitHandler, useForm } from "react-hook-form";

export function CompanyCouponsByCategory(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    const [selectedCategory, setCategory] = useState<Category>();
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
        if(couponStore.getState().company.companyCoupons !== null){
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

    const uniqueCoupCategoryList = couponList.
        filter((obj, index, self) => index === 
        self.findIndex((o) => o.category === obj.category)
    );

    function filterCategory() {
        let myList:Coupon[] = [];
        couponList.forEach((coup)=>{
            if(coup.category === selectedCategory){
                myList.push(coup);
            }
        })
        setFilteredList(myList);
    }

    return (
        <div className="CompanyCouponsByCategory">
			<Typography variant="h4" className="HeadLine">My Coupons by Category</Typography>
            <hr />
            <div className="CustomerCouponsByCategory">
                <div className="Select Category Box">
                    <label>Select Category:</label><br />
                    <select >
                        {uniqueCoupCategoryList.map((item)=><option key={item.id} value={item.category}>{item.category as string}</option>)}v
                    </select><br /><br/>
                    <button type="button" color="primary" onClick={()=>{filterCategory()}} >Filter By Category</button>
                </div>
                <div className="Coupon List Result">
                    {filteredCouponList.map((item)=><SingleCoupon key={item.id} coupon={item}/>)}
                </div>
            </div>
        </div>
    );
}
