import { useNavigate, useParams } from "react-router-dom";
import "./ViewCompany.css";
import { useEffect, useState } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";
import { Button, ButtonGroup, Card, CardActionArea, CardActions, CardContent, Typography } from "@mui/material";
import  DeleteIcon  from "@mui/icons-material/Delete";
import UpdateIcon from '@mui/icons-material/Update';
import { checkData } from "../../../../Utilities/checkData";
import axiosJWT from "../../../../Utilities/axiosJWT";
import { Company } from "../../../../Models/Company";
import axios from "axios";
import { getOneCompanyAction } from "../../../../Redux/adminReducer";

export function ViewCompany(): JSX.Element {
    const params = useParams();
    const [company, setLocalCompany] = useState<Company>();
    const [companyList, setLocalCompList] = useState<Company[]>([]);
    const navigate = useNavigate();

    function getCompFromDB(){
        // console.log("Getting company data from Backend")
        // get company data from backend
        axiosJWT.get(`http://localhost:8080/Admin/GetOneCompany/${params.companyID}`).then(res=>{
            setLocalCompany(res.data);
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
        else {
            checkData();
            // // check if requested company exists in redux
            // if(couponStore.getState().admin.companies.length>0){
            //     console.log("From Redux: "+couponStore.getState().admin.companies);
            //     setLocalCompList(couponStore.getState().admin.companies);
            //     for(let index =0; index< 3; index++){
            //         if(companyList[index]?.id===parseInt(params.companyID as string)){
            //             setLocalCompany(companyList[index]);
            //         }
            //     }
            //     // If redux is not empty but requested company doesn't exist in it
            //     if(company?.email === ""){
            //         getCompFromDB();
            //     }
            // } else {    // If redux is empty
            //     getCompFromDB();
            // }
            getCompFromDB();
        }
    },[]);

    // Determine which client Type is logged on, and display relevant 'Menu' accordingly
    const [isAdmin,setAdmin] = useState(false);
    const [isCompany,setCompany] = useState(false);
    const [isCustomer,setCustomer] = useState(false);
    const [isLogged, setLogged] = useState(false);

    couponStore.subscribe(()=>{
        setLogged(couponStore.getState().auth.isLogged);
        setAdmin(couponStore.getState().auth.clientType===ClientType.Administrator);
        setCompany(couponStore.getState().auth.clientType===ClientType.Company);
        setCustomer(couponStore.getState().auth.clientType===ClientType.Customer);
    });
    
    return (
        <div className="ViewCompany Box">
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <Typography variant="h6" className="HeadLine">ID: {company?.id}</Typography>
                    <br/>
                    <Typography variant="h4" className="HeadLine">{company?.name}</Typography>
                    <br/>
                    <Typography variant="h6" className="HeadLine">{company?.email}</Typography>
                    <br/><br/>
                </div>
                <div className="Grid-Child">
                    <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/deleteComp/${company?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/updateComp/${company?.id}`) }}>Update</Button>
                    </ButtonGroup>
                </div>
            </div>
        </div>
    );
}
