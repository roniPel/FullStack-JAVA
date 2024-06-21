import { useNavigate, useParams } from "react-router-dom";
import "./ViewCoupon.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import axios from "axios";
import { Button, ButtonGroup, Typography } from "@mui/material";
import { couponStore } from "../../../../Redux/store";
import  DeleteIcon  from "@mui/icons-material/Delete";
import UpdateIcon from '@mui/icons-material/Update';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import { ClientType } from "../../../../Models/ClientType";
import { checkData } from "../../../../Utilities/checkData";

export function ViewCoupon(): JSX.Element {
    const params = useParams();
    const IMAGE_WIDTH=200;
    const navigate = useNavigate();
    const [coupon,setCoupon] = useState<Coupon>();

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
    
    const guestView = ()=>{
        return (
            <>
                
            </>
        )
    }

    const companyView = ()=>{
        return (
            <>
                <ButtonGroup variant="contained" fullWidth>
                    <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Delete</Button>
                    <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/update/${coupon?.id}`) }}>Update</Button>
                </ButtonGroup>
            </>
        )
    }

    const customerView = ()=>{
        return (
            <>
                <ButtonGroup variant="contained" fullWidth>
                    <Button variant="contained" color="success" startIcon={<AddShoppingCartIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Buy Now!</Button>
                </ButtonGroup> 
            </>
        )
    }

    const adminView = ()=>{
        return (
            <>
                {customerView()}
                {companyView()}
            </>
        )
    }

    useEffect(()=>{
        checkData();
        axios.get(`http://localhost:8080/Guest/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
        })
    },[])

    return (
        <div className="ViewCoupon Box">
            {isAdmin && adminView()}
            {isCompany && companyView()}
            {isCustomer && customerView()}
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <img src={coupon?.image} width={IMAGE_WIDTH} />
                </div>
                <div className="Grid-Child">
                <Typography gutterBottom variant="h5" component="div">
                    {coupon?.title}
                    </Typography>
                    <Typography variant="h6" color="text.secondary">
                    {coupon?.description}
                    </Typography >
                    <Typography variant="body1" color="text.secondary">
                    Valid Until: {coupon?.end_date}<br/>
                    Only {coupon?.price} (NIS)<br/>
                    </Typography>
                </div>
                <br/>
                <div className="Grid-Child">
                    {!isLogged && <ButtonGroup variant="contained" fullWidth >
                        <Button variant="contained" color="primary" startIcon={<LoginIcon/>} onClick={() => { navigate(`/login`) }}>Login</Button>
                        <Button variant="contained" color="success" startIcon={<HowToRegIcon/>} onClick={() => { navigate(`/register`) }}>Register</Button>
                    </ButtonGroup>}
                    {(isCustomer || isAdmin) && <Button variant="contained" color="success" startIcon={<AddShoppingCartIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Buy Now!</Button>}
                    {(isCompany || isAdmin) && <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/update/${coupon?.id}`) }}>Update</Button>
                    </ButtonGroup>}
                </div>
            </div>
        </div>
    );
}
