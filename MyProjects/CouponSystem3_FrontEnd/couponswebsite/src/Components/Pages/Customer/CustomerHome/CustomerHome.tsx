import { useEffect, useState } from "react";
import "./CustomerHome.css";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { useNavigate } from "react-router-dom";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import axiosJWT from "../../../../Utilities/axiosJWT";
import axios from "axios";
import { Customer } from "../../../../Models/Customer";
import { getOneCustomerAction } from "../../../../Redux/adminReducer";
import { checkData } from "../../../../Utilities/checkData";
import { getCustomerDetailsAction } from "../../../../Redux/customerReducer";

export function CustomerHome(): JSX.Element {
    const navigate = useNavigate();
    const [customer, setLocalCustomer] = useState<Customer>();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Customer){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        checkData();
        // check if requested customer exists in redux
        if(couponStore.getState().customer.customerDetails !== null){
            // If redux is not empty but requested customer doesn't exist in it
            if(couponStore.getState().customer.customerDetails.id === -1){
                getCustDetailsFromDB();
            } else {
                setLocalCustomer(couponStore.getState().customer.customerDetails);
            }
        } else {    // If redux is empty
            getCustDetailsFromDB();
        }
    },[]);

    function getCustDetailsFromDB(){
        // console.log("Getting customer data from Backend")
        // get customer data from backend
        axios.get(`http://localhost:8080/Customer/GetCustomerDetails`).then(res=>{
            setLocalCustomer(res.data);
            couponStore.dispatch(getCustomerDetailsAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
    }
    
    return (
        <div className="CustomerHome">
            <br/><Typography variant="h4" className="HeadLine">{couponStore.getState().auth.name}'s Home</Typography>
            <hr/>
            <br/>
            <div className="Details Box" style={{ width: "40%" }}>
                <Typography variant="h5" className="HeadLine">My Details: </Typography>
                <hr/>
                <br/>
                <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <Typography variant="h4" className="HeadLine">{customer?.firstName} {customer?.lastName}</Typography>
                        <br/>
                        <Typography variant="h6" className="HeadLine">{customer?.email}</Typography>
                        <br/>
                    </div>
                    <div className="Grid-Child">
                        <ButtonGroup variant="contained" fullWidth>
                        </ButtonGroup>
                    </div>
                </div>
            </div>
        </div>
    );
}

