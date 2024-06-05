import "./Main.css";
import { useState,useEffect } from "react";
import axios from "axios";
import { Meetings } from "../../model/meeting";
import { SingleMeeting } from "../../Pages/singleMeeting/singleMeeting";

export function Main(): JSX.Element {
    const [meetings,setMeetings] = useState<Meetings[]>([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/api/v1/meeting/all")
        .then (response=>response.data)
        .then(data=>{
            setMeetings(data);
        })
        .catch(error=>{
            console.log(error);
        })
    },[]);
    return (
        <div className="Main">
			{meetings.map(meeting=><SingleMeeting meeting={meeting}/>)}
        </div>
    );
}
