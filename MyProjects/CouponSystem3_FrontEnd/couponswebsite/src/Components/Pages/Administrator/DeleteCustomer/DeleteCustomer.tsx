import { useNavigate, useParams } from "react-router-dom";
import "./DeleteCustomer.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Customer } from "../../../../Models/Customer";
import { SubmitHandler, useForm } from "react-hook-form";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { deleteCustomerAction, getOneCustomerAction } from "../../../../Redux/adminReducer";
import { Button, ButtonGroup, Typography } from "@mui/material";
import  DeleteIcon  from "@mui/icons-material/Delete";
import CancelIcon from '@mui/icons-material/Cancel';

export function DeleteCustomer(): JSX.Element {
    const [customer, setCustomer] = useState<Customer>();
    const params = useParams();
    const { register, handleSubmit, formState: {errors} } = useForm<Customer>();
    const navigate = useNavigate();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        // check if we have customer in redux
        if(couponStore.getState().admin.customer.id == parseInt(params.customerID as string)){
            //console.log("Get from Store");
            setCustomer(couponStore.getState().admin.customer);
        } else {
            // Get customer from BackEnd
            //console.log("Get from Backend");
            getCustFromDB();
        }
    },[]);

    function getCustFromDB(){
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

    const onSubmit: SubmitHandler<Customer> = (data) => {
        //console.log(data);
        axiosJWT.delete(`http://localhost:8080/Admin/DeleteCustomer/${params.customerID}`)
        .then((res)=> {
            couponStore.dispatch(deleteCustomerAction(data.id));
            notify.success("The customer was deleted successfully.");
            navigate("/adminHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem deleting the customer.");
        })
    }
    
    return (
        <div>
            <Typography variant="h4" className="HeadLine">Delete Customer</Typography>
            <hr/>
            <div className="DeleteCustomer Box" style={{ width: "40%" }}>
            <div className="Grid-Parent">
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <div className="Grid-Child">
                            <Typography variant="h5" className="HeadLine">{customer?.firstName} {customer?.lastName}</Typography>
                            <br/>
                            <Typography variant="h6" className="HeadLine">{customer?.email}</Typography>
                            <br/><br/>
                        </div>
                        <div className="Grid-Child">
                            <ButtonGroup variant="contained" fullWidth>
                                <Button type="submit" variant="contained" color="error" startIcon={<DeleteIcon/>} >Delete</Button>
                                <Button variant="contained" color="primary" startIcon={<CancelIcon/>} onClick={() => { navigate("/adminHome") }}>Cancel</Button>
                            </ButtonGroup>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}
