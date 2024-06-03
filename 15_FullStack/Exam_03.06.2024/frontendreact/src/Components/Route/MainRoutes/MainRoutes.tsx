import { Route, Routes } from "react-router-dom";
import "./MainRoutes.css";
import { AllTasks } from "../../Pages/AllTasks/AllTasks";
import { AddTask } from "../../Pages/AddTask/AddTask";
import { DeleteTask } from "../../Pages/DeleteTask/DeleteTask";
import { Page404 } from "../../Pages/Page404/Page404";
import { UpdateTask } from "../../Pages/UpdateTask/UpdateTask";

export function MainRoutes(): JSX.Element {
    return (
        <div className="MainRoutes">
			<Routes>
                <Route path="/" element={<AllTasks/>}/>
                <Route path="/delete" element={<DeleteTask/>}/>
                <Route path="/add" element={<AddTask/>}/>
                <Route path="/update" element={<UpdateTask/>}/>
                <Route path="*" element={<Page404/>}/>
            </Routes>
        </div>
    );
}
