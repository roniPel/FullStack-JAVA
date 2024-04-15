const displayName = (name) => {
    var result = [];
    result[0] = makeStars(name.length+4);
    result[1] = '* '+name+' *';
    result[2] = result[0];
    return result;
}
const displayPyramid = (baseSize)=>{
    var result=[];
    for (var counter=0;counter<baseSize;counter++){
        result[counter] = (makeSpace(baseSize-counter+1)+makeStars(counter+1));
    }
    return result;
}

const makeStars = (stars)=>{
    var result="";
    for (var counter=0;counter<stars;counter++){
        result+="*";
    }
    return result;
}

const makeSpace = (space)=>{
    var result="";
    for (var counter=0;counter<space;counter++){
        result+=" ";
    }
    return result;
}

const printArray = (array)=>{
    for (var counter=0;counter<array.length;counter++){
        console.log(array[counter]);
    }
}
console.log("Display Name Function: ");
printArray(displayName("Roni"));
console.log("Display Pyramid Function: ");
printArray(displayPyramid(8));

