import { useNavigate } from "react-router-dom";
import { SongData } from "../../../model/SongData";
import "./SingleSong.css";

interface songProps {
    songData: SongData;
}

export function SingleSong(props: songProps): JSX.Element {
    const navigate = useNavigate();
    const IMAGE_WIDTH=200;
    return (
        <div className="SingleSong Box"
            onClick={()=>{
                navigate(`/player/${props.songData.id}`)
            }}
        >
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <img src={props.songData.image} width={IMAGE_WIDTH} />
                </div>
                <div className="Grid-Child">
                    <h1>{props.songData.name}</h1>
                    {props.songData.desc}
                </div>
            </div>
        </div>
    );
}
