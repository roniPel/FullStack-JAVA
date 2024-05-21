import { NavLink } from "react-router-dom";
import "./Menu.css";
import { Typography } from "@mui/material";
import { useState } from "react";
import { couponStore } from "../../../Redux/store";

export function Menu(): JSX.Element {

    const [isAdmin,setAdmin] = useState(false);

    couponStore.subscribe(()=>{
        setAdmin(couponStore.getState().auth.userType==="ADMIN");
    });
    
    const simpleMenu = ()=>{
        return (
            <>
                <br/><Typography variant="h4" className="HeadLine">Menu</Typography><br/>
                <hr/>
                <NavLink to="/">Home</NavLink><br/>
                <NavLink to="/aboutus">About Us</NavLink><br/>
                <hr/>
            </>
        )
    }

    const adminMenu = ()=>{
        return (
            <>
                <NavLink to="/">User List</NavLink><br/>
                <NavLink to="/">Find User</NavLink><br/>
                <NavLink to="/">Delete User</NavLink><br/>
                <hr/>
            </>
        )
    }
    return (
        <div className="Menu">
			{simpleMenu()}
            <hr/>
            {isAdmin && adminMenu()}
        </div>
    );


    return (
        <div className="Menu">
            <br/><Typography variant="h4" className="HeadLine">Menu</Typography><br/><hr/>
            <NavLink to="/">Home</NavLink><br/>
            <NavLink to="/aboutus">About Us</NavLink><br/>
            <hr/>
        </div>
    );
}
