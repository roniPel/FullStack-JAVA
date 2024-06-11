import "./Header.css";
import logo from "../../../Assets/Logo.png";
import { useNavigate } from "react-router-dom";
import { Button, ButtonGroup, Typography } from "@mui/material";
import { useState } from "react";
import { couponStore } from "../../../Redux/store";
import { logoutAction } from "../../../Redux/authReducer";
import axios from "axios";
import notify from "../../../Utilities/notify";
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import LogoutIcon from '@mui/icons-material/Logout';
import { clearCompanyStateAction } from "../../../Redux/companyReducer";
import { clearAdminStateAction } from "../../../Redux/adminReducer";
import { clearCustomerStateAction } from "../../../Redux/customerReducer";
import { ClientType } from "../../../Models/ClientType";

export function logoutBackend():void{
    //axios
    axios.put(`http://localhost:8080/Users/logout/${couponStore.getState().auth.clientType}`).then(res=>{
        notify.success("User was logged out successfully");
    })
    .catch((err)=>{
        console.log(err);
        notify.error("There was a problem logging out.");
    })
}
//TODO: Change LoginHeadline to: Hello, {user}}!
export function Header(): JSX.Element {
    const [isLogged, setLogged] = useState(false);
    const [userName, setName] = useState("");

    couponStore.subscribe(() => {
        setName(couponStore.getState().auth.name);
        setLogged(couponStore.getState().auth.isLogged);
    })

    const navigate = useNavigate();

    return (
        <div className="Header">
            <div>
                <img src={logo} width={150}/>
            </div>
            <div>
                <Typography variant="h2" className="MainHeadLine">Coupons System</Typography>
            </div>
            <div className="login">
                Hello {userName} <br />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color={isLogged ? "error" : "primary"} startIcon = {isLogged?<LogoutIcon/>:<></>}
                        onClick={() => {
                            if (isLogged) {
                                {logoutBackend()};
                                // clear relevant reducer
                                switch(couponStore.getState().auth.clientType){
                                    case ClientType.Administrator:
                                        couponStore.dispatch(clearAdminStateAction);
                                        break;
                                    case ClientType.Company:
                                        couponStore.dispatch(clearCompanyStateAction);
                                        break;
                                    case ClientType.Customer:
                                        couponStore.dispatch(clearCustomerStateAction);
                                        break;
                                }                               
                                // clear auth reducer
                                couponStore.dispatch(logoutAction());
                                navigate("/");
                            } else {                                                 
                                navigate("/login");
                            }
                        }}>{isLogged ? "Logout" : "Login"}</Button>
                    {!isLogged && <Button color="success" onClick={() => { navigate("/register") }}>register</Button>}
                </ButtonGroup>
            </div>
        </div>
    );
}
