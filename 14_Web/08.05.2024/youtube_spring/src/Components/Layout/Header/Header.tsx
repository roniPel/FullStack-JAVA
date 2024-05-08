import "./Header.css";
import uriImage from "./../../../Assets/uri.png"
import { NavLink } from "react-router-dom";

export function Header(): JSX.Element {
    return (
        <div className="Header">
            <div>
            <img src={uriImage}/>
            </div>
            <div>
			<h1>Class 169</h1>
            <h2>the best that lecturer can get</h2>
            </div>
            <div className="login">
                <input type="text" placeholder="user name"/>
                <input type="password" placeholder="user pass"/>
                <input type="submit" value="login"/>
                <input type="button" value="register"/>
                <NavLink to="/register">New User</NavLink><br/>
            </div>
        </div>
    );
}
