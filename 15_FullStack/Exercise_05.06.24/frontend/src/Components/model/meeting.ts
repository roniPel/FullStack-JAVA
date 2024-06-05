export class Meetings{
    description:string;
    devId:number;
    endTime:string;
    meetingId:number;
    roomName:string;
    startTime:string;

    constructor(description:string,devId:number,endTime:string,meetingId:number,roomName:string,startTime:string){
        this.description=description;
        this.devId=devId;
        this.endTime=endTime;
        this.meetingId=meetingId;
        this.roomName=roomName;
        this.startTime=startTime;
    }
}