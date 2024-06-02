import { Task } from "../../Models/Task";
import "./SingleTask.css";

export function SingleTask(props:Task): JSX.Element {
    
    return (
        <div className="SingleTask">
			<h1>{props.name}</h1>
            {props.responsible}<br/>
            Scheduled: {props.dateScheduled.toString()}<br/>
            Completed: {props.dateCompleted ? props.dateCompleted.toString() : ""}<hr/>
            <h5>{props.isCompleted}</h5>
        </div>
    );
}
