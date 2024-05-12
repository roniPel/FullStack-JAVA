import "./Header.css";
import logo from "../../../Assets/Cards.png"
import { useNavigate } from "react-router-dom";

export function Header(): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="Header">
            <div>
                <img src={logo} width={100}/>
            </div>
            <div>
			<h1>Class 169</h1>
            <h2>the best that lecturer can get</h2>
            </div>
            <div className="login">
                <input type="text" placeholder="user name"/>
                <input type="password" placeholder="user pass"/>
                <input type="submit" value="login"/>
                <input type="button" value="register" onClick={()=>{
                        //navigate to song list
                        navigate("/register");                   
                    }}/>
            </div>
        </div>
    );
}
