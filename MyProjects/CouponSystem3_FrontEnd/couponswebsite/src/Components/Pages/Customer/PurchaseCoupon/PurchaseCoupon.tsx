import { useNavigate, useParams } from "react-router-dom";
import "./PurchaseCoupon.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import { SubmitHandler, useForm } from "react-hook-form";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import axios from "axios";
import { getOneCouponAction } from "../../../../Redux/guestReducer";
import { Button, ButtonGroup, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import ShopIcon from '@mui/icons-material/Shop';
import { getOneCouponViaCustomerAction, purchaseCouponAction } from "../../../../Redux/customerReducer";
import axiosJWT from "../../../../Utilities/axiosJWT";

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
        let reduxCoupon:Coupon = couponStore.getState().customer.coupon;
        if((reduxCoupon!==undefined) && reduxCoupon.id == parseInt(params.couponID as string)){
            //console.log("Get from Store");
            setCoupon(couponStore.getState().customer.coupon);
        } else {
            //console.log("Get from Backend");
            // get coupon data from backend
            axiosJWT.get(`http://localhost:8080/Customer/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
            couponStore.dispatch(getOneCouponViaCustomerAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
    }

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        //console.log(coupon);
        axiosJWT.post(`http://localhost:8080/Customer/PurchaseCoupon`,coupon)
        .then((res)=> {
            couponStore.dispatch(purchaseCouponAction(coupon as Coupon));
            notify.success("The coupon was purchased successfully.");
            navigate("/customerHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem purchasing the coupon.");
        })
    }
    
    return (
        <div>
            <Typography variant="h4" className="HeadLine">Purchase Coupon</Typography>
            <hr/>
            <div className="PurchaseCoupon Box" style={{ width: "40%" }}>
            <div className="Grid-Parent">
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <div className="Grid-Child">
                            <img src={coupon?.image} />
                        </div>
                        <div className="Grid-Child">
                            <Typography gutterBottom variant="h5" component="div">{coupon?.title}</Typography>
                            <Typography variant="h6" color="text.secondary">{coupon?.description}</Typography >
                            <Typography variant="body2" color="text.secondary">Category: {coupon?.category}</Typography ><br/>
                            <Typography variant="body1" color="text.secondary">
                            Valid Until: {coupon?.end_date}<br/>
                            Only {coupon?.price} (NIS)<br/>
                            </Typography><hr/><br/>
                        </div>
                        <div className="Grid-Child">
                            <ButtonGroup variant="contained" fullWidth>
                                <Button type="submit" variant="contained" color="success" startIcon={<ShopIcon/>} >Purchase</Button>
                                <Button variant="contained" color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/companyHome") }}>Cancel</Button>
                            </ButtonGroup>
                        </div>
                        <br/>
                    </form>
                </div>
            </div>
        </div>
    );
}
