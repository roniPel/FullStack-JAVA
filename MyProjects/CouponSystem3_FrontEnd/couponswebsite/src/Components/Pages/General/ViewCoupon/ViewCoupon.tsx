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
import { getOneCouponAction } from "../../../../Redux/guestReducer";

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
        getCoupon();
        // Check whether coupon is in customer/company list (according to logged on client type)
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

    function getCoupon(){
        // check if we have coupon in redux
        if(couponStore.getState().customer.coupon.id == parseInt(params.couponID as string)){
            //console.log("Get from Store");
            setCoupon(couponStore.getState().customer.coupon);
        } else {
            //console.log("Get from Backend");
            // get coupon data from backend
            axios.get(`http://localhost:8080/Guest/GetOneCoupon/${params.couponID}`).then(res=>{
            setCoupon(res.data);
            couponStore.dispatch(getOneCouponAction(res.data));
            }).catch((err)=>{
                console.log(err);
                notify.error("There was a problem getting the requested data.");
            });
        }
    }

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
                    {/* // If user is a guest */}
                    {!isLogged && <ButtonGroup variant="contained" fullWidth >
                        <Button variant="contained" color="primary" startIcon={<LoginIcon/>} onClick={() => 
                            { navigate(`/login`) }}>Login</Button>
                        <Button variant="contained" color="success" startIcon={<HowToRegIcon/>} onClick={() => 
                            { navigate(`/register`) }}>Register</Button>
                    </ButtonGroup>}
                    {/* // If user is Admin  -or- a Customer and the coupon is not in customer Coupon list */}
                    {(isAdmin || (isCustomer && !inCustomerList) ) &&<Button variant="contained" color="success" 
                    startIcon={<AddShoppingCartIcon/>} onClick={() => { navigate(`/purchase/${coupon?.id}`) }}>Buy Now!</Button>}
                    {/* // If user is Admin  -or- a Company and the coupon is in company Coupon list */}
                    {(isAdmin || (isCompany && inCompanyList) ) && <ButtonGroup variant="contained" fullWidth>
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} onClick={() => 
                            { navigate(`/deleteCoup/${coupon?.id}`) }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} onClick={() => 
                            { navigate(`/updateCoup/${coupon?.id}`) }}>Update</Button>
                    </ButtonGroup>}
                </div>
            </div>
        </div>
    );
}
