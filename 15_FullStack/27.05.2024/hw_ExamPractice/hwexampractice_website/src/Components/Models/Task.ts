export class Task{
    public id:number;
    public name:string;
    public responsible:string;
    public dateScheduled: Date;
    public dateCompleted: Date;
    public isCompleted:boolean;

    constructor(id:number,name:string, responsible:string, dateScheduled:Date, dateCompleted:Date, isCompleted:boolean){
        this.id=id;
        this.name=name;
        this.responsible=responsible;
        this.dateScheduled=dateScheduled;
        this.dateCompleted=dateCompleted;
        this.isCompleted=isCompleted;
    }

}