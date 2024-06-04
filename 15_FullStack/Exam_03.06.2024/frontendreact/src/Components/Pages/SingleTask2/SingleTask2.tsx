import { useNavigate } from "react-router-dom";
import { Task } from "../../Models/Task";
import "./SingleTask2.css";
import { ColumnProps } from "../../Models/ColumnProps";
import Table from "../../Table/Table";

export function SingleTask2(props:Task): JSX.Element {
    const navigate = useNavigate();
    const columns: Array<ColumnProps<Task>> = [
        {
            key: 'id',
            title: 'ID',
        },
        {
            key: 'name',
            title: 'Name',
        },
        {
            key: 'responsible.name',
            title: 'Responsible',
        },
        {
            key: 'scheduledDate',
            title: 'Scheduled For',
        },
        {
            key: 'completed',
            title: 'Completed?',
        },
        ];
        
        const data = [
        {
            id: props.id,
            name: props.name,
            responsible: props.responsible,
            scheduledDate: props.scheduledDate,
            completed: props.completed,
        }
        ];

        
    return (
        <div className="SingleTask2" id="table" onClick={()=>{
            navigate(`/view2/${props.id}`)
        }}>
			<div>
                <Table data={data} columns={columns} />
            </div>
        </div>
    );
}
