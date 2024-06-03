import { Person } from "./Person";

export class Task {
    public id:number;
    public name:string;
    public responsible:Person;
    public scheduledDate:Date;
    public isCompleted:boolean;

    constructor(id:number,name:string,responsible:Person,scheduledDate:Date,isCompleted:boolean){
        this.id = id;
        this.name = name;
        this.responsible = responsible;
        this.scheduledDate = scheduledDate;
        this.isCompleted = isCompleted;
    }
}