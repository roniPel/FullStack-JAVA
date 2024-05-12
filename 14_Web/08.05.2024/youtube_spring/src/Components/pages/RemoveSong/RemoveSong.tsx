import { useState } from "react";
import "./RemoveSong.css";
import { SongData } from "../../../model/SongData";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import ConfirmationModal from "../../ModalComponents/ConfirmationModal/ConfirmationModal";

export function RemoveSong(): JSX.Element {
    const [songID,setID] = useState("");
    const [songData,setData] = useState<SongData>();
    const navigate = useNavigate();
    return (
        <div className="RemoveSong">
			<h1>Remove song</h1><hr/>
            <div className="Box">
                <input type="text" placeholder="youtube song id" onChange={args =>setID(args.target.value)}/>
                <input type="button" placeholder="search" onClick={()=>{
                    // Get song data from backend
                    axios.get(`http://localhost:8080/${songID}`).then((result)=>{
                        console.log(result.data);
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
                    <input type="button" value="remove song from my list" onClick={()=>{
                        // Confirm user request (Confirmation Modal)
                        <ConfirmationModal/>
                        {}
                        // remove song
                        axios.delete(`http://localhost:8080/removeSong/${songID}`);
                        // navigate to song list
                        navigate("/");
                    }}/>
                </div>
            </div>
        </div>
    );
}
