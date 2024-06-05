import "./AddMetting.css";
import axios from "axios";
import { useEffect,useState } from "react";
import { Group } from "../../model/group";
import { SubmitHandler, useForm } from "react-hook-form";
import { Meetings } from "../../model/meeting";
import {useNavigate} from "react-router-dom";

export function AddMetting(): JSX.Element {
    const [groups,setGroups]=useState<Group[]>([]);
    const { register, handleSubmit, formState: { errors } } = useForm<Meetings>();
    const navigate = useNavigate();
    useEffect(()=>{
        axios.get("http://localhost:8080/api/v1/group/all")
        .then(response=>response.data)
        .then(data=>setGroups(data));
    },[])

    const addMeeting: SubmitHandler<Meetings> = (data) => {
        console.log(data);
        axios.post("http://localhost:8080/api/v1/meeting/add",data)
        .then(response=>console.log(data));
        navigate("/");
    }

    return (
        <div className="AddMetting Box">
            <form onSubmit={handleSubmit(addMeeting)}>
                <select {...register("devId")}>
                    {groups.map(item=><option key={item.id} value={item.id}>{item.teamName}</option>)}
                </select><br /><br />
                <input type="text" placeholder="Description" {...register("description")}/><br /><br />
                <label>Start Time</label><br />
                <input type="time" {...register("startTime")}/><br /><br />
                <label>End Time</label><br />
                <input type="time" {...register("endTime")}/><br /><br />
                <input type="text" placeholder="room name" {...register("roomName")}/><br /><br />
                <button type="submit">Add</button>
            </form>
        </div>
    );
}


