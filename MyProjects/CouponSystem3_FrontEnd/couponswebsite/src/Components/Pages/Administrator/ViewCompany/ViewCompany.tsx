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

export function ViewCompany(): JSX.Element {
    const params = useParams();
    const [company,setLocalCompany] = useState<Company>();
    const navigate = useNavigate();
    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
        else {
            checkData();
            axios.get(`http://localhost:8080/Admin/GetOneCompany/${params.companyID}`).then(res=>{
            setLocalCompany(res.data);
        })
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
			{/* <Card sx={{ maxWidth: 345 }}>
                <CardActionArea>
                    <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                    {company?.name}
                    </Typography>
                    <Typography variant="h6" color="text.secondary">
                    Email: {company?.email}
                    </Typography >
                    </CardContent>
                </CardActionArea>
                <CardActions>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/deleteComp/${company?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/updateComp/${company?.id}`) }}>Update</Button>
                    </ButtonGroup>
                </CardActions>
            </Card> */}
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <h1>{company?.name}</h1>
                    <h3>{company?.email}</h3><br/>
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
