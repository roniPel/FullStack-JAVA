import "./Register.css";

export function Register(): JSX.Element {
    return (
        <div className="Register">
            <div>
			<h1>Register</h1><hr/>
                <input type="text" placeholder="user name"/>
                <input type="text" placeholder="user email"/>
                <input type="password" placeholder="user password"/>
                <input type="text" placeholder="Song Private? (t/f)"/>
                <input type="submit" value="register"/>
            </div>
        </div>
    );
}
