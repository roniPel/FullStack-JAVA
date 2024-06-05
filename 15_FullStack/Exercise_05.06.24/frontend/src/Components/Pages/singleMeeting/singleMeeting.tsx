import { Meetings } from "../../model/meeting";
import "./singleMeeting.css";

interface meetingProps{
    meeting:Meetings;
}

export function SingleMeeting(props:meetingProps): JSX.Element {
    return (
        <div className="singleMeeting Box">
			<h1>{props.meeting.roomName}</h1><hr/>
            {props.meeting.description}<br/>
            {props.meeting.startTime}<br/>
            {props.meeting.endTime}<br/>            
        </div>
    );
}
