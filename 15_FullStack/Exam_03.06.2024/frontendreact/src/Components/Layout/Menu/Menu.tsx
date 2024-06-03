import { NavLink } from "react-router-dom";
import "./Menu.css";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
			<NavLink to="/add">Add Task </NavLink>
            | 
            <NavLink to="/"> All Tasks </NavLink>
        </div>
    );
}
