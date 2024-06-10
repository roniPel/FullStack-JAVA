import { useNavigate } from "react-router-dom";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import "./AllCompanies.css";
import { useEffect } from "react";
import notify from "../../../../Utilities/notify";

export function AllCompanies(): JSX.Element {
    const navigate = useNavigate();
    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);
    
    return (
        <div className="AllCompanies">
			
        </div>
    );
}
