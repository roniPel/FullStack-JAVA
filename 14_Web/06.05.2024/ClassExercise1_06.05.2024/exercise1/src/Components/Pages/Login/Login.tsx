import "./Login.css";

export function Login(): JSX.Element {
    return (
        <div className="Login">
			<br/>
            <h2>Login Page</h2>
            <br/>
            <input type="text" placeholder="Enter user name..."/><br/><br/>
            <input type="password" placeholder="Enter Password..."/><br/><br/>
            <input type="button" value="login"/>
        </div>
    );
}
