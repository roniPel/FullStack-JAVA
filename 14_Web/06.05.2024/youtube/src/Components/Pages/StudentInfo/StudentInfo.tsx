import "./StudentInfo.css";
interface StudentProps{
    name: string;
    age: any;
    grade: any;
}
export function StudentInfo(info:StudentProps): JSX.Element {
    return (
        <div className="StudentInfo">
			<div className="Box">
                {info.name}<hr/>
                {info.age}<br/>
                <h3>{info.grade}</h3>
            </div>
        </div>
    );
}
