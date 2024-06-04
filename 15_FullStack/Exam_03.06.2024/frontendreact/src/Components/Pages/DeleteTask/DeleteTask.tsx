import "./DeleteTask.css";
import axios from "axios";
import notify from "../../Utils/notify";
import { useNavigate, useParams } from "react-router-dom";
import { Button, ButtonGroup } from "@mui/material";
import { useEffect, useState } from "react";
import { Task } from "../../Models/Task";

export function DeleteTask(): JSX.Element {
    const navigate = useNavigate();
    const params = useParams();
    const [task,setTask] = useState<Task>();

    useEffect(()=>{
        axios.get(`http://localhost:8080/Tasks/GetSingleTask/${params.taskID}`).then(res=>{
            //console.log(res.data);
            setTask(res.data);
        }).catch((err)=>{
            console.log(err);
            notify.error("There was a problem getting the requested task details");
        })
    },[])

    const deleteTask = ()=>{
        //axios
        axios.delete(`http://localhost:8080/Tasks/DeleteTask/${params.taskID}`).then(res=>{
            //move to home + notify success
            notify.success("Task was deleted successfully");
            navigate("/");
        }).catch((err)=>{
            console.log(err);
            notify.error("There was a problem deleting the task.");
        })
    }
    return (
        <div className="DeleteTask">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Responsible</th>
                    <th>Scheduled For</th>
                    <th>Completed?</th>
                </tr>
                <tr>
                    <td>{task?.id}</td>
                    <td>{task?.name}</td>
                    <td>{task?.responsible.name}</td>
                    <td>{task?.scheduledDate.toString()}</td>
                    <td>{task?.completed.toString()}</td>
                </tr>
            </table>
			<div className="Box">
                <h1>Delete Task</h1><hr /><br/>
                <h3>Are you sure you would like to delete task {params.taskID}?</h3>
                <ButtonGroup>
                    <Button variant="contained" color="error" onClick={deleteTask}>Yes</Button>
                    <Button variant="contained" color="primary" onClick={() => { navigate("/") }}>No</Button>
                </ButtonGroup>
            </div>
        </div>
    );
}
