import { Button, ButtonGroup, Checkbox, MenuItem, Select, TextField, Typography } from "@mui/material";
import "./Logon.css";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { ClientType } from "../../../Models/ClientType";
import axiosJWT from "../../../Utilities/axiosJWT";
import { jwtDecode } from "jwt-decode";
import { couponStore } from "../../../Redux/store";
import { loginAction } from "../../../Redux/authReducer";
import notify from "../../../Utilities/notify";

type userLoginData = {
    userEmail: string;
    userPass: string;
    clientType: string;
    userRemember: boolean;
}

type jwtData = { 
        "userType": string,
        "userName": string,
        "sub": string,
        "iat": number,
        "exp": number
}

export function Logon(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<userLoginData>();

    const onSubmit: SubmitHandler<userLoginData> = (data) => {
        console.log(data);
        let userDetails = {
            "email":data.userEmail,
            "password":data.userPass,
            "userType":data.clientType,
        }
        //check that the passwords are the same , if not, do not countinue
        
        //move to axios :)
        axiosJWT.post("http://localhost:8080/Users/login",userDetails)
        .then (res=>{
            const JWT = res.headers["authorization"].split(" ")[1];
            // Decoding JWT token:
            const decoded_jwt = jwtDecode<jwtData>(JWT);
            console.log(decoded_jwt);

            let myAuth = {
                name: decoded_jwt.userName,
                id: decoded_jwt.sub,
                token : JWT,
                userType: decoded_jwt.userType,
                isLogged:true,
                rememberMe:data.userRemember,    
            };
            // Update Redux with user details
            couponStore.dispatch(loginAction(myAuth))
            notify.success("Welcome back");
            navigate("/");
        })
        .catch(err=>{
            console.log(err);
            notify.error("bad user or password !!");
        });
    }
        
    return (
        <div className="Login">
            <div className="Box" style={{ width: "40%" }}>
                <Typography variant="h4" className="HeadLine">User Login</Typography>
                <hr /><br/>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField label="user email" variant="outlined" {
                        ...register("userEmail", { required: true })} fullWidth />
                    {errors.userEmail && <span style={{ color: "red" }}>Email is required</span>}
                    <br /><br />
                    <TextField label="user password" type="password" variant="outlined" {
                        ...register("userPass", { required: true})} fullWidth />
                    <br/>
                    <Checkbox {...register("userRemember")}/> Remember me
                    <hr /><br/>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" >Login</Button>
                        <Button color="error" onClick={() => { navigate("/") }}>cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
