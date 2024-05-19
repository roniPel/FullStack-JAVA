import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import "./Logon.css";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { Credentials } from "../../../Models/Credentials";

export function Logon(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Credentials>();
    const onSubmit: SubmitHandler<Credentials> = (data) => {
        console.log(data)
        //check that the passwords are the same , if not, do not countinue
    }
        //todo, move to axios :)
    return (
        <div className="Login">
            <div className="Box" style={{ width: "40%" }}>
                <Typography variant="h4" className="HeadLine">User Login</Typography>
                <hr /><br/>
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form onSubmit={handleSubmit(onSubmit)}>
                    <TextField label="user email" variant="outlined" {
                        ...register("userEmail", { required: true })} fullWidth />
                    {errors.userEmail && <span style={{ color: "red" }}>Email is required</span>}
                    <br /><br />
                    <TextField label="user password" type="password" variant="outlined" {
                        ...register("userPassword", { required: true})} fullWidth />
                    <br/><br/>
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
