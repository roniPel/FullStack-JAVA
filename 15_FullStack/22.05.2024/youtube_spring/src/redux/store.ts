//npm install @reduxjs/toolkit

import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { SongReducer } from "./songReducer";
import { AuthReducer } from "./authReducer";

//which reducers should we use
const reducers = combineReducers({songs:SongReducer,auth:AuthReducer});

//combine all reducer to one single and happy store
export const youtube = configureStore({
    reducer: reducers,
    middleware: (getDefualtMiddleWare)=> getDefualtMiddleWare({serializableCheck:false})
});