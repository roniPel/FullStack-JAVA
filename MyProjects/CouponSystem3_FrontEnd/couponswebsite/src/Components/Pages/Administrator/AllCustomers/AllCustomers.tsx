import { useNavigate } from "react-router-dom";
import "./AllCustomers.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Customer } from "../../../../Models/Customer";
import { checkData } from "../../../../Utilities/checkData";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getAllCustomersAction } from "../../../../Redux/adminReducer";
import { Typography } from "@mui/material";
import { SingleCustomer } from "../SingleCustomer/SingleCustomer";

export function AllCustomers(): JSX.Element {
    const navigate = useNavigate();
    const [customerList, setList] = useState<Customer[]>([]);

    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }

        //get company list from backend
        let recivedList:Customer[] = [];

        // Check that our redux data is updated
        checkData();
        //check if we have any companies in our list - 0 length indicates that we don't have any companies
        if (couponStore.getState().admin.customers.length == 0) {
            axiosJWT.get("http://localhost:8080/Admin/GetAllCustomers")
            .then(result=>{
                for (let index=0;index<result.data.length;index++){
                    recivedList.push(new Customer(
                        result.data[index].id,
                        result.data[index].firstName,
                        result.data[index].lastName,
                        result.data[index].email,
                        result.data[index].password
                    ));
                };
                setList(recivedList);                   
                couponStore.dispatch(getAllCustomersAction(recivedList));
                setList(couponStore.getState().admin.customers);
            })
            .catch((err)=>{
                navigate("/login")
            });
        } else {
            setList(couponStore.getState().admin.customers);
        }

    },[]);
    return (
        <div className="AllCustomers">
			<br/><Typography variant="h4" className="HeadLine">All Customers</Typography><hr/><br/>
            {customerList.map(item=><SingleCustomer key={item.id} customer={item}/>)}
        </div>
    );
}
