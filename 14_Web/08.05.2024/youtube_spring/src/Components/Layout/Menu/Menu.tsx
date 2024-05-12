import { NavLink } from "react-router-dom";
import "./Menu.css";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
			<h1>Menu</h1><hr/>
            <NavLink to="/">Song List</NavLink><br/>
            <NavLink to="/addSong">Add Song</NavLink><br/>
            <NavLink to="/removeSong">Remove Song</NavLink><br/>
            <hr/>
            <NavLink to="/user">Song by User</NavLink>
        </div>
    );
}
