import "./Menu.css";
import { NavLink } from "react-router-dom";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
			<h1>Menu</h1>
            <NavLink to="/">Metting list </NavLink>| 
            <NavLink to="/meeting/add"> Add Metting </NavLink>| 
            <NavLink to="/groups"> Group List</NavLink>
        </div>
    );
}
