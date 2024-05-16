import "./Header.css";
import logo from "../../../Assets/Logo.png";
import { useNavigate } from "react-router-dom";
import { Button, ButtonGroup, Typography } from "@mui/material";

//TODO: Change LoginHeadline to: Hello, {user}}!
export function Header(): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="Header">
            <div>
                <img src={logo} width={180}/>
            </div>
            <div>
                <Typography variant="h2" className="MainHeadLine">Coupons System</Typography>
            </div>
            <div className="login">
            <Typography variant="h6" className="LoginHeadline">Hello, XXX!</Typography>           
                <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" onClick={()=>{navigate("/login")}}>login</Button>
                        <Button color="success" onClick={() => { navigate("/register") }}>register</Button>
                </ButtonGroup>
            </div>
        </div>
    );
}
