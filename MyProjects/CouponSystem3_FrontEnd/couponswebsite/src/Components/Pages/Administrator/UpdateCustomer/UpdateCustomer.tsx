import { useNavigate, useParams } from "react-router-dom";
import "./UpdateCustomer.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Customer } from "../../../../Models/Customer";
import { SubmitHandler, useForm } from "react-hook-form";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getOneCustomerAction, updateCustomerAction } from "../../../../Redux/adminReducer";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';

export function UpdateCustomer(): JSX.Element {
    const navigate = useNavigate();
    const [customer, setCustomer] = useState<Customer>();
    const params = useParams();

    const { register, handleSubmit, formState: {errors} } = useForm<Customer>();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        getCustomer();
    },[]);

    function getCustomer(){
        // check if we have customer in redux
        if(couponStore.getState().admin.customer.id == parseInt(params.customerID as string)){
            //console.log("Get from Store");
            setCustomer(couponStore.getState().admin.customer);
        } else {
        // console.log("Getting customer data from Backend")
        // get customer data from backend
        axiosJWT.get(`http://localhost:8080/Admin/GetOneCustomer/${params.customerID}`).then(res=>{
            setCustomer(res.data);
            couponStore.dispatch(getOneCustomerAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
    }

    const onSubmit: SubmitHandler<Customer> = (data) => {
        data.id = parseInt(params.customerID as string);
        //console.log(data);
        axiosJWT.put(`http://localhost:8080/Admin/UpdateCustomer`,data)
        .then((res)=> {
            couponStore.dispatch(updateCustomerAction(data));
            notify.success("The customer was updated successfully.");
            navigate("/adminHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem updating the customer.");
        })
    }
    
    return (
        <div>
            <Typography variant="h4" className="HeadLine">Update Customer</Typography>
            <hr />
            <div className="UpdateCustomer Box" style={{ width: "40%" }}>
                <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            First Name:<input type="text" placeholder="First Name" defaultValue={customer?.firstName}  {...register("firstName")} />
                            <br/><br/>
                            Last Name: <input type="text" placeholder="Last Name" defaultValue={customer?.lastName}  {...register("lastName")} />
                            <br/><br/>
                            Email: <input type="text" placeholder="Email" defaultValue={customer?.email} {...register("email",{required:true})} />
                            <br/><br/>
                            Password: <input type="password" placeholder="Password" defaultValue={customer?.password} {...register("password",{required:true})} />
                            <br/><br/>
                            <ButtonGroup variant="contained" fullWidth>
                                <Button type="submit" variant="contained" color="primary" startIcon={<UpdateIcon/>} >Update</Button>
                                <Button variant="contained" color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/adminHome") }}>Cancel</Button>
                            </ButtonGroup>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}
