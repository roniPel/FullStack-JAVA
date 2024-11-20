import { ClientType } from "./ClientType";

export class UserDetails{
    public id:number;
    public name:string;
    public password:string;
    public email:string;
    public clientType:ClientType;

    constructor(id:number,name:string,password:string,email:string,clientType:ClientType){
         this.id=id;
         this.name=name;
         this.password=password;
         this.email=email;
         this.clientType= clientType;
    }
}