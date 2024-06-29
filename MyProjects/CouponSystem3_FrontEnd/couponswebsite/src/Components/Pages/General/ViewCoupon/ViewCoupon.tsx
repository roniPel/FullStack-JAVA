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
import notify from "../../../../Utilities/notify";
import { getCompanyCouponsFromDB } from "../../../../Utilities/getCompanyCouponsFromDB";
import { getCustomerCouponsFromDB } from "../../../../Utilities/getCustomerCouponsFromDB";
import { getOneCouponAction } from "../../../../Redux/guestReducer";
import { getOneCouponViaCompanyAction } from "../../../../Redux/companyReducer";
import { getOneCouponViaCustomerAction } from "../../../../Redux/customerReducer";

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
        // Get coupon info from redux or DB
        getCoupon();

    },[])

    function areIdsEqual(id1:number, id2:number) {
        if(id1 === id2){
            return true;
        }
        return false;
    }

    function getCoupon(){
        let paramId = parseInt(params.couponID as string);
        let storeId:number = -1;
        let couponList:Coupon[] = []; 
        switch(couponStore.getState().auth.clientType){
            case (ClientType.Company as string):
                // set id from redux
                // Todo - get data from store via 'useSelector' ? (another way?)
                storeId = (coupon !== undefined)? couponStore.getState().company.coupon.id:-1;
                if(areIdsEqual(storeId,paramId))    // If coupon exists in redux
                {
                    //console.log("Get coupon from redux");
                    // Update local coupon from redux
                    setCoupon(couponStore.getState().company.coupon);
                }
                else if(couponStore.getState().company.companyCoupons.length !== 0)     // If coupon exists in redux coupon list
                {
                    //console.log("Coupon exists in redux coupon list")
                    couponList = [...couponStore.getState().company.companyCoupons];
                    couponList.forEach((coup)=>{
                        if(coup.id === paramId){
                            setCoupon(coup)
                        }
                    })
                }
                else    // Coupon doesn't exists in redux at all - get from DB
                {
                    getCouponFromDB();
                }
                // update redux from local coupon
                couponStore.dispatch(getOneCouponViaCompanyAction(coupon as Coupon));
                // Company coupon check
                checkCompanyList();
                break;
            case (ClientType.Customer as string):
                // set id from redux
                // console.log("Inside 'customer' switch, coupon from store: "+couponStore.getState().customer.coupon);
                storeId = (couponStore.getState().company.coupon !== undefined)? couponStore.getState().company.coupon.id:-1;
                if(areIdsEqual(storeId,paramId))    // If coupon exists in redux
                {
                    // Update local coupon from redux
                    setCoupon(couponStore.getState().customer.coupon);
                }
                else if(couponStore.getState().customer.customerCoupons.length !== 0)   // If redux coupon list is not empty
                {
                    couponList = couponStore.getState().customer.customerCoupons;
                    couponList.forEach((coup)=>{
                        if(coup.id === paramId){
                            setCoupon(coup);
                        }
                    })
                }
                if (coupon === undefined)   // Coupon doesn't exists in redux at all - get from DB
                {
                    //console.log("Get coupon from DB");
                    getCouponFromDB();
                }
                // update redux from local coupon
                couponStore.dispatch(getOneCouponViaCustomerAction(coupon as Coupon));
                // Customer coupon check
                checkCustomerList();
                break;
            case (ClientType.Guest as string):
                // set id from redux
                storeId = couponStore.getState().guest.coupon.id;
                if(areIdsEqual(storeId,paramId)){
                    // Update local coupon from redux
                    setCoupon(couponStore.getState().guest.coupon);
                }
                else {
                    getCouponFromDB();
                }
                // update redux from local coupon
                couponStore.dispatch(getOneCouponAction(coupon as Coupon));
                break;
        }
    }

    function getCouponFromDB() {
        //console.log("Get from Backend");
        // get coupon data from backend
        axios.get(`http://localhost:8080/Guest/GetOneCoupon/${params.couponID}`).then(res=>{
            // Update local coupon with result
            setCoupon(res.data);
        })
        .catch((err)=>{
            console.log(err);
            notify.error("There was a problem getting the requested data.");
        });
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
                        <Button variant="contained" color="error" startIcon={<DeleteIcon/>} 
                            onClick={() => { 
                                couponStore.dispatch(getOneCouponViaCompanyAction(coupon as Coupon));
                                navigate(`/deleteCoup/${coupon?.id}`); 
                                }}>Delete</Button>
                        <Button variant="contained" color="primary" startIcon={<UpdateIcon/>} 
                            onClick={() => 
                            { navigate(`/updateCoup/${coupon?.id}`) }}>Update</Button>
                    </ButtonGroup>}
                </div>
            </div>
        </div>
    );
}
