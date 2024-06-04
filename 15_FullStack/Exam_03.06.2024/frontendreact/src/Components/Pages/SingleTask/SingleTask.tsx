import { useNavigate } from "react-router-dom";
import { Task } from "../../Models/Task";
import "./SingleTask.css";
import { useEffect, useState } from "react";

export function SingleTask(props:Task): JSX.Element {
    const navigate = useNavigate();
    //console.log(props);

    const numRows = Task.length;
    return (
        <div className="SingleTask" id="table" onClick={()=>{
            navigate(`/view/${props.id}`)
        }}>
            <table>
                <tr>
                    <td>{props.id}</td>
                    <td>{props.name}</td>
                    <td>{props.responsible.name}</td>
                    <td>{props.scheduledDate.toString()}</td>
                    <td>{props.completed.toString()}</td>
                </tr>
            </table>
        </div>
    );
}
