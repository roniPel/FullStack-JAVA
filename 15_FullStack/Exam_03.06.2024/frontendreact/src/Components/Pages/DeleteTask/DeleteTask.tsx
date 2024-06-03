import { JSXElementConstructor, ReactElement, ReactNode, ReactPortal, useState } from "react";
import "./DeleteTask.css";
import { Task } from "../../Models/Task";
import axios from "axios";
import notify from "../../Utils/notify";
import { useNavigate, useParams } from "react-router-dom";
import { ViewTask } from "../ViewTask/ViewTask";
import { Button, ButtonGroup } from "@mui/material";

export function DeleteTask(): JSX.Element {
    const [id,setID] = useState(0);
    const [task,setTask] = useState<Task>();
    const navigate = useNavigate();
    const [show,setShow] = useState(false);
    const params = useParams();

    const deleteTask = ()=>{
        //axios
        axios.delete(`http://localhost:8080/Tasks/DeleteTask/${params.taskID}`).then(res=>{
            //console.log(res.data);
            navigate("/");
        })
    }
    return (
        <div className="DeleteTask">
			<div className="Box">
                <h1>Delete Task</h1><hr /><br/>
                <h3>Are you sure you would like to delete task {params.taskID}?</h3>
                <ButtonGroup>
                    <Button variant="contained" color="error" onClick={deleteTask}>Yes</Button>
                    <Button variant="contained" color="primary" onClick={() => { navigate("/") }}>No</Button>
                </ButtonGroup>
                {/* <input type="button" value="Yes" onClick={deleteTask}/>
                <input type="button" value="No!" onClick={()=>navigate("/")}/> */}
            </div>
            <hr/>

        </div>
    );
}
