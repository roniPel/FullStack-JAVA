import { Button, ButtonGroup, Checkbox, MenuItem, Select, TextField, Typography } from "@mui/material";
import "./Login.css";
import { SubmitHandler, useForm } from "react-hook-form";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import notify from "../../../util/notify";
import { youtube } from "../../../redux/store";
import { authState, loginAction } from "../../../redux/authReducer";
import { jwtDecode } from "jwt-decode";
import axiosJWT from "../../../util/axiosJWT";

type userLoginData = {
    userEmail: string;
    userPass: string;
    userType: string;
    userRemember: boolean;
}

type jwtData = { 
        "userType": string,
        "userName": string,
        "sub": string,
        "iat": number,
        "exp": number
}

//show message with animation on screen => npm install notyf

export function Login(): JSX.Element {
    const navigate = useNavigate();
    //get the methods that we need from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<userLoginData>();

    const makeLogin: SubmitHandler<userLoginData> = (data) => {
        //console.log(data);
        let userDetails = {
            "email":data.userEmail,
            "password":data.userPass,
            "userType":data.userType.toUpperCase()
        }
        axiosJWT.post("http://localhost:8080/user/login",userDetails)
        .then (res=>{
            // console.log(res);
            // if (data.userRemember){
            //     localStorage.setItem("token","here goes the jwt token");
            // } else {
            //     sessionStorage.setItem("token","here goes the jwt token");
            //     localStorage.removeItem("token");
            // }
            const JWT = res.headers["authorization"];
            const cleanToken = JWT.split(" ")[1];
            //console.log("from server:",JWT); //email,name,userType
            
            //decode to the token.
            //npm i jwt-decode
            const decoded_jwt = jwtDecode<jwtData>(cleanToken);
            console.log(decoded_jwt);
            let myAuth = {
                name: decoded_jwt.userName,
                email: decoded_jwt.sub,
                token : JWT,
                userType: decoded_jwt.userType,
                isLogged:true    
            };

            youtube.dispatch(loginAction(myAuth))
            if(data.userRemember===true){
                localStorage.setItem("token",myAuth.token);
            }
            
            notify.success("Welcome back");
            navigate("/");
        })
        .catch(err=>{
            console.log(err);
            notify.error("bad user or password !!");
        });
    }

    return (
        <div className="Login Box">
            <form onSubmit={handleSubmit(makeLogin)}>
                <Typography variant="h4" className="HeadLine">User Login</Typography><hr />
                <TextField label="user email" variant="outlined" {...register("userEmail")}/><br /><br />
                <TextField label="user password" variant="outlined" type="password" {...register("userPass")}/><br /><br/>
                <Select  fullWidth {...register("userType")} defaultValue="customer">
                    <MenuItem value="Customer">Customer</MenuItem>
                    <MenuItem value="Company">Company</MenuItem>
                    <MenuItem value="Admin">Admin</MenuItem>
                </Select>
                <br/>
                <Checkbox {...register("userRemember")}/> Remember me
                <hr />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color="success" style={{ marginRight: 10 }}>Login</Button>
                    <Button color="error">Cancel</Button>
                </ButtonGroup>
            </form>
        </div>
    );
}
