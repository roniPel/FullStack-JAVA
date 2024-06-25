import { NavLink } from "react-router-dom";
import "./Menu.css";
import { Typography } from "@mui/material";
import { useState } from "react";
import { couponStore } from "../../../Redux/store";
import { ClientType } from "../../../Models/ClientType";

export function Menu(): JSX.Element {
    // Determine which client Type is logged on, and display relevant 'Menu' accordingly

    const [isAdmin,setAdmin] = useState(false);
    const [isCompany,setCompany] = useState(false);
    const [isCustomer,setCustomer] = useState(false);

    couponStore.subscribe(()=>{
        setAdmin(couponStore.getState().auth.clientType===ClientType.Administrator);
        setCompany(couponStore.getState().auth.clientType===ClientType.Company);
        setCustomer(couponStore.getState().auth.clientType===ClientType.Customer);
    });
    
    const guestMenu = ()=>{
        return (
            <>
                <br/><Typography variant="h4" className="HeadLine">Menu</Typography><br/>
                <hr/>
                <NavLink to="/">Welcome</NavLink><br/>
                <NavLink to="/aboutus">About Us</NavLink><br/>
            </>
        )
    }

    //Todo - Change the 'to' in NavLinks for 'getOneCoupon' (so it won't be the same as admin) + update 'MainRoutes'
    const adminMenu = ()=>{
        return (
            <>
                <NavLink to="/adminHome">Admin Home</NavLink><br/>
                <NavLink to="/addCompany">Add Company</NavLink><br/>
                <NavLink to="/register">Add Customer</NavLink><br/>
                <NavLink to="/allCompanies">All Companies</NavLink><br/>
                <NavLink to="/allCustomers">All Customers</NavLink><br/>
                <hr/>
            </>
        )
    }

    const companyMenu = ()=>{
        return (
            <>
                <NavLink to="/companyHome">Company Home</NavLink><br/>
                <NavLink to="/">All Coupons</NavLink><br/>
                <NavLink to="/addCoupon">Add Coupon</NavLink><br/>
                <NavLink to="/companyCoupons">My Coupons</NavLink><br/>
                <NavLink to="/compCoupByCat">My Coupons by Category</NavLink><br/>
                <NavLink to="/compCoupByPrice">My Coupons by Max Price</NavLink><br/>
                <hr/>
            </>
        )
    }

    const customerMenu = ()=>{
        return (
            <>
                <NavLink to="/customerHome">Customer Home</NavLink><br/>
                <NavLink to="/">All Coupons</NavLink><br/>
                <NavLink to="/customerCoupons">My Coupons</NavLink><br/>
                <NavLink to="/custCoupByCat">My Coupons by Category</NavLink><br/>
                <NavLink to="/">My Coupons by Max Price</NavLink><br/>
                <hr/>
            </>
        )
    }

    return (
        <div className="Menu">
			{guestMenu()}
            <hr/>
            {isAdmin && adminMenu()}
            {isCompany && companyMenu()}
            {isCustomer && customerMenu()}
        </div>
    );
}
