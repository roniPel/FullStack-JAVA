import axios from "axios";
import { couponStore } from "../Redux/store";
import { updateTokenAction } from "../Redux/authReducer";

//axiox JWT - conains interceptors to 'catch' requests and responses and add JWT tokens to their headers (for authentication)
const axiosJWT = axios.create();

axiosJWT.interceptors.request.use(
    request=>{
        request.headers.Authorization = `Bearer ${couponStore.getState().auth.token}`;
        return request;
    }
)

axiosJWT.interceptors.response.use(
    response=>{
        console.log(response.headers.authorization);
        couponStore.dispatch(updateTokenAction(response.headers.authorization));      
        sessionStorage.setItem("jwt",response.headers.authorization); 
        return response;
    }
)

export default axiosJWT;