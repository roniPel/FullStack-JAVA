import { useState } from "react";
import "./Register.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { UserDetails } from "../../../model/UserDetails";
import { useNavigate } from "react-router-dom";
import { TextField, Typography, Button, ButtonGroup } from "@mui/material";
import axios from "axios";
import notify from "../../../util/notify";

//for using form in efficent way, we will install => npm install react-hook-form
//for using Material UI (MUI) =>
//npm install @mui/material @emotion/react @emotion/styled @mui/icons-material
export function Register(): JSX.Element {
    // const [id,setId] = useState(0);
    // const [email,setEmail] = useState("");

    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<UserDetails>();

    const onSubmit: SubmitHandler<UserDetails> = (data) => {
        console.log(data)
        //check that the passwords are the same , if not, do not countinue
        data.id = 0;

        //todo, move to axios :)
        axios.post("http://localhost:8080/user/register",data)
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
                <hr />
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField label="user name" variant="outlined" {...register("name", { required: true })} fullWidth />
                    <br /><br />
                    <TextField label="user email" variant="outlined" {
                        ...register("email", { required: true })} fullWidth />
                    <br />{errors.email && <span style={{ color: "red" }}>Email is required</span>}
                    <br /><br />
                    <TextField label="user password" type="password" variant="outlined" {
                        ...register("password", { required: true, minLength: 5, maxLength: 10 })} fullWidth />
                    {errors.password?.type == "required" && <><br /><span style={{ color: "red" }}>password is required</span></>}
                    {errors.password?.type == "minLength" && <><br /><span style={{ color: "red" }}>password is too short</span></>}
                    {errors.password?.type == "maxLength" && <><br /><span style={{ color: "red" }}>password is too long</span></>}
                    <br /><br />
                    <TextField label="password check" variant="outlined" type="password"  fullWidth />
                    <br /><br />
                    <TextField label="user type" variant="outlined" {...register("userType")} fullWidth />
                    <br /><br />
                    <TextField label="user genre" variant="outlined" {...register("genre")} fullWidth />
                    <br />  <br />
                    <TextField label="user tel" variant="outlined" {...register("tel")} fullWidth />
                    <br />  <br />
                    <TextField label="user location" variant="outlined" {...register("location")} fullWidth />
                    <br />  <br />
                    <hr />
                    <ButtonGroup variant="contained" fullWidth>
                        <Button type="submit" color="primary" >register</Button>
                        <Button color="error" onClick={() => { navigate("/") }}>cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}

/*
    private int id;
    private String name; min=3 max=10 required
    private String email; min=5 max=10 required
    private String password; min=5 max 15 required
    private UserType userType; list
    private String tel; min=5 max=10
    private String location; min=5 max=10
    private String genre; min=5 max=10
*/