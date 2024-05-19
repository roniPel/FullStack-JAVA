import { ClientType } from "./ClientType";

export class Credentials{
    public id:number;
    public userName:string;
    public userPassword:string;
    public userEmail:string;
    public clientType:ClientType;

    constructor(id:number,userName:string,userPassword:string,userEmail:string,clientType:ClientType){
         this.id=id;
         this.userName=userName;
         this.userPassword=userPassword;
         this.userEmail=userEmail;
         this.clientType= clientType;
    }
}