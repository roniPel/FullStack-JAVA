import { Button, Form } from "react-bootstrap";
import ConfirmationModal from "../../ModalComponents/ConfirmationModal/ConfirmationModal";
import "./Register.css";
import { UserDetails } from "../../../model/UserDetails";
import { useState } from "react";
import axios from "axios";

export function Register(): JSX.Element {
    const [userDetails, setDetails] = useState<UserDetails>();
    return (
        <div className="Register">
            <h1>Register</h1>
            <div className="Box">
                <form>
                    User Type: 
                    <select>
                        <option value="ADMIN">Administrator</option>
                        <option value="COMPANY">Company</option>
                        <option value="CUUSTOMER">Customer</option>
                    </select><br /><br />
                    UserName <input type="text" placeholder="UserName..." required /><br /><br />
                    Password <input type="password" placeholder="Password..." required /><br /><br />
                    Email <input type="email" placeholder="Email..." required /><br /><br />
                    <input type="checkbox" />Private?
                    <br /><br />
                    <input type="submit" value="Submit" onClick={()=> {
                        // Save User details
                        let userDetails: UserDetails = new UserDetails(
                            100, "email@email.com", "password", "ADMIN"
                        );
                        console.log(userDetails);
                        // Send user details to backend
                        axios.post(`http://localhost:8080/Users/addUser`, userDetails);
                    }}/>
                    <input type="reset" value="Reset" />
                </form>
                
            </div>
        </div>
    );
}
