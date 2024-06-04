import axios from "axios";
import "./UpdateTask.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Task } from "../../Models/Task";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import notify from "../../Utils/notify";
import { ViewTask } from "../ViewTask/ViewTask";
import { Button, ButtonGroup } from "@mui/material";

export function UpdateTask(): JSX.Element {
    const [taskID,setTaskID] = useState(0);
    const [respID,setRespID] = useState(0);
    const [task,setTask] = useState<Task>();
    const params = useParams();
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Task>();

    useEffect(()=>{
        axios.get(`http://localhost:8080/Tasks/GetSingleTask/${params.taskID}`).then(res=>{
            //console.log(res.data);
            setTask(res.data);
            setTaskID(res.data.id);
            setRespID(res.data.responsible.id);
        }).catch((err)=>{
            console.log(err);
            notify.error("There was a problem getting the requested task details");
        })
    },[])

    const onSubmit: SubmitHandler<Task> = (data) => {
        //console.log(data);
        data.id = taskID;
        data.responsible.id = respID;
        axios.put(`http://localhost:8080/Tasks/UpdateTask/${data.id}`,data).then(res=>{
            //move to home + notify success
            notify.success("Task was updated successfully");
            navigate("/");
        }).catch((err)=>{
            console.log(err);
            notify.error("There was a problem updating the task.");
        })
    }

    return (
        <div className="UpdateTask">
            <div className="ViewTask">
            </div>
			<div className="Box">
                <form  onSubmit={handleSubmit(onSubmit)}>
                    <h1>Update Task</h1><hr /><br/>
                    <input type="text" placeholder="task name" defaultValue={task?.name} {...register("name",{required:true})}  />
                    {errors.name?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>task name is required</span></>
                    }
                    <br /><br />
                    <input type="date" placeholder="Scheduled date" 
                        {...register("scheduledDate", { required: true})} />
                    {errors.scheduledDate?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>scheduled date is required</span></>
                    }
                    <br /><br />
                    <input type="checkbox" placeholder="Completed?"
                    {...register("isCompleted")} />
                    <br/><br/>
                    <h3>Person Responsible: </h3>
                    <input type="text" placeholder="name" defaultValue={task?.responsible.name} {...register("responsible.name",{required:true})}/>
                    {errors.responsible?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>person's name is required</span></>
                    }
                    <br/><br/>
                    <input type="number" placeholder="phone number" defaultValue={task?.responsible.phoneNum} {...register("responsible.phoneNum")}/>
                    <br/><br/>
                    <ButtonGroup>
                        <Button type = "submit" variant="contained" color="primary" >Update Task</Button>
                        <Button variant="contained" color="error" onClick={() => { navigate("/") }}>Cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
    
}
