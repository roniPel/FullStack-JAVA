import { useEffect, useState } from "react";
import "./AllTasks.css";
import axios from "axios";
import { SingleTask } from "../SingleTask/SingleTask";
import { Task } from "../../Models/Task";

export function AllTasks(): JSX.Element {
    const [tasks,setTasks] = useState<Task[]>([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/Tasks/allTasks").then(res=>{
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
			{tasks.map(item=><SingleTask key={item.id} id={item.id} name={item.name} 
            responsible={item.responsible} dateScheduled={item.dateScheduled} 
            dateCompleted={item.dateCompleted} isCompleted={item.isCompleted}/>)}
        </div>
    );
}
