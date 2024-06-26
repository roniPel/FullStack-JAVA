import { useNavigate } from "react-router-dom";
import "./CompanyCoupons.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import axios from "axios";
import { getAllCompanyCouponsAction } from "../../../../Redux/companyReducer";
import { SingleCoupon } from "../../General/SingleCoupon/SingleCoupon";
import { Typography } from "@mui/material";
import axiosJWT from "../../../../Utilities/axiosJWT";

export function CompanyCoupons(): JSX.Element {
    const navigate = useNavigate();
    const [couponList, setList] = useState<Coupon[]>([]);
    
    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        getCoupons();

    },[]);

    function getCoupons() {
        let recivedList:Coupon[] = [];
        //check if we have any coupons on our list - 0 length indicates that we don't have any coupons
        if (couponStore.getState().company.companyCoupons.length == 0) {
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
        } else {
            setList(couponStore.getState().company.companyCoupons);
        }
    }

    
    return (
        <div className="CompanyCoupons">
			<br/><Typography variant="h4" className="HeadLine">My Coupons</Typography><hr/><br/>
            {couponList.map(item=><SingleCoupon key={item.id} coupon={item}/>)}
        </div>
    );
}
