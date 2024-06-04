import { Person } from "./Person";

export class Task {
    public id:number;
    public name:string;
    public responsible:Person;
    public scheduledDate:Date;
    public completed:boolean;

    constructor(id:number,name:string,responsible:Person,scheduledDate:Date,completed:boolean){
        this.id = id;
        this.name = name;
        this.responsible = responsible;
        this.scheduledDate = scheduledDate;
        this.completed = completed;
    }
}