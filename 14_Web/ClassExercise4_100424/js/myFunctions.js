const displayMultiplicationTable = (size) => {
    var rowProducts = [];
    var product = 1;
    for(var row = 1; row<=size; row++) {
        for(var col = 1; col<=size; col++) {
            product = row*col;
            rowProducts[col] = product;
        }
        printArray(rowProducts);
    }
}

const printArray = (array)=>{
    var row = "";
    for (var counter=1;counter<array.length;counter++){
        row += array[counter]+"\t";
    }
    console.log(row);
}

displayMultiplicationTable(10);

