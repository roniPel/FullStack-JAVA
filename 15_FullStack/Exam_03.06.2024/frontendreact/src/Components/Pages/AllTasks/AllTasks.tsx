import { useEffect, useState } from "react";
import "./AllTasks.css";
import { Task } from "../../Models/Task";
import axios from "axios";
import { SingleTask } from "../SingleTask/SingleTask";

export function AllTasks(): JSX.Element {
    const [tasks,setTasks] = useState<Task[]>([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/Tasks/GetAllTasks").then(res=>{
            //console.log(res.data);           
            let returnTask = [];
            for (let index=0;index<res.data.length;index++){
                returnTask.push(res.data[index]);
            }
            setTasks(returnTask);
        })
    },[])

    return (
        <div className="AllTasks">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Responsible</th>
                    <th>Scheduled For</th>
                    <th>Completed?</th>
                </tr>
            </table>
            {tasks.map(item=><SingleTask key={item.id} id={item.id} name={item.name} responsible=
            {item.responsible} scheduledDate={item.scheduledDate} isCompleted={item.isCompleted}/>)}
        </div>
    );


}
