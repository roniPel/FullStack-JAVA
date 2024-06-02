export class Task{
    public id:number;
    public name:string;
    public responsible:string;
    public dateScheduled: string;
    public dateCompleted: string;
    public isCompleted:boolean;

    constructor(id:number,name:string, responsible:string, dateScheduled:string, dateCompleted:string, isCompleted:boolean){
        this.id=id;
        this.name=name;
        this.responsible=responsible;
        this.dateScheduled=dateScheduled;
        this.dateCompleted=dateCompleted;
        this.isCompleted=isCompleted;
    }

}