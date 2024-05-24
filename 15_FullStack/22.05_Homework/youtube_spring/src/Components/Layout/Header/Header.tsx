import "./Header.css";
import logo from "../../../assests/uri.png";
import { useNavigate } from "react-router-dom";
import { Button, ButtonGroup } from "@mui/material";
import { useEffect, useState } from "react";
import { youtube } from "../../../redux/store";
import { logoutAction } from "../../../redux/authReducer";

export function Header(): JSX.Element {
    //let isLogged = false;
    const [isLogged, setLogged] = useState(false);
    const [userName, setName] = useState("");

    // useEffect(()=>{
    //     let userToken = localStorage.getItem("token") || "";        
    //     setLogged(userToken.length>5);        
    // },[])

    youtube.subscribe(() => {
        setName(youtube.getState().auth.name);
        setLogged(youtube.getState().auth.isLogged);
    })

    const navigate = useNavigate();
    return (
        <div className="Header">
            <div>
                <img src={logo} width={180} />
            </div>
            <div>
                <h1>Class 169</h1>
                <h2>the best that lecturer can get</h2>
            </div>
            <div className="login">
                Hello {userName} <br />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color={isLogged ? "error" : "primary"}
                        onClick={() => {
                            if (isLogged) {
                                sessionStorage.removeItem("jwt");               
                                youtube.dispatch(logoutAction());
                                localStorage.removeItem("token");
                                navigate("/login");
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
