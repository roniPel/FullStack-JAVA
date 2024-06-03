import { useNavigate } from "react-router-dom";
import { Task } from "../../Models/Task";
import "./SingleTask.css";

export function SingleTask(props:Task): JSX.Element {
    const navigate = useNavigate();
    const completed = () => {
        if(props.isCompleted===true){
            return "Yes";
        }
        else{
            return "No";
        }}
        
    return (
        <div className="SingleTask" id="table" onClick={()=>{
            navigate(`/view/${props.id}`)
        }}>
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
