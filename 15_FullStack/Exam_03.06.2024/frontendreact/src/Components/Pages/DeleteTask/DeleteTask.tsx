import { useState } from "react";
import "./DeleteTask.css";
import { Task } from "../../Models/Task";
import axios from "axios";
import { SingleTask } from "../SingleTask/SingleTask";

export function DeleteTask(): JSX.Element {
    const [id,setID] = useState(0);
    const [task,setTask] = useState<Task>()
    
    const getData = ()=>{
        //axios
        axios.get(`http://localhost:8080/Tasks/GetSingleTask/${id}`).then(res=>{
            console.log(res.data);
            setTask(res.data);
        })
    }
    return (
        <div className="DeleteTask">
			<div className="Box">
                <h1>Delete Task</h1><hr /><br/>
                <input type="number" placeholder="ID to delete" onChange={(args)=>setID(args.target.valueAsNumber)}/>
                <input type="button" value="Find Task" onClick={getData}/>
            </div>
            <hr/>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Responsible</th>
                    <th>Scheduled For</th>
                    <th>Completed?</th>
                </tr>
            </table>
            {<SingleTask key={task!.id} id={task!.id} name={task!.name} responsible=
            {task!.responsible} scheduledDate={task!.scheduledDate} isCompleted={task!.isCompleted}/>}
        </div>
    );
}
