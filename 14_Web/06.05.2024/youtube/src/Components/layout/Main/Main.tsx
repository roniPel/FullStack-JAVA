import { StudentInfo } from "../../Pages/StudentInfo/StudentInfo";
import "./Main.css";

export function Main(): JSX.Element {
    let user: string = "roni peled";
    let students = ["Tim", "Ori", "Tamir", "Shani"];
    let students2 = [
        {name: "Tim", age: "30+", grade: 94},
        {name: "Ori", age: 20, grade: "unknown"},
        {name: "Shani", age: 25, grade:96}
    ];
    let daily = "pasta and schnitzel";
    let weekend = "Staek";
    let weekendHappy = "TGIF";
    let isWeekend = false;
    return (
        <div className="Main">
			<h2>Main</h2> <hr/>
            <div className="Box">
                Hello User: {user}
            </div>
            <br/><br/>
            Students 1: 
            <br/>
            {students.map(item=><div className="Box">{item}</div>)}
            <br/><br/>
            Students 2: 
            <br/>
            {students2.map(item=><StudentInfo name={item.name} age={item.age} grade={item.grade}></StudentInfo>)}
            <br/><br/>
            Regular Text: isWeekend?weekend:daily
            <br/><br/>
            'Curly Brackets' Text:  {isWeekend?weekend:daily}
            <br/><br/>
            {isWeekend && weekendHappy}
        </div>
    );
}
