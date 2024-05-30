import { Button, ButtonGroup, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import "./Register.css";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { ClientType } from "../../../Models/ClientType";
import axios from "axios";
import notify from "../../../Utilities/notify";
import { UserDetails } from "../../../Models/UserDetails";

export function Register(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<UserDetails>();
    const onSubmit: SubmitHandler<UserDetails> = (data) => {
        data.clientType = ClientType.Customer;
        console.log(data);
        //check that the passwords are the same , if not, do not countinue

        //todo, move to axios :)
        axios.post("http://localhost:8080/Users/register",data)
        .then((res)=>{
            navigate("/login");
        })
        .catch(err=>{
            console.log(err);
            notify.error("There was a problem saving the user");
        });
    }

    return (
        <div className="Register">
            <div className="Box" style={{ width: "40%" }}>
                <Typography variant="h4" className="HeadLine">User Register</Typography>
                <hr /><br/>
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField label="user name" variant="outlined" {...register("userName", { required: true })} fullWidth />
                    <br /><br />
                    <TextField label="user email" variant="outlined" {
                        ...register("userEmail", { required: true })} fullWidth />
                    {errors.userEmail && <span style={{ color: "red" }}>Email is required</span>}
                    <br /><br />
                    <TextField label="user password" type="password" variant="outlined" {
                        ...register("userPassword", { required: true, minLength: 5, maxLength: 10 })} fullWidth />
                    {errors.userPassword?.type == "required" && <><br /><span style={{ color: "red" }}>password is required</span></>}
                    {errors.userPassword?.type == "minLength" && <><br /><span style={{ color: "red" }}>password is too short</span></>}
                    {errors.userPassword?.type == "maxLength" && <><br /><span style={{ color: "red" }}>password is too long</span></>}
                    <br /><br />
                    <TextField label="password check" variant="outlined" type="password" fullWidth />
                    <br /><br />
                    
                    {/* Section below only relevant for Admin registration of new Company
                    <Select
                        size="medium"
                        {...register("clientType")} 
                        defaultValue={ClientType.Customer}
                        fullWidth
                        labelId="user type"
                        id="user type"
                        label="user type"
                        variant="outlined"
                        autoWidth
                        >
                        <MenuItem value=""><em>User Type</em></MenuItem>
                        <MenuItem value={ClientType.Administrator}>Administrator</MenuItem>
                        <MenuItem value={ClientType.Company}>Company</MenuItem>
                        <MenuItem value={ClientType.Customer}>Customer</MenuItem>
                    </Select> */}

                    <br/><br/>
                    <hr />
                    <br/>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" >register</Button>
                        <Button color="error" onClick={() => { navigate("/") }}>cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
