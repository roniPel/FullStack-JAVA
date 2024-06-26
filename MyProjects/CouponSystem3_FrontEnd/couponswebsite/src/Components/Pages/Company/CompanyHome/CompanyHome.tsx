import { useNavigate } from "react-router-dom";
import "./CompanyHome.css";
import { useEffect, useState } from "react";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";
import { TextField, Typography } from "@mui/material";
import { Company } from "../../../../Models/Company";
import { checkData } from "../../../../Utilities/checkData";
import axios from "axios";
import { getCompanyDetailsAction } from "../../../../Redux/companyReducer";
import axiosJWT from "../../../../Utilities/axiosJWT";

export function CompanyHome(): JSX.Element {
    const navigate = useNavigate();
    const [company, setLocalCompany] = useState<Company>();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Company){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        checkData();
        // check if requested company exists in redux
        if(couponStore.getState().company.companyDetails !== null){
            // If redux is not empty but requested company doesn't exist in it
            if(couponStore.getState().company.companyDetails.id === -1){
                getCompDetailsFromDB();
            } else {
                setLocalCompany(couponStore.getState().company.companyDetails);
            }
        } else {    // If redux is empty
            getCompDetailsFromDB();
        }
    },[]);

    function getCompDetailsFromDB(){
        //console.log("Getting data from Backend")
        // get data from backend
        axiosJWT.get(`http://localhost:8080/Company/GetCompanyDetails`).then(res=>{
            setLocalCompany(res.data);
            couponStore.dispatch(getCompanyDetailsAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
    }

    return (
        <div className="CompanyHome">
			<Typography variant="h4" className="HeadLine">{couponStore.getState().auth.name}'s Home</Typography>
            <hr/>
            <br/>
            <div className="Details Box" style={{ width: "40%" }}>
                <Typography variant="h5" className="HeadLine">My Details: </Typography>
                <hr/>
                <br/>
                <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <Typography variant="h4" className="HeadLine">{company?.name}</Typography>
                        <br/>
                        <Typography variant="h6" className="HeadLine">{company?.email}</Typography>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    );
}
