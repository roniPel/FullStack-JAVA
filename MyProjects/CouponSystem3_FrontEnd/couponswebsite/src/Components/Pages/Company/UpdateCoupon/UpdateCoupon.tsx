import { Button, ButtonGroup, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
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
import { getOneCouponViaCompanyAction, updateCouponAction } from "../../../../Redux/companyReducer";
import { Category } from "../../../../Models/Category";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';

export function UpdateCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [coupon, setCoupon] = useState<Coupon>();
    const params = useParams();
    const [categoryList, setCategoryList] = useState<string[]>([]);

    const { register, handleSubmit, formState: {errors} } = useForm<Coupon>();
    
    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        getCoupon();
        // create a list of categories from the 'Category' Model
        const catList: string[] = [];
        Object.keys(Category).map((item)=>{catList.push(item)})
        //console.log(catList);
        setCategoryList(catList);
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
            couponStore.dispatch(getOneCouponViaCompanyAction(res.data));
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
                        {categoryList.map((item)=><option label={item} value={item}>{item}</option>)} 
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
