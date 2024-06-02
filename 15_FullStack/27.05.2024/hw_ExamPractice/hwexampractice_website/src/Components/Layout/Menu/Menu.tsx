import { NavLink } from "react-router-dom";
import "./Menu.css";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
			<div className="Menu">
			<NavLink to="/add">Add Task </NavLink>
            | 
            <NavLink to="/all"> All Tasks </NavLink>
            | 
            <NavLink to="/completed"> Completed Tasks </NavLink>
            | 
            <NavLink to="/uncompleted"> Uncompleted Tasks </NavLink>
            | 
            <NavLink to="/byDate"> Task By Date </NavLink>
        </div>
        </div>
    );
}
