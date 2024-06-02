import { useEffect, useState } from "react";
import "./UncompletedTasks.css";
import { Task } from "../../Models/Task";
import axios from "axios";
import { SingleTask } from "../SingleTask/SingleTask";

export function UncompletedTasks(): JSX.Element {
    const [tasks,setTasks] = useState<Task[]>([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/Tasks/uncompletedTasks").then(res=>{
            //console.log(res.data);           
            let returnTask = [];
            for (let index=0;index<res.data.length;index++){
                returnTask.push(res.data[index]);
            }
            setTasks(returnTask);
        })
    },[])
    return (
        <div className="UncompletedTasks">
			{tasks.map(item=><SingleTask key={item.id} id={item.id} name={item.name} 
            responsible={item.responsible} dateScheduled={item.dateScheduled} 
            dateCompleted={item.dateCompleted} isCompleted={item.isCompleted}/>)}
        </div>
    );
}
