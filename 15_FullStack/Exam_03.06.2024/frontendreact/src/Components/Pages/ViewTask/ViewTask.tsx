import { useNavigate, useParams } from "react-router-dom";
import "./ViewTask.css";
import { useEffect, useState } from "react";
import { Task } from "../../Models/Task";
import axios from "axios";
import { Button, ButtonGroup } from "@mui/material";

export function ViewTask(): JSX.Element {
    const params = useParams();
    const navigate = useNavigate();
    const [task,setTask] = useState<Task>();

    useEffect(()=>{
        axios.get(`http://localhost:8080/Tasks/GetSingleTask/${params.taskID}`).then(res=>{
            //console.log(res.data);
            setTask(res.data);
        })
    },[])

    return (
        <div className="ViewTask">
            <table>
                <tr>
                    <td>ID</td>
                    <td>{task?.id}</td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td>{task?.name}</td>
                </tr>
                <tr>
                    <td>Responsible</td>
                    <td>{task?.responsible.name}</td>
                </tr>
                <tr>
                    <td>Scheduled For</td>
                    <td>{task?.scheduledDate.toString()}</td>
                </tr>
                <tr>
                    <td>Completed?</td>
                    <td>{task?.isCompleted}</td>
                </tr>
            </table>
            <br/>
            <ButtonGroup>
                <Button variant="contained" color="error" onClick={() => { navigate(`/delete/${task?.id}`) }}>Delete</Button>
                <Button variant="contained" color="primary" onClick={() => { navigate(`/update/${task?.id}`) }}>Update</Button>
            </ButtonGroup>
        </div>
    );
}
