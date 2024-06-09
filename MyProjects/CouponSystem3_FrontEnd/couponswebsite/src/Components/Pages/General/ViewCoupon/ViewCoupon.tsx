import { useNavigate, useParams } from "react-router-dom";
import "./ViewCoupon.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import axios from "axios";
import { Button, ButtonGroup } from "@mui/material";
import { couponStore } from "../../../../Redux/store";
import  DeleteIcon  from "@mui/icons-material/Delete";
import UpdateIcon from '@mui/icons-material/Update';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';

export function ViewCoupon(): JSX.Element {
    const params = useParams();
    const IMAGE_WIDTH=200;
    const navigate = useNavigate();
    const [coupon,setCoupon] = useState<Coupon>();

    // Determine which client Type is logged on, and display relevant 'Menu' accordingly

    const [isAdmin,setAdmin] = useState(false);
    const [isCompany,setCompany] = useState(false);
    const [isCustomer,setCustomer] = useState(false);

    couponStore.subscribe(()=>{
        setAdmin(couponStore.getState().auth.clientType==="Administrator");
        setCompany(couponStore.getState().auth.clientType==="Company");
        setCustomer(couponStore.getState().auth.clientType==="Customer");
    });
    
    const guestView = ()=>{
        return (
            <>
                <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <img src={coupon?.image} width={IMAGE_WIDTH} />
                    </div>
                    <div className="Grid-Child">
                        <h1>{coupon?.title}</h1>
                        <h3>{coupon?.description}</h3><br/>
                        Amount Available: {coupon?.amount}<br/>
                        Valid Until: {coupon?.end_date}<br/>
                        Only {coupon?.price} (NIS)<br/>
                    </div>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="primary" startIcon={<LoginIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Login</Button>
                        <Button variant="contained" color="success" startIcon={<HowToRegIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Register</Button>
                    </ButtonGroup>
                </div>
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
        axios.get(`http://localhost:8080/Guest/GetOneCoupon/${params.couponID}`).then(res=>{
            //console.log(res.data);
            setCoupon(res.data);
        })
    },[])

    return (
        <div className="ViewCoupon Box">
            {guestView()}
            {isAdmin && adminView()}
            {isCompany && companyView()}
            {isCustomer && customerView()}
        </div>
    );
}
