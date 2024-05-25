import { useEffect } from "react";
import "./UserList.css";
import { youtube } from "../../../redux/store";
import { useNavigate } from "react-router-dom";
import notify from "../../../util/notify";

export function UserList(): JSX.Element {
    const navigate = useNavigate();
    useEffect(()=>{
        if (youtube.getState().auth.userType!=="ADMIN"){
            navigate("/login");
            notify.error("You are not allowed!!!");
        }
    },[]);
    return (
        <div className="UserList">
			<h1>Show me the M O N E Y ! ! !</h1>
        </div>
    );
}