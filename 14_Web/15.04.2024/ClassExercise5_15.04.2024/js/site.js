var myGrades = {
    gradeArray: [],
    average: 0,
    min: 0,
    max: 0,
    calcValues: function(){
        this.min = this.gradeArray[0];
        this.max = this.gradeArray[0];
        this.average = this.gradeArray[0];
        var sum = 0;
        for(var counter=1; counter<this.gradeArray.length; counter++){
            if(this.gradeArray[counter]<this.min){
                this.min = this.gradeArray[counter];
            }
            if(this.gradeArray[counter]>this.max){
                this.max = this.gradeArray[counter];
            }
            this.average += this.gradeArray[counter];
        }
        this.average /= this.gradeArray.length;
    },
}
myGrades.gradeArray = [58,78,35,89,20,45,76];
myGrades.calcValues();
console.log("*My Grades* ")
console.log("Average: ",myGrades.average);
console.log("Min: ", myGrades.min);
console.log("Max: ", myGrades.max);