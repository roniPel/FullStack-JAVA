export class UserDetails{
    public id:number;
    public name:string;
    public email:string;
    public password:string; 
    public userType:string; 
    public tel:string; 
    public location:string; 
    public genre:string;

    constructor(id:number,name:string,email:string,password:string,userType:string,tel:string,
        location:string,genre:string){
            this.id=id;
            this.name=name;
            this.email=email;
            this.password=password;
            this.userType=userType;
            this.tel=tel;
            this.location=location;
            this.genre=genre;
        }
}