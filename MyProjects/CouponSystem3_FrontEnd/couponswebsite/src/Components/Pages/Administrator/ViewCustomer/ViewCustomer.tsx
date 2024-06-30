import { useNavigate, useParams } from "react-router-dom";
import "./ViewCustomer.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Customer } from "../../../../Models/Customer";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getOneCustomerAction } from "../../../../Redux/adminReducer";
import { checkData } from "../../../../Utilities/checkData";
import { Button, ButtonGroup, Typography } from "@mui/material";
import  DeleteIcon  from "@mui/icons-material/Delete";
import UpdateIcon from '@mui/icons-material/Update';

export function ViewCustomer(): JSX.Element {
    const navigate = useNavigate();
    const params = useParams();
    const [customer, setLocalCustomer] = useState<Customer>();
    const [customerList, setLocalCustList] = useState<Customer[]>([]);

    function getCustFromDB(){
        // console.log("Getting customer data from Backend")
        // get customer data from backend
        axiosJWT.get(`http://localhost:8080/Admin/GetOneCustomer/${params.customerID}`).then(res=>{
            setLocalCustomer(res.data);
            couponStore.dispatch(getOneCustomerAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
    }

    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        else {
            checkData();
            // // check if requested customer exists in redux
            // if(couponStore.getState().admin.customers.length>0){
            //     console.log("From Redux: "+couponStore.getState().admin.customers);
            //     setLocalCustomer(couponStore.getState().admin.customers);
            //     for(let index =0; index< 3; index++){
            //         if(customerList[index]?.id===parseInt(params.customerID as string)){
            //             setLocalCustomer(customerList[index]);
            //         }
            //     }
            //     // If redux is not empty but requested customer doesn't exist in it
            //     if(customer?.email === ""){
            //         getCustFromDB();
            //     }
            // } else {    // If redux is empty
            //     getCustFromDB();
            // }
            getCustFromDB();
        }
    },[]);
    return (
        <div className="ViewCustomer Box">
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <Typography variant="h6" className="HeadLine">ID: {customer?.id}</Typography>
                    <br/>
                    <Typography variant="h4" className="HeadLine">{customer?.firstName} {customer?.lastName}</Typography>
                    <br/>
                    <Typography variant="h6" className="HeadLine">{customer?.email}</Typography>
                    <br/><br/>
                </div>
                <div className="Grid-Child">
                    <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/deleteCust/${customer?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/updateCust/${customer?.id}`) }}>Update</Button>
                    </ButtonGroup>
                </div>
            </div>
        </div>
    );
}
