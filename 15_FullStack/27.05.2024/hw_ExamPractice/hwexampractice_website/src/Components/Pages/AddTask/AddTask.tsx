import { useNavigate } from "react-router-dom";
import "./AddTask.css";
import { SubmitHandler, useForm } from "react-hook-form";
import { Task } from "../../Models/Task";
import axios from "axios";

export function AddTask(): JSX.Element {
    const navigate = useNavigate();

    //declare our needed methods from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<Task>();

    const onSubmit: SubmitHandler<Task> = (data) => {
        console.log(data)
        data.id=0;
        data.isCompleted = false;
        axios.post("http://localhost:8080/Tasks/add",data).then(res=>{
            //go to to main page
            navigate("/");
        })
    }

    return (
        <div className="AddTask">
			<div className="Box">
                <form  onSubmit={handleSubmit(onSubmit)}>
                    <h1>Add Task</h1><hr />
                    <input type="text" placeholder="task name" {...register("name",{required:true})} />
                    {errors.name?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>'name' is required</span></>
                    }
                    <br /><br />
                    <input type="text" placeholder="responsible"
                        {...register("responsible", { required: true })} />
                    {errors.responsible?.type == "required" &&
                        <><br /><span style={{ color: "red" }}>'responsible' is required</span></>
                    }
                    <br /><br />
                    <input type="date" placeholder="Scheduled date" {...register("dateScheduled")}/>
                    <br /><br />
                    <input type="submit" value="create task" />
                </form>
            </div>
        </div>
    );
}