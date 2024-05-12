import { useState } from "react";
import "./AddSong.css";
import axios from "axios";
import { SongData } from "../../../model/SongData";
import { useNavigate } from "react-router-dom";
export function AddSong(): JSX.Element {
    const [songID,setID] = useState("");
    const [songData,setData] = useState<SongData>();
    const navigate = useNavigate();
    return (
        <div className="AddSong">
			<h1>Add Song</h1><hr/>
            <div className="Box">
                <input type="text" placeholder="youtube song id" onChange={(args=>setID(args.target.value))}/>
                <input type="button" value="search" onClick={()=>{
                    //send command to backend, to get the song info
                    axios.get(`http://localhost:8080/${songID}`).then((result)=>{
                        let songData:SongData = new SongData(
                            result.data.id,
                            result.data.name,
                            result.data.description,
                            result.data.imageURL
                        );
                        setData(songData);
                    })       
                }}/>
                <hr/>
                <div className="Box">
                    <h1>{songData?.name}</h1><hr/>
                    <img src={songData?.image} width={100}/><br/>
                    {songData?.desc}<br/><br/>
                    <input type="button" value="add song to my list" onClick={()=>{
                        //navigate to song list
                        navigate("/");                   
                    }}/>
                </div>
            </div>
        </div>
    );
}
