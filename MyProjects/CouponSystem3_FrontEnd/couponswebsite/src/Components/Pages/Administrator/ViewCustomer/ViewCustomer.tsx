import { useNavigate } from "react-router-dom";
import "./ViewCustomer.css";
import { useEffect } from "react";
import { couponStore } from "../../../../Redux/store";
import { ClientType } from "../../../../Models/ClientType";
import notify from "../../../../Utilities/notify";

export function ViewCustomer(): JSX.Element {
    const navigate = useNavigate();
    useEffect(()=>{
        if (couponStore.getState().auth.clientType!==ClientType.Administrator){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);
    return (
        <div className="ViewCustomer">
			
        </div>
    );
}
