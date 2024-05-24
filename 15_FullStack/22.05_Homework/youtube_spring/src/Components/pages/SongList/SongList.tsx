import { useEffect, useState } from "react";
import "./SongList.css";
import axios from "axios";
import { SongData } from "../../../model/SongData";
import { SingleSong } from "../SingleSong/SingleSong";
import { youtube } from "../../../redux/store";
import { getAllSongsAction } from "../../../redux/songReducer";
import axiosJWT from "../../../util/axiosJWT";
import { useNavigate } from "react-router-dom";
import { checkData } from "../../../util/checkData";

export function SongList(): JSX.Element {
    //react hooks => useState , useEffect
    const [songList, setList] = useState<SongData[]>([]);
    const navigate = useNavigate();
    //get song list from backend
    useEffect(() => {
        let recivedList: SongData[] = [];
        //check if we have any song on our list, 0 length indicates that we don't have any song
        if (youtube.getState().auth.token.length<10){
            navigate("/login");
        }
        checkData();
        if (youtube.getState().songs.allSongs.length == 0) {
            axiosJWT.get("http://localhost:8080/all")
                .then(result => {
                    console.log("data:", result);
                    for (let index = 0; index < result.data.length; index++) {
                        recivedList.push(new SongData(
                            result.data[index].id,
                            result.data[index].name,
                            result.data[index].description,
                            result.data[index].imageURL
                        ));
                    }                   
                    youtube.dispatch(getAllSongsAction(recivedList));
                    setList(youtube.getState().songs.allSongs);
                })
                .catch(err=>{
                    navigate("/login")
                });
        } else {
            setList(youtube.getState().songs.allSongs);
        }
    }, [])

    return (
        <div className="SongList">
            {/* {songList.map(item=><div className="Box">{item.id}<br/>{item.name}<br/>{item.desc}<br/><img src={item.image} width={100}/></div>)} */}
            {songList.map(item => <SingleSong key={item.id} songData={item} />)}
        </div>
    );
}
