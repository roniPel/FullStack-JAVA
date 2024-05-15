import { useState } from "react";
import "./Register.css";

export function Register(): JSX.Element {
    const[] = useState
    return (
        <div className="Register">
			
            <div className="Box">
                <h1>Register</h1><hr/>
                <input type="text" placeholder="user name..."/><br/><br/>
                <input type="text" placeholder="user email..."/><br/><br/>
                <input type="password" placeholder="user password..."/><br/><br/>
                <input type="password" placeholder="password check..."/><br/><br/>
                <input type="text" placeholder="user type..."/><br/><br/>
                <input type="text" placeholder="user tel..."/><br/><br/>
                <input type="text" placeholder="user location..."/><br/><br/>
                <input type="text" placeholder="user genre..."/><br/>  <br/>              
                <hr/>
                <input type="button" value="register"/>
                <input type="button" value="cancel"/>
            </div>
        </div>
    );
}

/*
    private int id;
    private String name; min=3 max=10 required
    private String email; min=5 max=10 required
    private String password; min=5 max 15 required
    private UserType userType; list
    private String tel; min=5 max=10
    private String location; min=5 max=10
    private String genre; min=5 max=10
*/