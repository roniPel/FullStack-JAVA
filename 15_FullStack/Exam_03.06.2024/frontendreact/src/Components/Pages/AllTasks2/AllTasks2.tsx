import {  useState, useEffect } from "react";
import "./AllTasks2.css";
import { ColumnProps } from "../../Models/ColumnProps";
import Table  from "../../Table/Table";
import { Task } from "../../Models/Task";
import axios from "axios";
import { SingleTask2 } from "../SingleTask2/SingleTask2";

export function AllTasks2(): JSX.Element {
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

    const columns: Array<ColumnProps<Task>> = [
        {
            key: 'id',
            title: 'ID',
        },
        {
            key: 'name',
            title: 'Name',
        },
        {
            key: 'responsible',
            title: 'Responsible',
        },
        {
            key: 'scheduledDate',
            title: 'Scheduled For',
        },
        {
            key: 'completed',
            title: 'Completed?',
        },
    ];

      let myData = Array();
      tasks.map((item)=>{
        myData.push({
            id: item.id,
            name: item.name,
            responsible: item.responsible.name,
            scheduledDate: item.scheduledDate,
            isCompleted: item.completed,
      })
      });

    return (
        <div className="AllTasks2">
            <div>
                <br/>
                <Table data={myData} columns={columns} />
            </div>
            {/* {tasks.map(item=><SingleTask2 key={item.id} id={item.id} name={item.name} responsible=
            {item.responsible} scheduledDate={item.scheduledDate} isCompleted={item.isCompleted}/>)} */}
        </div>
    );
}
