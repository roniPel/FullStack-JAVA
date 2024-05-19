import { Button, Typography } from "@mui/material";
import "./Welcome.css";
import { NavLink, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Coupon } from "../../../Models/Coupon";
import axios from "axios";
import { SingleCoupon } from "../SingleCoupon/SingleCoupon";

export function Welcome(): JSX.Element {
    
    //react hooks => useState , useEffect
    const [couponList, setList] = useState<Coupon[]>([]);

    //get song list from backend
    useEffect(()=>{
        let recivedList:Coupon[] = [];
        axios.get("http://localhost:8080/Guest/GetAllCoupons").then(result=>{
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
        });
    },[])

    return (
        <div className="Welcome">
            <br/><Typography variant="h4" className="HeadLine">Available Coupons</Typography><hr/><br/>
            {/* {songList.map(item=><div className="Box">{item.id}<br/>{item.name}<br/>{item.desc}<br/><img src={item.image} width={100}/></div>)} */}
            {couponList.map(item=><SingleCoupon key={item.id} coupon={item}/>)}
        </div>
    );

}
