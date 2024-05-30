import { Button, Typography } from "@mui/material";
import "./Welcome.css";
import { NavLink, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Coupon } from "../../../Models/Coupon";
import axios from "axios";
import { SingleCoupon } from "../SingleCoupon/SingleCoupon";
import { checkData } from "../../../Utilities/checkData";
import { couponStore } from "../../../Redux/store";

export function Welcome(): JSX.Element {
    
    //react hooks => useState , useEffect
    const [couponList, setList] = useState<Coupon[]>([]);
    const navigate = useNavigate();

    //get song list from backend
    useEffect(()=>{
        let recivedList:Coupon[] = [];

         //check if we have any coupons on our list, 0 length indicates that we don't have any song
         if (couponStore.getState().auth.token.length<10){
            navigate("/login");
        }
        checkData();

        //if(couponStore.getState().coupons.allCoupons.length == 0)
        if (true) {
            axios.get("http://localhost:8080/Guest/GetAllCoupons")
            .then(result=>{
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
                // couponStore.dispatch(getAllCouponsAction(recivedList));
                // setList(couponStore.getState().coupons.allCoupons);
                })
                .catch(err=>{
                    navigate("/login")
                });
        } else {
            //setList(couponStore.getState().coupons.allCouponss);
        }
        
    },[]);

    return (
        <div className="Welcome">
            <br/><Typography variant="h4" className="HeadLine">Available Coupons</Typography><hr/><br/>
            {couponList.map(item=><SingleCoupon key={item.id} coupon={item}/>)}
        </div>
    );

}
