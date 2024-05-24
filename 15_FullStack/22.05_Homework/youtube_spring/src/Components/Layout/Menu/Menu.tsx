import { NavLink } from "react-router-dom";
import "./Menu.css";
import { useState } from "react";
import { youtube } from "../../../redux/store";

export function Menu(): JSX.Element {
    //const [totalSongs,setTotal] = useState(youtube.getState().songs.allSongs.length); 
    //this is no good, what will happend if you will add songs, the compoent will not render
    
    //the correct way is to listen to state changes :)
    const [totalSongs,setTotal] = useState(0);
    const [isAdmin,setAdmin] = useState(false);

    youtube.subscribe(()=>{
        setTotal(youtube.getState().songs.allSongs.length);
        setAdmin(youtube.getState().auth.userType==="ADMIN");
    });

    const simpleMenu = ()=>{
        return (
            <>
                <h1>Menu</h1><hr/>
                <NavLink to="/">Song List</NavLink><br/>
                <NavLink to="/addSong">Add Song</NavLink><br/>
                <NavLink to="/remove">Remove Song</NavLink><br/>
                <hr/>
                <NavLink to="/user">Song by User</NavLink>
            </>
        )
    }

    const adminMenu = ()=>{
        return (
            <>
                <NavLink to="/">User List</NavLink><br/>
                <NavLink to="/">Find User</NavLink><br/>
                <NavLink to="/">Delete User</NavLink><br/>
                <hr/>
            </>
        )
    }
    return (
        <div className="Menu">
			{simpleMenu()}
            <hr/>
            {isAdmin && adminMenu()}
            Total songs: {totalSongs}
        </div>
    );
}
