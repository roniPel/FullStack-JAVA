//npm install @reduxjs/toolkit

import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { SongReducer } from "./songReducer";

//which reducers should we use
const reducers = combineReducers({songs:SongReducer});

//combine all reducer to one single and happy store
export const youtube = configureStore({
    reducer: reducers,
    middleware: (getDefualtMiddleWare)=> getDefualtMiddleWare({serializableCheck:false})
});