import { Route, Routes } from "react-router-dom";
import "./MainRoute.css";
import { Page404 } from "../../Pages/Page404/Page404";
import { Register } from "../../Pages/Register/Register";
import { Home, Login } from "@mui/icons-material";
import { Welcome } from "../../Pages/Welcome/Welcome";
import { AboutUs } from "../../Pages/AboutUs/AboutUs";

export function MainRoute(): JSX.Element {
    return (
        <div className="MainRoute">
			<Routes>
                <Route path="/" element ={<Welcome/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/aboutus" element={<AboutUs/>}/>
                <Route path="*" element ={<Page404/>}/>
            </Routes>
        </div>
    );
}
