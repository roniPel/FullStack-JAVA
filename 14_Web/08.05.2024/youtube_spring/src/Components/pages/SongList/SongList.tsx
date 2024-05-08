import { useEffect, useState } from "react";
import "./SongList.css";
import axios from "axios";
import { SongData } from "../../../model/SongData";
import { SingleSong } from "../SingleSong/SingleSong";

export function SongList(): JSX.Element {
    //react hooks => useState , useEffect
    const [songList, setList] = useState<SongData[]>([]);

    //get song list from backend
    useEffect(()=>{
        let recivedList:SongData[] = [];
        axios.get("http://localhost:8080/all").then(result=>{
            for (let index=0;index<result.data.length;index++){
                recivedList.push(new SongData(
                    result.data[index].id,
                    result.data[index].name,
                    result.data[index].description,
                    result.data[index].imageURL
                ));
            };
            setList(recivedList);
        });
    },[])

    return (
        <div className="SongList">
            {/* {songList.map(item=><div className="Box">{item.id}<br/>{item.name}<br/>{item.desc}<br/><img src={item.image} width={100}/></div>)} */}
            {songList.map(item=><SingleSong songData={item}/>)}
        </div>
    );
}
