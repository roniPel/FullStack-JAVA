import { NavLink } from "react-router-dom";
import "./Menu.css";
import { Typography } from "@mui/material";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
            <br/><Typography variant="h4" className="HeadLine">Menu</Typography><br/><hr/>
            <NavLink to="/">Home</NavLink><br/>
            <NavLink to="/login">Login</NavLink><br/>
            <NavLink to="/register">Register</NavLink><br/>
            <NavLink to="/aboutus">About Us</NavLink><br/>
            <hr/>
        </div>
    );
}
