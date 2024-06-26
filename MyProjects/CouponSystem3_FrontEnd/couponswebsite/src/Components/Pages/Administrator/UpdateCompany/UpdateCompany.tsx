import { useNavigate, useParams } from "react-router-dom";
import "./UpdateCompany.css";
import { Component, useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Company } from "../../../../Models/Company";
import { SubmitHandler, useForm } from "react-hook-form";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getOneCompanyAction, updateCompanyAction } from "../../../../Redux/adminReducer";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import CancelIcon from '@mui/icons-material/Cancel';
import UpdateIcon from '@mui/icons-material/Update';

export function UpdateCompany(): JSX.Element {
    const navigate = useNavigate();
    const [company, setCompany] = useState<Company>();
    const params = useParams();

    const { register, handleSubmit, formState: {errors} } = useForm<Company>();

    function getCompFromDB(){
        // get company data from backend
        axiosJWT.get(`http://localhost:8080/Admin/GetOneCompany/${params.companyID}`).then(res=>{
            setCompany(res.data);
            couponStore.dispatch(getOneCompanyAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
    }

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        // check if we have company in redux
        if(couponStore.getState().admin.company.id == parseInt(params.companyID as string)){
            //console.log("Get from Store");
            setCompany(couponStore.getState().admin.company);
        } else {
            // Get company from BackEnd
            //console.log("Get from Backend");
            getCompFromDB();
        }
    },[]);

    const onSubmit: SubmitHandler<Company> = (data) => {
        data.id = parseInt(params.companyID as string);
        data.name = company?.name as string;
        //console.log(data);
        axiosJWT.put(`http://localhost:8080/Admin/UpdateCompany`,data)
        .then((res)=> {
            couponStore.dispatch(updateCompanyAction(data));
            notify.success("The company was updated successfully.");
            navigate("/adminHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem updating the company.");
        })
    }

    return (
        <div>
            <Typography variant="h4" className="HeadLine">Update Company</Typography>
            <hr/>
            <div className="UpdateCompany">
                <div className="Box" style={{ width: "40%" }}>
                    <div className="Grid-Parent">
                        <div className="Grid-Child">
                            <form onSubmit={handleSubmit(onSubmit)}>
                                Company Name: <input type="text" placeholder="Company Name" value={company?.name}  {...register("name")} />
                                {errors.name?.type == "required" && 
                                <><br/><span style={{ color: "red" }}>Name can't be edited</span></>
                                }
                                <br/><br/>
                                Company Email: <input required type="text" placeholder="Company Email" value={company?.email}  {...register("email",{required:true})} />
                                <br/><br/>
                                Company Password: <input required type="password" placeholder="Company Password" defaultValue={company?.password}  {...register("password",{required:true})} />
                                <br/><br/>
                                <ButtonGroup variant="contained" fullWidth>
                                    <Button type="submit" variant="contained" color="primary" startIcon={<UpdateIcon/>} >Update</Button>
                                    <Button variant="contained" color="error" startIcon={<CancelIcon/>} onClick={() => { navigate("/adminHome") }}>Cancel</Button>
                                </ButtonGroup>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
