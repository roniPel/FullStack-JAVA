import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import "./Register.css";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { Credentials } from "../../../Models/Credentials";

export function Register(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Credentials>();

    const onSubmit: SubmitHandler<Credentials> = (data) => {
        console.log(data)
        //check that the passwords are the same , if not, do not countinue

        //todo, move to axios :)
    }

    return (
        <div className="Register">
            <div className="Box" style={{ width: "40%" }}>
                <Typography variant="h4" className="HeadLine">User Register</Typography>
                <hr />
                {/* <input type="text" placeholder="user name..." onChange={(args)=>setEmail(args.target.value)}/><br/><br/> */}
                <form  onSubmit={handleSubmit(onSubmit)}>
                    <TextField label="user name" variant="outlined" {...register("userName", { required: true })} fullWidth />
                    <br /><br />
                    <TextField label="user email" variant="outlined" {
                        ...register("userEmail", { required: true })} fullWidth />
                    <br />{errors.userEmail && <span style={{ color: "red" }}>Email is required</span>}
                    <br /><br />
                    <TextField label="user password" type="password" variant="outlined" {
                        ...register("userPassword", { required: true, minLength: 5, maxLength: 10 })} fullWidth />
                    {errors.userPassword?.type == "required" && <><br /><span style={{ color: "red" }}>password is required</span></>}
                    {errors.userPassword?.type == "minLength" && <><br /><span style={{ color: "red" }}>password is too short</span></>}
                    {errors.userPassword?.type == "maxLength" && <><br /><span style={{ color: "red" }}>password is too long</span></>}
                    <br /><br />
                    <TextField label="password check" variant="outlined" type="password" fullWidth />
                    <br /><br />
                    <TextField label="user type" variant="outlined" {...register("clientType")} fullWidth />
                    <br /><br />
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
