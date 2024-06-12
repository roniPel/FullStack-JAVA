import { useNavigate } from "react-router-dom";
import "./AddCompany.css";
import { useEffect } from "react";
import { ClientType } from "../../../../Models/ClientType";
import { couponStore } from "../../../../Redux/store";
import notify from "../../../../Utilities/notify";

export function AddCompany(): JSX.Element {
    const navigate = useNavigate();

    useEffect(()=>{
        // Check if user has viewing permissions
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);
    return (
        <div className="AddCompany">
			
        </div>
    );
}
