import { Button, ButtonGroup, Typography } from "@mui/material";
import "./UpdateCoupon.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import { useNavigate, useParams } from "react-router-dom";
import { Coupon } from "../../../../Models/Coupon";
import { SubmitHandler, useForm } from "react-hook-form";
import notify from "../../../../Utilities/notify";
import axios from "axios";
import { getOneCouponAction } from "../../../../Redux/guestReducer";
import { updateCouponAction } from "../../../../Redux/companyReducer";
import { Category } from "../../../../Models/Category";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';

export function UpdateCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const params = useParams();

    const { register, handleSubmit, formState: {errors} } = useForm<Coupon>();
    
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
        if(couponStore.getState().company.coupon.id == parseInt(params.couponID as string)){
            //console.log("Get from Store");
            setCoupon(couponStore.getState().company.coupon);
        } else {
        // console.log("Get from Backend")
        // get data from backend
        axios.get(`http://localhost:8080/Company/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
            couponStore.dispatch(getOneCouponAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
    }

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        data.id = parseInt(params.couponID as string);
        console.log(data);
        axios.put(`http://localhost:8080/Company/UpdateCoupon/${[params.couponID]}`,data)
        .then((res)=> {
            couponStore.dispatch(updateCouponAction(data));
            notify.success("The coupon was updated successfully.");
            navigate("/companyHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem updating the coupon.");
        })
    }

    return (
        <div>
            <Typography variant="h4" className="HeadLine">Update Coupon</Typography>
            <hr/>
            <div className="UpdateCoupon Box" style={{ width: "40%" }}>
            <form onSubmit={handleSubmit(onSubmit)}>
                    <input type="text" placeholder="Title" defaultValue={coupon?.title} {...register("title",{required:true})} />
                    {errors.title?.type == "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                    <br /><br />
                    <input type="text" placeholder="Description" defaultValue={coupon?.description} {...register("description")} />
                    <br /><br />
                    <label>Start Date: </label>
                    <input type="date" placeholder="Start Date" defaultValue={coupon?.start_date} {...register("start_date", { required: true })} />
                    <br /><br />
                    <label>End Date: </label>
                    <input type="date" placeholder="End Date" defaultValue={coupon?.end_date} {...register("end_date", { required: true })} />
                    <br /><br />
                    <input type="number" placeholder="Amount" defaultValue={coupon?.amount} {...register("amount", { required: true })} />
                    <br /><br />
                    <input type="number" placeholder="Price" defaultValue={coupon?.price} {...register("price", { required: true })} />
                    <br /><br />
                    <label>Select Category:</label><br />
                    <select defaultValue={coupon?.category} {...register("category")} >
                        <option key = {Category.Automotive} value = {Category.Automotive}>{Category.Automotive}</option>
                        <option key = {Category.BabyToddler} value = {Category.BabyToddler}>{Category.BabyToddler}</option>
                    </select><br /><br/>
                    <input type="text" placeholder="Image" defaultValue={coupon?.image} {...register("image")} />
                    <br/><br />
                    <hr />
                    <br/>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" variant="contained" color="primary" startIcon={<UpdateIcon/>} >Update</Button>
                        <Button variant="contained" color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/companyHome") }}>Cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
