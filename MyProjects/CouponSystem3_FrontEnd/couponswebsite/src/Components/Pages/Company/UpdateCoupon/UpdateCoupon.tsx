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
import { Key } from "@mui/icons-material";

export function UpdateCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const params = useParams();
    const [categoryList, setCategoryList] = useState<string[]>([]);

    const { register, watch, handleSubmit, formState: {errors} } = useForm<Coupon>({
        resetOptions: { keepDefaultValues: true }
    });
    const watchAllFields = watch();
    
    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        getCoupon();
        // create a list of categories from the 'CouponCategory' Model
        const catList: string[] = [];
        Object.keys(CouponCategory).map((item)=>{catList.push(item)})
        //console.log(catList);
        setCategoryList(catList);
        // Check values for watch
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
        console.log("The coupon is: "+coupon);
    }

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        data.id = parseInt(params.couponID as string);
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
                        <Select labelId="Category-label" id="Category-label" label="Category" {...register("category")} 
                        defaultValue={coupon?.category} fullWidth >
                            <MenuItem value=""><em>None</em></MenuItem>
                            <MenuItem key = {Category.BabyToddler} value = {Category.BabyToddler}>{Category.BabyToddler}</MenuItem>
                            <MenuItem key = {Category.Automotive} value = {Category.Automotive}>{Category.Automotive}</MenuItem>
                        </Select>
                        <br/>
                        <TextField type="text" label="Image" fullWidth {...register("image")} />
                        <br/> */}

                        <input type="text" placeholder="Title" defaultValue={coupon?.title} {...register("title")} />
                        {errors.title?.type == "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                        <br /><br />
                        <input type="text" placeholder="Description" defaultValue={coupon?.description} {...register("description")} />
                        <br /><br />
                        <label>Start Date: </label>
                        <input type="date" placeholder="Start Date" defaultValue={coupon?.start_date} {...register("start_date")} />
                        <br /><br />
                        <label>End Date: </label>
                        <input type="date" placeholder="End Date" defaultValue={coupon?.end_date} {...register("end_date")} />
                        <br /><br />
                        <input type="number" placeholder="Amount" defaultValue={coupon?.amount} {...register("amount")} />
                        <br /><br />
                        <input type="number" placeholder="Price" defaultValue={coupon?.price} {...register("price")} />
                        <br /><br />
                        <label>Select Category:</label><br />
                        <select 
                            // Object.entries(CouponCategory).filter(([key,val]) => key === {coupon?.category as string}) } 
                        {...register("category")} >
                            {Object.entries(CouponCategory).map(([key,val])=><option key={val} value={val}>{val}</option>)} 
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
        </div>
    );
}
