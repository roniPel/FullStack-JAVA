import { useNavigate, useParams } from "react-router-dom";
import "./ViewCoupon.css";
import { useEffect, useState } from "react";
import { Coupon } from "../../../../Models/Coupon";
import axios from "axios";
import { Button, ButtonGroup, List, Typography } from "@mui/material";
import { couponStore } from "../../../../Redux/store";
import  DeleteIcon  from "@mui/icons-material/Delete";
import UpdateIcon from '@mui/icons-material/Update';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import LoginIcon from '@mui/icons-material/Login';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import { ClientType } from "../../../../Models/ClientType";
import { checkData } from "../../../../Utilities/checkData";
import notify from "../../../../Utilities/notify";
import { getCompanyCouponsFromDB } from "../../../../Utilities/getCompanyCouponsFromDB";
import { getCustomerCouponsFromDB } from "../../../../Utilities/getCustomerCouponsFromDB";

export function ViewCoupon(): JSX.Element {
    const params = useParams();
    const IMAGE_WIDTH=200;
    const navigate = useNavigate();
    const [coupon,setCoupon] = useState<Coupon>();
    const [inCustomerList, setInCustomerList] = useState(false);
    const [inCompanyList, setInCompanyList] = useState(false);

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

    function checkCustomerList(){
        if(couponStore.getState().customer.customerDetails.id === -1){
            getCustomerCouponsFromDB();
        }
        let customerCouponList = couponStore.getState().customer.customerCoupons as Coupon[];
        // Check whether coupon exists in the list
        customerCouponList.forEach((coup)=>{
            if(coup.id === parseInt(params.couponID as string)){
                setInCustomerList(true);
            }
        })
        console.log("In company list? "+inCompanyList);
    }

    function checkCompanyList(){
        if(couponStore.getState().company.companyDetails.id === -1){
            getCompanyCouponsFromDB();
        }
        
        let companyCouponList = couponStore.getState().company.companyCoupons as Coupon[];
        // Check whether coupon exists in the list
        companyCouponList.forEach((coup)=>{
            if(coup.id === parseInt(params.couponID as string)){
                setInCompanyList(true);
            }
        })

    }

    useEffect(()=>{
        checkData();
        // Update logged status for different user types: 
        setLogged(couponStore.getState().auth.isLogged);
        setAdmin(couponStore.getState().auth.clientType===ClientType.Administrator);
        setCompany(couponStore.getState().auth.clientType===ClientType.Company);
        setCustomer(couponStore.getState().auth.clientType===ClientType.Customer);
        // Get coupon info from DB
        axios.get(`http://localhost:8080/Guest/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
        }).catch((err)=>{
            console.log(err);
            notify.error("There was a problem getting the requested data.");
        });
        switch(couponStore.getState().auth.clientType){
            case (ClientType.Company as string):
                // Company coupon check
                checkCompanyList();
                break;
            case (ClientType.Customer as string):
                // Customer coupon check
                checkCustomerList();
                break;
        }

    },[])

    return (
        <div className="ViewCoupon Box">
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <img src={coupon?.image} width={IMAGE_WIDTH} />
                </div>
                <div className="Grid-Child">
                <Typography gutterBottom variant="h5" component="div">{coupon?.title}</Typography>
                    <Typography variant="h6" color="text.secondary">{coupon?.description}</Typography >
                    <Typography variant="body2" color="text.secondary">Category: {coupon?.category}</Typography ><br/>
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
                    {(isAdmin || (isCustomer && !inCustomerList) ) &&<Button variant="contained" color="success" startIcon={<AddShoppingCartIcon/>} onClick={() => { navigate(`/purchase/${coupon?.id}`) }}>Buy Now!</Button>}
                    {(isAdmin || (isCompany && inCompanyList) ) && <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => { navigate(`/delete/${coupon?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => { navigate(`/update/${coupon?.id}`) }}>Update</Button>
                    </ButtonGroup>}
                </div>
            </div>
        </div>
    );
}
