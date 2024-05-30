import { jwtDecode } from "jwt-decode";
import { couponStore } from "../Redux/store";
import { loginAction } from "../Redux/authReducer";

type jwtData = {
    "userType": string,
    "userName": string,
    "sub": string,
    "iat": number,
    "exp": number
}

export const checkData = () => {
    //check if the redux is not updated, and check if we can update it from the session storage
    if (couponStore.getState().auth.token.length < 10) {
        //try to load it from the session storage
        try {
            const JWT = localStorage.getItem("jwt")!.split(" ")[1];
            const decoded_jwt = jwtDecode<jwtData>(JWT);
            console.log(decoded_jwt);
            let myAuth = {
                name: decoded_jwt.userName,
                id: decoded_jwt.sub,
                token: localStorage.getItem("jwt")!,
                userType: decoded_jwt.userType,
                isLogged: true,
                rememberMe: true,
            };
            couponStore.dispatch(loginAction(myAuth))
        } catch {
            return;
        }

    }
}