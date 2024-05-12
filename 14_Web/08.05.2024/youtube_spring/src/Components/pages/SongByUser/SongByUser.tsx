import { useParams } from "react-router-dom";
import { PlayListData } from "../../../model/PlayListData";
import "./SongByUser.css";

export function SongByUser(): JSX.Element {
    const params = useParams();
    const userName = params.userName;
    return (
        <div className="SongByUser">
			<h1>Song by user ${params.userName}</h1>
            <div className="Box">
            </div>
        </div>
    );
}
