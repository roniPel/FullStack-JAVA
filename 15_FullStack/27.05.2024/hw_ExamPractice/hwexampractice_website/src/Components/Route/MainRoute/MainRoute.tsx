import { Route, Routes } from "react-router-dom";
import { Main } from "../../Layout/Main/Main";
import "./MainRoute.css";
import { AllTasks } from "../../Pages/AllTasks/AllTasks";
import { CompletedTasks } from "../../Pages/CompletedTasks/CompletedTasks";
import { UncompletedTasks } from "../../Pages/UncompletedTasks/UncompletedTasks";
import { TaskByDate } from "../../Pages/TaskByDate/TaskByDate";
import { AddTask } from "../../Pages/AddTask/AddTask";
import { Page404 } from "../../Pages/Page404/Page404";

export function MainRoute(): JSX.Element {
    return (
        <div className="MainRoute">
			<Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/all" element={<AllTasks/>}/>
                <Route path="/completed" element={<CompletedTasks/>}/>
                <Route path="/uncompleted" element={<UncompletedTasks/>}/>
                <Route path="/byDate" element={<TaskByDate/>}/>
                <Route path="/add" element={<AddTask/>}/>
                <Route path="*" element={<Page404/>}/>
            </Routes>
        </div>
    );
}