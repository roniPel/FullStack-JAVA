import { useNavigate, useParams } from "react-router-dom";
import "./PurchaseCoupon.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { useForm } from "react-hook-form";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import axios from "axios";
import { getOneCouponAction } from "../../../../Redux/guestReducer";

export function PurchaseCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const params = useParams();

    const { register, handleSubmit, formState: {errors} } = useForm<Coupon>();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Customer){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }   
        getCoupon();
        
    },[]);

    function getCoupon(){
        // check if we have coupon in redux
        if(couponStore.getState().customer.coupon.id == parseInt(params.couponID as string)){
            //console.log("Get from Store");
            setCoupon(couponStore.getState().customer.coupon);
        } else {
            //console.log("Get from Backend");
            // get coupon data from backend
            axios.get(`http://localhost:8080/Customer/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
            couponStore.dispatch(getOneCouponAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
    }
    
    return (
        <div className="PurchaseCoupon">
			
        </div>
    );
}
