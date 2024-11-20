import { Button, ButtonGroup, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import "./UpdateCoupon.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import { useNavigate, useParams } from "react-router-dom";
import { Coupon } from "../../../../Models/Coupon";
import { SubmitHandler, useForm } from "react-hook-form";
import notify from "../../../../Utilities/notify";
import { getOneCouponViaCompanyAction, updateCouponAction } from "../../../../Redux/companyReducer";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';
import axiosJWT from "../../../../Utilities/axiosJWT";
import { CouponCategory } from "../../../../Models/CouponCategory";

export function UpdateCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const [catKey, setCategoryKey] = useState<keyof typeof CouponCategory>();
    const params = useParams();
    

    const { register, handleSubmit, formState: {errors} } = useForm<Coupon>({
        resetOptions: { keepDefaultValues: true }
    });
    
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
        let reduxCoupon = couponStore.getState().company.coupon;
        if(params.couponID && (reduxCoupon !== undefined) && (reduxCoupon.id === +params.couponID)){
            //console.log("Get from Store: \n",coupon);
        } else {
        //console.log("Get from Backend")
        // get data from backend
        axiosJWT.get(`http://localhost:8080/Company/GetOneCoupon/${params.couponID}`)
        .then((res)=>{
            setCoupon(res.data as Coupon);
            couponStore.dispatch(getOneCouponViaCompanyAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
        setCoupon(couponStore.getState().company.coupon as Coupon);
        //console.log("The coupon is: "+coupon);
        let key: keyof typeof CouponCategory = coupon?.category as CouponCategory;
        setCategoryKey(key);
    }

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        // Insert required data
        data.id = parseInt(params.couponID as string);
        // data.category? console.log(data.category) : data.category = coupon?.category as CouponCategory;
        //console.log(data);
        axiosJWT.put(`http://localhost:8080/Company/UpdateCoupon/${[params.couponID]}`,data)
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
            <div className="UpdateCoupon"> 
                <div className="Box" style={{ width: "40%" }}>
                    <form onSubmit={handleSubmit(onSubmit)}>

                        {/* <TextField required type="text" label="Title" defaultValue={coupon?.title} fullWidth {...register("title",{required:true})} />
                        {errors.title?.type == "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                        <br />
                        <TextField type="text" label="Description" defaultValue={coupon?.description} fullWidth {...register("description")} />
                        <br />
                        <InputLabel id="startDate">Start Date</InputLabel>
                        <TextField required id = "startDate" type="date" defaultValue={coupon?.start_date} fullWidth {...register("start_date")} />
                        <br />
                        <InputLabel id="endDate">End Date</InputLabel>
                        <TextField required id = "endDate" type="date" defaultValue={coupon?.end_date} fullWidth {...register("end_date")} />
                        <br />
                        <TextField required type="number" label="Amount" defaultValue={coupon?.amount} fullWidth {...register("amount", { required: true })} />
                        <br />
                        <TextField type="number" label="Price" defaultValue={coupon?.price} fullWidth {...register("price", { required: true })} />
                        <br />
                        <InputLabel id="Category-label">Select Category</InputLabel>
                        <Select labelId="Category-label" id="Category-label" label="Category" {...register("category")} defaultValue={coupon?.category} fullWidth >
                            <MenuItem disabled selected> -- select a category -- </MenuItem>
                            {Object.entries(CouponCategory).map(([key,val])=><MenuItem key={key} value={val}>{val}</MenuItem>)}
                        </Select>
                        <br/>
                        <TextField type="text" defaultValue={coupon?.image} label="Image" fullWidth {...register("image")} />
                        <br/> */}
                        <label>Title: </label>
                        <input required type="text" placeholder="Title" defaultValue={coupon?.title} {...register("title",{required:true})} />
                        {errors.title?.type === "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                        <br /><br />
                        <label>Description: </label>
                        <input required type="text" placeholder="Description" defaultValue={coupon?.description} {...register("description", {required:true})} />
                        <br /><br />
                        <label>Start Date: </label>
                        <input required type="date" placeholder="Start Date" defaultValue={coupon?.start_date} {...register("start_date", { required: true })} />
                        <br /><br />
                        <label>End Date: </label>
                        <input required type="date" placeholder="End Date" defaultValue={coupon?.end_date} {...register("end_date", { required: true })} />
                        <br /><br />
                        <label>Amount: </label>
                        <input required type="number" placeholder="Amount" defaultValue={coupon?.amount} {...register("amount", { required: true })} />
                        <br /><br />
                        <label>Price: </label>
                        <input required type="number" placeholder="Price" defaultValue={coupon?.price} {...register("price", { required: true })} />
                        <br /><br />
                        {/* onChange={handleCatChange} */}
                        {/* <label>Category: </label>
                        <select required  defaultValue={coupon?.category} {...register("category", {required:true})} >
                            <option disabled > -- select category -- </option>
                            {Object.entries(CouponCategory).map(([key,val])=><option key={val} value={val}>{val}</option>)}
                            {errors.category?.type == undefined && <><br /><span style={{ color: "red" }}>Category is required</span></>}
                        </select><br /><br/> */}
                        <InputLabel id="Category-label">Select Category</InputLabel>
                        <Select labelId="Category-label" id="Category-label" label="Category" defaultValue={catKey} {...register("category", {required:true})} style={{width:"40%"}} >
                            {Object.entries(CouponCategory).map(([key,val])=><MenuItem key={key} value={val}>{val}</MenuItem>)}
                        </Select>
                        {errors.category?.type === "required" && <><br /><span style={{ color: "red" }}>Category is required</span></>}
                        <br/><br/>
                        <label>Image: </label>
                        <input required type="text" placeholder="Image" defaultValue={coupon?.image} {...register("image", {required:true})} />
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
        </div>
    );
}
