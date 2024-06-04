import "./DeleteTask.css";
import axios from "axios";
import notify from "../../Utils/notify";
import { useNavigate, useParams } from "react-router-dom";
import { Button, ButtonGroup } from "@mui/material";

export function DeleteTask(): JSX.Element {
    const navigate = useNavigate();
    const params = useParams();

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
