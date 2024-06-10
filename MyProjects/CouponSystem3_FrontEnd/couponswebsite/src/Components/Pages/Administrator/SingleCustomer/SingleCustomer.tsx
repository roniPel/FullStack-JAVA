import { useNavigate } from "react-router-dom";
import "./SingleCustomer.css";
import { useEffect } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";

export function SingleCustomer(): JSX.Element {
    const navigate = useNavigate();
    // useEffect(()=>{
    //     if (couponStore.getState().auth.clientType!==ClientType.Administrator){
    //         navigate("/login");
    //         notify.error("You are not allowed!!!");
    //     }
    // },[]);
    return (
        <div className="SingleCustomer">
			
        </div>
    );
}
