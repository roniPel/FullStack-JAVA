import { Task } from "../../Models/Task";
import "./SingleTask.css";

export function SingleTask(props:Task): JSX.Element {
    const completed = () => {
        if(props.isCompleted===true){
            return "Yes";
        }
        else{
            return "No";
        }}
    return (
        <div className="SingleTask" id="table">
            <table>
                <tr>
                    <td>{props.id}</td>
                    <td>{props.name}</td>
                    <td>{props.responsible.name}</td>
                    <td>{props.scheduledDate.toString()}</td>
                    <td>{completed()}</td>
                </tr>
            </table>
        </div>
    );
}
