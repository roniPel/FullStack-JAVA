import { useNavigate, useParams } from "react-router-dom";
import "./UpdateCompany.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Company } from "../../../../Models/Company";
import { SubmitHandler, useForm } from "react-hook-form";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { getOneCompanyAction } from "../../../../Redux/adminReducer";
import { Button, ButtonGroup } from "@mui/material";
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

        // Get company from BackEnd

        getCompFromDB();
    },[]);

    const onSubmit: SubmitHandler<Company> = (data) => {
        data.id = parseInt(params.companyID as string);
        axiosJWT.put(`http://localhost:8080/Admin/UpdateCompany`,data)
        .then((res)=> {
            notify.success("The company was updated successfully.");
            navigate("/adminHome");
        })
        .catch((err)=> {
            console.log(err);
            notify.error("There was a problem updating the company.");
        })
    }

    return (
        <div className="UpdateCompany Box">
			<div className="Grid-Parent">
                <div className="Grid-Child">
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <h1>Update Company</h1>
                        <hr/>
                        Name: <input type="text" placeholder="Company Name" defaultValue={company?.name} {...register("name")} />
                        {/* {errors.name?.type == "required" && 
                        <><br/><span style={{ color: "red" }}>Name is required</span></>
                        } */}
                        <br/><br/>
                        Email: <input type="text" placeholder="Company Email" defaultValue={company?.email} {...register("email",{required:true})} />
                        <br/><br/>
                        Password: <input type="password" placeholder="Company Password" defaultValue={company?.password} {...register("password",{required:true})} />
                        <br/><br/>
                        <ButtonGroup variant="contained" fullWidth>
                            <Button type="submit" variant="contained" color="primary" startIcon={<UpdateIcon/>} >Update</Button>
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
