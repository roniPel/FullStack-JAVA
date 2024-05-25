export class SongData{
    public id:string;
    public name:string;
    public desc:string;
    public image:string;

    constructor(id:string,name:string,desc:string,image:string){
        this.id=id;
        this.name=name;
        this.desc=desc;
        this.image=image;
    }
}