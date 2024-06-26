import { useEffect, useState } from "react";
import "./AllCoupons.css";
import { Coupon } from "../../../../Models/Coupon";
import { useNavigate } from "react-router-dom";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import notify from "../../../../Utilities/notify";
import { Typography } from "@mui/material";
import { SingleCoupon } from "../SingleCoupon/SingleCoupon";

export function AllCoupons(): JSX.Element {
    const [couponList, setList] = useState<Coupon[]>([]);
    const navigate = useNavigate();

    //get coupon list from backend
    useEffect(()=>{
        let recivedList:Coupon[] = [];
        // Check that our redux data is updated
        checkData();
        // Get all coupons from DB
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
            })
            .catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
                navigate("/");
            });
    },[]);
    return (
        <div className="AllCoupons">
			<br/><Typography variant="h4" className="HeadLine">Available Coupons</Typography><hr/><br/>
            {couponList.map(item=><SingleCoupon key={item.id} coupon={item}/>)}
        </div>
    );
}
