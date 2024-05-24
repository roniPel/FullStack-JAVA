import { useEffect, useState } from "react";
import "./RemoveSong.css";
import { SongData } from "../../../model/SongData";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import notify from "../../../util/notify";
import { checkData } from "../../../util/checkData";
import { youtube } from "../../../redux/store";
import { deleteSongAction } from "../../../redux/songReducer";
import axiosJWT from "../../../util/axiosJWT";

export function RemoveSong(): JSX.Element {
    const [songID,setID] = useState("");
    const [songData,setData] = useState<SongData>();
    const navigate = useNavigate();

    useEffect(() => {
        checkData();
        if (youtube.getState().auth.token.length < 10) {
            navigate("/login");
        }
    })

    return (
        <div className="RemoveSong">
			<h1>Remove song</h1><hr/>
            <div className="Box">
                <input type="text" placeholder="youtube song id" onChange={args =>setID(args.target.value)}/>
                <input type="button" value="search" onClick={()=>{
                    //send command to backend, to get the song info
                    axiosJWT.get(`http://localhost:8080/${songID}`)
                        .then((result) => {
                            console.log(result.data);
                            let songData: SongData = new SongData(
                                result.data.id,
                                result.data.name,
                                result.data.description,
                                result.data.imageURL
                            );
                            setData(songData);
                        })
                        .catch(err=>{
                            console.log(err);
                            navigate("/page404");
                        });
                }}/>
                <hr/>
                <div className="Box">
                    <h1>{songData?.name}</h1><hr/>
                    <img src={songData?.image} width={100}/><br/>
                    {songData?.desc}<br/><br/>
                    <input type="button" value="remove song from my list" onClick={()=>{
                        //Todo - Confirm user request (Are you sure?)
                        // remove song
                        axiosJWT.delete(`http://localhost:8080/removeSong/${songID}`)
                        .then((result)=>{
                            // remove song from redux
                        youtube.dispatch(deleteSongAction(songID!))
                        // Notify success
                        notify.success("Song was deleted successfully");
                        // navigate to song list
                        navigate("/");
                        })                        
                        .catch(err=>{
                            notify.error("There was a problem deleting the song")
                            console.log(err);
                            navigate("/page404")
                        });;
                        
                    }}/>
                </div>
            </div>
        </div>
    );
}
