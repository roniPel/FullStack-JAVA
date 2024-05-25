import axios from "axios";
import { youtube } from "../redux/store";
import { loginAction, updateTokenAction } from "../redux/authReducer";

const axiosJWT = axios.create();

axiosJWT.interceptors.request.use(
    request=>{
        request.headers.Authorization = `Bearer ${youtube.getState().auth.token}`;
        return request;
    }
)

axiosJWT.interceptors.response.use(
    response=>{
        console.log(response.headers.authorization);
        youtube.dispatch(updateTokenAction(response.headers.authorization));      
        sessionStorage.setItem("jwt",response.headers.authorization); 
        return response;
    }
)

export default axiosJWT;