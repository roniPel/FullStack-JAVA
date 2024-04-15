var carList = [];

function Car(cManufacturer,cModel,cPic,cPrice){
    this.cManufacturer = cManufacturer;
    this.cModel = cModel;
    this.cPic = cPic;
    this.cPrice - cPrice;
}

const addCar = ()=> {
    carList.push(
        new Car(
            document.getElementById("cManufacturer").value,
            document.getElementById("cModel").value,
            document.getElementById("cPic").value,
            document.getElementById("cPrice").value,
        )
    );
    createTable();
    document.getElementById("myForm").reset();
}

const createTable = ()=>{
    var tableList = document.getElementById("tableList");
    var result = "";

    for(let counter = 0; counter<carList.length; counter++) {
        result += `
        <tr>
            <td>${carList[counter].cManufacturer}</td>
            <td>${carList[counter].cModel}</td>
            <td><img src=${carList[counter].cPic}></td>
            <td>${carList[counter].cPrice}</td>
        </tr>
        `;
    }
    tableList.innerHTML = result;
}