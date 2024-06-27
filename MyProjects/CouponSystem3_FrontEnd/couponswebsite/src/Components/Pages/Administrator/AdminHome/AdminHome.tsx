import { useNavigate } from "react-router-dom";
import "./AdminHome.css";
import { useEffect } from "react";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";

export function AdminHome(): JSX.Element {
    const navigate = useNavigate();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);

    return (
        <div className="AdminHome">
            <Typography variant="h4" className="HeadLine">{couponStore.getState().auth.name}'s Home</Typography>
            <hr/>
            <br/>
            <div className="Details Box" style={{ width: "40%" }}>
                <Typography variant="h5" className="HeadLine">My Details: </Typography>
                <hr/>
                <br/>
                <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <Typography variant="h4" className="HeadLine">Name: {couponStore.getState().auth.name}</Typography>
                        <br/>
                        <Typography variant="h6" className="HeadLine">Type: {couponStore.getState().auth.clientType}</Typography>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    );
}
