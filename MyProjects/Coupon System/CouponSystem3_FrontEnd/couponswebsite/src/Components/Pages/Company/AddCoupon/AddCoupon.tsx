import { useNavigate } from "react-router-dom";
import "./AddCoupon.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Coupon } from "../../../../Models/Coupon";
import { useEffect } from "react";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { ClientType } from "../../../../Models/ClientType";
import { Button, ButtonGroup, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import AddIcon from '@mui/icons-material/Add';
import { addCouponAction } from "../../../../Redux/companyReducer";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { CouponCategory } from "../../../../Models/CouponCategory";

export function AddCoupon(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Coupon>();
    
    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[])

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        data.id = 0;
        data.companyId = parseInt(couponStore.getState().auth.id);
        //console.log(data);
        //Todo - check that the passwords are the same , if not, do not countinue

        axiosJWT.post("http://localhost:8080/Company/AddCoupon",data)
        .then((res)=>{
            console.log(res.data);
            data.id = res.data;
            couponStore.dispatch(addCouponAction(data));
            notify.success("Coupon was added successfully");
            navigate("/companyHome");
        })
        .catch(err=>{
            console.log(err);
            notify.error("There was a problem adding the coupon");
        });
    }

    return (
        <div className="AddCoupon">
			<div className="Box" style={{ width: "30%" }}>
                <Typography variant="h4" className="HeadLine">Create New Coupon</Typography>
                <hr /><br/>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField required type="text" label="Title" fullWidth 
                    {...register("title",{required:true, maxLength:50})} />
                    {errors.title?.type === "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                    {errors.title?.type == "maxLength" && <><br/><span style={{ color: "red" }}>Title is too long. Max length: 50</span></>}
                    <br /><br />
                    <TextField type="text" label="Description" fullWidth 
                    {...register("description", {maxLength: 70})} />
                    {errors.description?.type == "maxLength" && <><br/><span style={{ color: "red" }}>Description is too long. Max length: 70</span></>}
                    <br /><br />
                    <InputLabel id="startDate">Start Date</InputLabel>
                    <TextField required id = "startDate" type="date" fullWidth {...register("start_date",{required:true})} />
                    <br /><br />
                    <InputLabel id="endDate">End Date</InputLabel>
                    <TextField required id = "endDate" type="date" fullWidth {...register("end_date",{required:true})} />
                    <br /><br />
                    <TextField required type="number" label="Amount" fullWidth {...register("amount", { required: true })} />
                    <br /><br />
                    <TextField required type="number" label="Price" fullWidth {...register("price", { required: true })} />
                    <br /><br />
                    <InputLabel id="category-label">Select Category</InputLabel>
                    <Select required labelId="category-label" id="Category" label="Select Category" {...register("category", {required:true})} fullWidth >
                        <MenuItem disabled selected> -- select a category -- </MenuItem>
                        {Object.entries(CouponCategory).map(([key,val])=><MenuItem key={key} value={val}>{val}</MenuItem>)}
                    </Select>
                    <br/><br/>
                    <TextField type="text" label="Image" fullWidth {...register("image")} />
                    <br/><br />
                    <hr />
                    <br/>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" startIcon={<AddIcon/>} >Add Coupon</Button>
                        <Button color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/") }}>Cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
