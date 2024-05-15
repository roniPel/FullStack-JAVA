import "./Header.css";
import logo from "../../../assests/uri.png";
import { useNavigate } from "react-router-dom";

export function Header(): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="Header">
            <div>
                <img src={logo} width={200}/>
            </div>
            <div>
			<h1>Class 169</h1>
            <h2>the best that lecturer can get</h2>
            </div>
            <div className="login">
                Hello Guest <br/>
                <input type="submit" value="login"/>
                <input type="button" value="register" onClick={()=>{
                    navigate("/register");
                }}/>
            </div>
        </div>
    );
}
