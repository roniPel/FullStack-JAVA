import { Button, Form } from "react-bootstrap";
import ConfirmationModal from "../../ModalComponents/ConfirmationModal/ConfirmationModal";
import "./Register.css";
import { UserDetails } from "../../../model/UserDetails";
import { useState } from "react";
import axios from "axios";

export function Register(): JSX.Element {
    const [userDetails,setDetails] = useState<UserDetails>();
    return (
        <div className="Register">
            <h1>Register</h1>
            <div className="Box">
                <Form>
                    <Form.Group className="userType" controlId="formBasicSelect">
                        <Form.Label>UserType </Form.Label>
                        <Form.Select>
                            <option>Select UserType</option>
                            <option value="1">Administrator</option>
                            <option value="2">Company</option>
                            <option value="3">Customer</option>
                        </Form.Select>
                    </Form.Group><br/>
                    <Form.Group className="email" controlId="formBasicEmail">
                        <Form.Label>Email address </Form.Label>
                        <Form.Control type="email" placeholder="Enter email" /><br/><br/>
                    </Form.Group>

                    <Form.Group className="password" controlId="formBasicPassword">
                        <Form.Label>Password </Form.Label>
                        <Form.Control type="password" placeholder="Password" /><br/><br/>
                    </Form.Group>
                    <Form.Group className="name" controlId="formBasicText">
                        <Form.Label>Name </Form.Label>
                        <Form.Control type="name" placeholder="Enter name" /><br/><br/>
                        <Form.Text className="text-muted">
                        </Form.Text>
                    </Form.Group>
                    <Form.Group className="private" controlId="formBasicCheckbox">
                        <Form.Check type="checkbox" label="Private?" /><br/><br/>
                    </Form.Group>
                    <Button variant="primary" type="submit" onClick={()=>{
                        // Save User details
                        let userDetails:UserDetails = new UserDetails(
                            1,"email@email.com","password","Administrator"
                        );

                        // Send user details to backend
                        axios.post(`http://localhost:8080/Users/`,userDetails);
                    }}>
                        Submit
                    </Button>
                </Form>
            </div>
        </div>
    );
}
