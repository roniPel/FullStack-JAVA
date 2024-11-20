import { useNavigate, useParams } from "react-router-dom";
import "./DeleteCoupon.css";
import { useEffect, useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { Coupon } from "../../../../Models/Coupon";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import axios from "axios";
import { getOneCouponAction } from "../../../../Redux/guestReducer";
import { deleteCouponAction, getOneCouponViaCompanyAction } from "../../../../Redux/companyReducer";
import { Button, ButtonGroup, Typography } from "@mui/material";
import  DeleteIcon  from "@mui/icons-material/Delete";
import CancelIcon from '@mui/icons-material/Cancel';
import axiosJWT from "../../../../Utilities/axiosJWT";

export function DeleteCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const params = useParams();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        
        getCoupon();
        
    },[]);

    function getCoupon(){
        // check if we have data in redux
        if(couponStore.getState().company.coupon.id === parseInt(params.couponID as string)){
            //console.log("Get from Store");
            setCoupon(couponStore.getState().company.coupon);
            //console.log("The Local saved coupon is: "+coupon);
        } else {
        // get data from backend
            axiosJWT.get(`http://localhost:8080/Company/GetOneCoupon/${params.couponID}`).then(res=>{
                setCoupon(res.data);
                couponStore.dispatch(getOneCouponViaCompanyAction(res.data));
                }).catch((err)=>{
                    console.log(err);
                    notify.error("There was a problem getting the requested data.");
                });
            }
    }

    function deleteCoupon() {
        //console.log(data);
        axiosJWT.delete(`http://localhost:8080/Company/DeleteCoupon/${params.couponID}`)
        .then((res)=> {
            couponStore.dispatch(deleteCouponAction(parseInt(params.couponID as string)));
            notify.success("The coupon was deleted successfully.");
            navigate("/companyHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem deleting the coupon.");
        })
    }
    
    return (
        <div>
            <Typography variant="h4" className="HeadLine">Delete Coupon</Typography>
            <hr/>
            <div className="DeleteCoupon Box" style={{ width: "40%" }}>
                <div className="Grid-Parent">
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
                            <Button type="button" variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={deleteCoupon} > Delete</Button>
                            <Button variant="contained" color="primary" startIcon={<CancelIcon/>} onClick={() => { navigate("/companyHome") }}>Cancel</Button>
                        </ButtonGroup>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    );
}
