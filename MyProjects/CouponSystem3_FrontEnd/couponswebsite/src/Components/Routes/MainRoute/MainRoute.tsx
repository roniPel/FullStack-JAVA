import { Route, Routes } from "react-router-dom";
import "./MainRoute.css";
import { Page404 } from "../../Pages/General/Page404/Page404";
import { Register } from "../../Pages/General/Register/Register";
import { Welcome } from "../../Pages/General/Welcome/Welcome";
import { AboutUs } from "../../Pages/General/AboutUs/AboutUs";
import { Logon } from "../../Pages/General/Logon/Logon";
import { ViewCoupon } from "../../Pages/General/ViewCoupon/ViewCoupon";
import { useState } from "react";
import { couponStore } from "../../../Redux/store";
import { ViewCompany } from "../../Pages/Administrator/ViewCompany/ViewCompany";
import { UpdateCompany } from "../../Pages/Administrator/UpdateCompany/UpdateCompany";
import { DeleteCompany } from "../../Pages/Administrator/DeleteCompany/DeleteCompany";
import { AdminHome } from "../../Pages/Administrator/AdminHome/AdminHome";
import { AllCompanies } from "../../Pages/Administrator/AllCompanies/AllCompanies";

export function MainRoute(): JSX.Element {

    // Determine which client Type is logged on, and display relevant 'Route' accordingly

    const [isAdmin,setAdmin] = useState(false);
    const [isCompany,setCompany] = useState(false);
    const [isCustomer,setCustomer] = useState(false);

    couponStore.subscribe(()=>{
        setAdmin(couponStore.getState().auth.clientType==="Administrator");
        setCompany(couponStore.getState().auth.clientType==="Company");
        setCustomer(couponStore.getState().auth.clientType==="Customer");
    });

    const simpleRoute = ()=>{
        return (
            <>
                <Route path="/" element ={<Welcome/>}/>
                <Route path="/login" element={<Logon/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/coupon/:couponID" element={<ViewCoupon/>}/>
                <Route path="/aboutus" element={<AboutUs/>}/>
            </>
        )
    }

    const adminRoute = ()=>{
        return (
            <>
                <Route path="/adminHome" element ={<AdminHome/>}/>
                <Route path="/allCompanies" element ={<AllCompanies/>}/>
                <Route path="/company/:companyID" element ={<ViewCompany/>}/>
                <Route path="/deleteComp/:companyID" element ={<DeleteCompany/>}/>
                <Route path="/updateComp/:companyID" element ={<UpdateCompany/>}/>
            </>
        )
    }

    const companyRoute = ()=>{
        return (
            <>
                <Route path="/adminHome" element ={<Welcome/>}/>
            </>
        )
    }

    const customerRoute = ()=>{
        return (
            <>
                <Route path="/adminHome" element ={<Welcome/>}/>
            </>
        )
    }
    
    return (
        <div className="MainRoute">
			<Routes>
                {simpleRoute()}
                {isAdmin && adminRoute()}
                {isCompany && companyRoute()}
                {isCustomer && customerRoute()}
                <Route path="*" element ={<Page404/>}/>
            </Routes>
        </div>
    );
}
