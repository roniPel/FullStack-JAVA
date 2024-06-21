import { useNavigate } from "react-router-dom";
import "./AddCompany.css";
import { useEffect } from "react";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { SubmitHandler, useForm } from "react-hook-form";
import { Company } from "../../../../Models/Company";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { addCompanyAction } from "../../../../Redux/adminReducer";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import AddIcon from '@mui/icons-material/Add';

export function AddCompany(): JSX.Element {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: {errors} } = useForm<Company>();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);

    const onSubmit: SubmitHandler<Company> = (data) => {
        //console.log(data);
        axiosJWT.post(`http://localhost:8080/Admin/AddCompany`,data)
        .then((res)=> {
            couponStore.dispatch(addCompanyAction(data));
            notify.success("The company was created successfully.");
            navigate("/adminHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem creating the company.");
        })
    }

    return (
        <div className="AddCompany Box" style={{ width: "40%" }}>
			<div className="Grid-Parent">
                <div className="Grid-Child">
                    <Typography variant="h4" className="HeadLine">New Company</Typography>
                    <hr /><br/>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <TextField type="text" label="Company Name" fullWidth {...register("name",{required:true})} />
                        {errors.name?.type == "required" && 
                        <><br/><span style={{ color: "red" }}>Name is required</span></>
                        }
                        <br/><br/>
                        <TextField type="text" label="Company Email" variant="outlined" fullWidth {...register("email",{required:true})} />
                        <br/><br/>
                        <TextField type="password" label="Company Password" variant="outlined" fullWidth {...register("password",{required:true})} />
                        <br/><br/>
                        <TextField label="password check" variant="outlined" type="password" fullWidth />
                        <br /><br />
                        {/* Todo - add a check that passwords are the same */}
                        <ButtonGroup variant="contained" fullWidth>
                            <Button type="submit" variant="contained" color="primary" startIcon={<AddIcon/>} >Add</Button>
                            <Button variant="contained" color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/adminHome") }}>Cancel</Button>
                        </ButtonGroup>
                    </form>
                </div>
                <div className="Grid-Child">
                    
                </div>
            </div>
        </div>
    );
}
