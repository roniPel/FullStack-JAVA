import { NavLink } from "react-router-dom";
import "./Menu.css";
import { Typography } from "@mui/material";
import { useState } from "react";
import { couponStore } from "../../../Redux/store";

export function Menu(): JSX.Element {
    // Determine which client Type is logged on, and display relevant 'Menu' accordingly

    const [isAdmin,setAdmin] = useState(false);
    const [isCompany,setCompany] = useState(false);
    const [isCustomer,setCustomer] = useState(false);

    couponStore.subscribe(()=>{
        setAdmin(couponStore.getState().auth.clientType==="Administrator");
        setCompany(couponStore.getState().auth.clientType==="Company");
        setCustomer(couponStore.getState().auth.clientType==="Customer");
    });
    
    const simpleMenu = ()=>{
        return (
            <>
                <br/><Typography variant="h4" className="HeadLine">Menu</Typography><br/>
                <hr/>
                <NavLink to="/">Home</NavLink><br/>
                <NavLink to="/aboutus">About Us</NavLink><br/>
            </>
        )
    }

    //Todo - Change the 'to' in NavLinks for 'getOneCoupon' (so it won't be the same as admin) + update 'MainRoutes'
    const adminMenu = ()=>{
        return (
            <>
                <NavLink to="/userId">Admin Home</NavLink><br/>
                <NavLink to="/addCompany">Add Company</NavLink><br/>
                <NavLink to="/deleteCompany">Delete Company</NavLink><br/>
                <NavLink to="/deleteCustomer">Delete Customer</NavLink><br/>
                <NavLink to="/getAllCompanies">View All Companies</NavLink><br/>
                <NavLink to="/getAllCustomers">View All Customers</NavLink><br/>
                <NavLink to="/getOneCompany">View One Company</NavLink><br/>
                <NavLink to="/getOneCustomer">View One Customer</NavLink><br/>
                <NavLink to="/updateCompany">Update Company</NavLink><br/>
                <NavLink to="/updateCustomer">Update Customer</NavLink><br/>
                <hr/>
            </>
        )
    }

    const companyMenu = ()=>{
        return (
            <>
                <NavLink to="/userId">Company Home</NavLink><br/>
                <NavLink to="/">Add Coupon</NavLink><br/>
                <NavLink to="/">Delete Coupon</NavLink><br/>
                <NavLink to="/">View My Coupons</NavLink><br/>
                <NavLink to="/">View My Coupons by Category</NavLink><br/>
                <NavLink to="/">View My Coupons by Max Price</NavLink><br/>
                <NavLink to="/">View My Details</NavLink><br/>
                <NavLink to="/">View One Coupon</NavLink><br/>
                <NavLink to="/">Update Coupon</NavLink><br/>
                <hr/>
            </>
        )
    }

    const customerMenu = ()=>{
        return (
            <>
                <NavLink to="/userId">Customer Home</NavLink><br/>
                <NavLink to="/">View All Coupons</NavLink><br/>
                <NavLink to="/">View All Customers</NavLink><br/>
                <NavLink to="/">View My Coupons</NavLink><br/>
                <NavLink to="/">View My Coupons by Category</NavLink><br/>
                <NavLink to="/">View My Coupons by Max Price</NavLink><br/>
                <NavLink to="/">View My Details</NavLink><br/>
                <NavLink to="/">View One Coupon</NavLink><br/>
                <NavLink to="/">Purchase Customer</NavLink><br/>
                <hr/>
            </>
        )
    }

    return (
        <div className="Menu">
			{simpleMenu()}
            <hr/>
            {isAdmin && adminMenu()}
            {isCompany && companyMenu()}
            {isCustomer && customerMenu()}
        </div>
    );
}
