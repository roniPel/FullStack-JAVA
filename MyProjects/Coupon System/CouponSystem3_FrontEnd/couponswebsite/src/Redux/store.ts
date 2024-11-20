import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { AuthReducer } from "./authReducer";
import { CustomerReducer } from "./customerReducer";
import { GuestReducer } from "./guestReducer";
import { CompanyReducer } from "./companyReducer";
import { AdminReducer } from "./adminReducer";

//which reducers should we use
const reducers = combineReducers({auth:AuthReducer, admin:AdminReducer, customer:CustomerReducer, company:CompanyReducer, guest:GuestReducer});

//combine all reducer to one single and happy store
export const couponStore = configureStore({
    reducer: reducers,
    middleware: (getDefualtMiddleWare)=> getDefualtMiddleWare({serializableCheck:false})
});