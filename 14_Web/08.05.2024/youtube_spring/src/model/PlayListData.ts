import { SongData } from "./SongData";

export class PlayListData{
    public id:number;
    public playlistName:string;
    public songs:any;

    constructor(id:number,playlistName:string,songs:any){
        this.id=id;
        this.playlistName=playlistName;
        this.songs=songs;
    }
}