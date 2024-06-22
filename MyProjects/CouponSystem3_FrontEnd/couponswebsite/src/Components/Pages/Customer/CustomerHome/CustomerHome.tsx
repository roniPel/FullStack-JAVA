import { useEffect } from "react";
import "./CustomerHome.css";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { useNavigate } from "react-router-dom";
import { TextField, Typography } from "@mui/material";

export function CustomerHome(): JSX.Element {
    const navigate = useNavigate();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Customer){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);
    
    return (
        <div className="CustomerHome">
            <Typography variant="h4" className="HeadLine">{couponStore.getState().auth.name}'s Home</Typography>
            <hr/>
            <br/>
            <div className="Details Box" style={{ width: "40%" }}>
                <Typography variant="h5" className="HeadLine">My Details: </Typography>
                <hr/>
                <br/>
                <div className="Grid-Parent">
                    <form>
                        <div className="Grid-Child">
                            <TextField type="text" label="ID" variant="outlined" fullWidth margin="dense" value={couponStore.getState().auth.id} />
                            <TextField type="text" label="Name" variant="outlined" fullWidth margin="dense" value={couponStore.getState().auth.name} />
                            <TextField type="text" label="Type" variant="outlined" fullWidth margin="dense" value={couponStore.getState().auth.clientType} />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}
