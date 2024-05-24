import { useParams } from "react-router-dom";
import "./SongPlayer.css";

export function SongPlayer(): JSX.Element {
    //get paramaters from the path
    const params = useParams();
    return (
        <div className="SongPlayer">
			<h1>Song Player</h1><hr/>
            <iframe width="560" height="315" 
            src= {`https://www.youtube.com/embed/${params.songID}?autoplay=1`}
            title="YouTube video player" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
            referrerPolicy="strict-origin-when-cross-origin" allowFullScreen ></iframe>
        </div>
    );
}
