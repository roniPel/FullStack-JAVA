import { Route, Routes } from "react-router-dom";
import "./MainRoutes.css";
import { AllTasks } from "../../Pages/AllTasks/AllTasks";
import { AddTask } from "../../Pages/AddTask/AddTask";
import { DeleteTask } from "../../Pages/DeleteTask/DeleteTask";
import { Page404 } from "../../Pages/Page404/Page404";
import { UpdateTask } from "../../Pages/UpdateTask/UpdateTask";
import { ViewTask } from "../../Pages/ViewTask/ViewTask";
import { AllTasks2 } from "../../Pages/AllTasks2/AllTasks2";

export function MainRoutes(): JSX.Element {
    return (
        <div className="MainRoutes">
			<Routes>
                <Route path="/" element={<AllTasks/>}/>
                <Route path="/All2" element={<AllTasks2/>}/>
                <Route path="/delete/:taskID" element={<DeleteTask/>}/>
                <Route path="/add" element={<AddTask/>}/>
                <Route path="/update/:taskID" element={<UpdateTask/>}/>
                <Route path="/view/:taskID" element={<ViewTask/>}/>
                <Route path="*" element={<Page404/>}/>
            </Routes>
        </div>
    );
}
