import { useState } from "react";
import "./TaskByDate.css";
import { Task } from "../../Models/Task";
import axios from "axios";
import { SingleTask } from "../SingleTask/SingleTask";

export function TaskByDate(): JSX.Element {
    const [date,setDate] = useState("");
    const [tasks,setTasks] = useState<Task[]>([]);

    const getData = ()=>{
        //axios
        axios.get(`http://localhost:8080/Tasks/taskCompletedBefore/${date}`).then(res=>{
            console.log(res.data);
            let returnTask = [];
            for (let index=0;index<res.data.length;index++){
                returnTask.push(res.data[index]);
            }
            setTasks(returnTask);
        })
    }

    return (
        <div className="TaskByDate">
			<div className="Box">
                <input type="date" placeholder="Enter date" onChange={(args)=>setDate(args.target.value)}/>
                <input type="button" value="Search" onClick={getData}/>
            </div>
            <hr/>
            {tasks.map(item=><SingleTask key={item.id} id={item.id} name={item.name} 
            responsible={item.responsible} dateScheduled={item.dateScheduled} 
            dateCompleted={item.dateCompleted} isCompleted={item.isCompleted}/>)}
        </div>
    );
}
