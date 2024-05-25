import { Button, ButtonGroup, Checkbox, MenuItem, Select, TextField, Typography } from "@mui/material";
import "./Login.css";
import { SubmitHandler, useForm } from "react-hook-form";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import notify from "../../../util/notify";

type userLoginData = {
    userEmail: string;
    userPass: string;
    userType: string;
    userRemember: boolean;
}


//show message with animation on screen => npm install notyf

export function Login(): JSX.Element {
    const navigate = useNavigate();
    //get the methods that we need from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<userLoginData>();

    const makeLogin: SubmitHandler<userLoginData> = (data) => {
        console.log(data);
        axios.post("http://localhost:8080/user/login",data)
        .then (res=>{
            console.log(res);
            if (data.userRemember){
                localStorage.setItem("token","here goes the jwt token");
            } else {
                sessionStorage.setItem("token","here goes the jwt token");
                localStorage.removeItem("token");
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
                    <MenuItem value="customer">Customer</MenuItem>
                    <MenuItem value="company">Company</MenuItem>
                    <MenuItem value="admin">Admin</MenuItem>
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
