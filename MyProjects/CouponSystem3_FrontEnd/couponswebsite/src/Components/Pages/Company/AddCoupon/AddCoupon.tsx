import { useNavigate } from "react-router-dom";
import "./AddCoupon.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Coupon } from "../../../../Models/Coupon";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { ClientType } from "../../../../Models/ClientType";
import axios from "axios";
import { Button, ButtonGroup, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import AddIcon from '@mui/icons-material/Add';
import DatePicker from "react-datepicker";
import { Category } from "../../../../Models/Category";
import { parseJsonText } from "typescript";
import { addCouponAction } from "../../../../Redux/companyReducer";

export function AddCoupon(): JSX.Element {
    const navigate = useNavigate();
    const [categoryList, setCategoryList] = useState<string[]>([]);

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Coupon>();
    
    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        // create a list of categories from the 'Category' Model
        const catList: string[] = [];
        Object.keys(Category).map((item)=>{catList.push(item)})
        //console.log(catList);
        setCategoryList(catList);
    },[])

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        data.id = couponStore.getState().guest.allCoupons.length+1;;
        data.companyId = parseInt(couponStore.getState().auth.id);
        console.log(data);
        //Todo - check that the passwords are the same , if not, do not countinue

        axios.post("http://localhost:8080/Company/AddCoupon",data)
        .then((res)=>{
            //couponStore.dispatch(addCouponAction(data));  // Problematic - sets incorrect ID
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
                    <TextField required type="text" label="Title" fullWidth {...register("title",{required:true})} />
                    {errors.title?.type == "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                    <br /><br />
                    <TextField type="text" label="Description" fullWidth {...register("description")} />
                    <br /><br />
                    <InputLabel id="startDate">Start Date</InputLabel>
                    <TextField required id = "startDate" type="date" fullWidth {...register("start_date")} />
                    <br /><br />
                    <InputLabel id="endDate">End Date</InputLabel>
                    <TextField required id = "endDate" type="date" fullWidth {...register("end_date")} />
                    <br /><br />
                    <TextField required type="number" label="Amount" fullWidth {...register("amount", { required: true })} />
                    <br /><br />
                    <TextField type="number" label="Price" fullWidth {...register("price", { required: true })} />
                    <br /><br />
                    <InputLabel id="Category-label">Select Category</InputLabel>
                    <Select labelId="Category-label" id="Category-label" label="Category" {...register("category")} fullWidth >
                        {categoryList.map((item)=><MenuItem key={item} value={item}>{item}</MenuItem>)}
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
