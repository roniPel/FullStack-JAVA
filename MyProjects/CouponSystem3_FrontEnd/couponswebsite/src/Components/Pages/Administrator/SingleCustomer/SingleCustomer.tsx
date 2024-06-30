import { useNavigate } from "react-router-dom";
import "./SingleCustomer.css";
import { useEffect } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Customer } from "../../../../Models/Customer";
import { Card, CardActionArea, CardContent, Typography } from "@mui/material";

interface customerProps {
    customer: Customer;
}

export function SingleCustomer(props: customerProps): JSX.Element {
    const navigate = useNavigate();
    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);

    return (
        <div className="SingleCustomer Box" onClick = {()=>{
            navigate(`/customer/${props.customer.id.valueOf()}`)
        }}>
            <Card sx={{ maxWidth: 345 }}>
                <CardActionArea>
                    <CardContent>
                    <Typography variant="h6" color="text.secondary">
                    ID: {props.customer.id}
                    </Typography >
                    <Typography gutterBottom variant="h5" component="div">
                    {props.customer.firstName} {props.customer.lastName}
                    </Typography>
                    <Typography variant="h6" color="text.secondary">
                    Email: {props.customer.email}
                    </Typography >
                    </CardContent>
                </CardActionArea>
            </Card>
        </div>
    );
}
