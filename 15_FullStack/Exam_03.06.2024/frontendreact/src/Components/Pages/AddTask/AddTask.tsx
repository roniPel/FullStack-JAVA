import { useNavigate } from "react-router-dom";
import "./AddTask.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Task } from "../../Models/Task";
import axios from "axios";
import { Button, ButtonGroup } from "@mui/material";

export function AddTask(): JSX.Element {

    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Task>();

    const onSubmit: SubmitHandler<Task> = (data) => {
        //console.log(data)
        data.id=0;
        data.completed=false;
        //todo, move to axios :)
        axios.post("http://localhost:8080/Tasks/AddTask",data).then(res=>{
            //move to thank you page
            navigate("/")
        })
    }
    
    return (
        <div className="AddTask">
			<div className="Box">
                <form  onSubmit={handleSubmit(onSubmit)}>
                    <h1>Add Task</h1><hr /><br/>
                    <input type="text" placeholder="task name" {...register("name",{required:true})} />
                    {errors.name?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>task name is required</span></>
                    }
                    <br /><br />
                    Scheduled: <input type="date" placeholder="Scheduled date"
                        {...register("scheduledDate", { required: true})} />
                    {errors.scheduledDate?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>scheduled date is required</span></>
                    }
                    <br /><br />
                    <h3>Person Responsible: </h3>
                    <input type="text" placeholder="name" {...register("responsible.name",{required:true})}/>
                    {errors.responsible?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>person's name is required</span></>
                    }
                    <br/><br/>
                    <input type="number" placeholder="phone number" {...register("responsible.phoneNum")}/>
                    <br/><br/>
                    <ButtonGroup>
                        <Button type = "submit" variant="contained" color="primary" >Create Task</Button>
                        <Button variant="contained" color="error" onClick={() => { navigate("/") }}>Cancel</Button>
                    </ButtonGroup>
                </form>
            </div>
        </div>
    );
}
