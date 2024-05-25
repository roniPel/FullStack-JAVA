import { useEffect, useState } from "react";
import "./AddSong.css";
import axios from "axios";
import { SongData } from "../../../model/SongData";
import { useNavigate } from "react-router-dom";
import { youtube } from "../../../redux/store";
import { addSongAction } from "../../../redux/songReducer";
import { checkData } from "../../../util/checkData";
import axiosJWT from "../../../util/axiosJWT";
export function AddSong(): JSX.Element {
    const [songID,setID] = useState("");
    const [songData,setData] = useState<SongData>();
    const navigate = useNavigate();


    useEffect(() => {
        checkData();
        if(youtube.getState().auth.token.length<10){
            navigate("/login");
        }
        else {
            const addSongResult = [
            <div className="AddSong">
                    <h1>Add Song</h1><hr/>
                    <div className="Box">
                        <input type="text" placeholder="youtube song id" onChange={(args=>setID(args.target.value))}/>
                        <input type="button" value="search" onClick={()=>{
                            //send command to backend, to get the song info
                            axiosJWT.get(`http://localhost:8080/${songID}`).then((result)=>{
                                console.log(result.data);
                                let songData:SongData = new SongData(
                                    result.data.id,
                                    result.data.name,
                                    result.data.description,
                                    result.data.imageURL
                                );
                                setData(songData);
                            }) 
                            .catch(err=>{
                                navigate("/page404")
                            });      
                        }}/>
                        <hr/>
                        <div className="Box">
                            <h1>{songData?.name}</h1><hr/>
                            <img src={songData?.image} width={100}/><br/>
                            {songData?.desc}<br/><br/>
                            <input type="button" value="add song to my list" onClick={()=>{
                                //add the song to redux                        
                                youtube.dispatch(addSongAction(songData!))
                                //navigate to song list
                                navigate("/");                   
                            }}/>
                        </div>
                    </div>
                </div>
            ]
            return (
                
            );
        }
    }, [])
    
    return(
        <div className="AddSong">
            {AddSongResult}
        </div>
        );
}
