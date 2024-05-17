import { Button, Typography } from "@mui/material";
import "./Welcome.css";
import { NavLink, useNavigate } from "react-router-dom";

export function Welcome(): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="Welcome">
			<br/><Typography variant="h4" className="HeadLine">Our Home is Your Home</Typography><br/>
        </div>
    );
}
