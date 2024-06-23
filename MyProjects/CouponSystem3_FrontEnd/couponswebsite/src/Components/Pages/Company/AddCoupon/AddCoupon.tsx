import { useNavigate } from "react-router-dom";
import "./AddCoupon.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Coupon } from "../../../../Models/Coupon";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { ClientType } from "../../../../Models/ClientType";
import axios from "axios";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
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
    },[])

    const onSubmit: SubmitHandler<Coupon> = (data) => {
        data.id = 0;
        data.companyId = parseInt(couponStore.getState().auth.id);
        console.log(data);
        //Todo - check that the passwords are the same , if not, do not countinue

        //todo, move to axios :)
        axios.post("http://localhost:8080/Company/AddCoupon",data)
        .then((res)=>{
            //couponStore.dispatch(addCouponAction(data));  // Problematic - sets incorrect ID
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
			<div className="Box" style={{ width: "40%" }}>
                <Typography variant="h4" className="HeadLine">Create New Coupon</Typography>
                <hr /><br/>
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField required label="Title" variant="outlined" {...register("title",{required:true})} fullWidth />
                    {errors.title?.type == "required" && <><br /><span style={{ color: "red" }}>Title is required</span></>}
                    <br /><br />
                    <TextField label="Description" variant="outlined" {...register("description")} fullWidth />
                    <br /><br />
                    <input type="date" placeholder="Start Date"{...register("start_date", { required: true })} />
                    <br /><br />
                    <input type="date" placeholder="End Date"{...register("end_date", { required: true })} />
                    <br /><br />
                    <input type="number" placeholder="Amount"{...register("amount", { required: true })} />
                    <br /><br />
                    <input type="number" placeholder="Price"{...register("price", { required: true })} />
                    <br /><br />
                    <label>Select Category:</label><br />
                    <select {...register("category")} >
                        <option key = {Category.Automotive} value = {Category.Automotive}>{Category.Automotive}</option>
                        <option key = {Category.BabyToddler} value = {Category.BabyToddler}>{Category.BabyToddler}</option>
                    </select><br /><br/>
                    <TextField label="Image" variant="outlined" {...register("image")} fullWidth />
                    <br/><br />
                    <hr />
                    <br/>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" startIcon={<AddIcon/>} >Create Coupon</Button>
                        <Button color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/") }}>Cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
