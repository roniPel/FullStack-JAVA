import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { AuthReducer } from "./authReducer";

//which reducers should we use
const reducers = combineReducers({auth:AuthReducer});

//combine all reducer to one single and happy store
export const couponStore = configureStore({
    reducer: reducers,
    middleware: (getDefualtMiddleWare)=> getDefualtMiddleWare({serializableCheck:false})
});