var list = [
    {"date": Date.now(),"product": "Milk", "urgency": 1, "picture": new url("C:\\Users\\ronir\\Pictures\\Screenshots\\Screenshot 2023-12-20 165713.png")},
    {"date": Date.now(),"product": "Cheese", "urgency": 2, "picture": new url("C:\\Users\\ronir\\Pictures\\Screenshots\\Screenshot 2023-12-20 165933.png")},
    {"date": Date.now(),"product": "Tomato", "urgency": 1, "picture": new url("C:\\Users\\ronir\\Pictures\\Screenshots\\Screenshot 2023-12-20 170220.png")},
    {"date": Date.now(),"product": "Humus", "urgency": 3, "picture": new url("C:\\Users\\ronir\\Pictures\\Screenshots\\Screenshot 2024-04-08 151808.png")},
];

function getTime() {
    var hours = new Date().now().getHours();
    var minutes = new Date().now().getMinutes();
    var seconds = new Date().now().getSeconds();
    return `${hours}:${minutes}:${seconds}`;
}

const addItem = ()=>{
    var date = document.getElementsById("date").value;
    var product = document.getElementsById("product").value;
    var urgency = document.getElementsById("urgency").value;
    var picture = document.getElementsById("picture").value;

    var newItem = new Object();
    newItem.date = date;
    newItem.product = product;
    newItem.urgency = urgency;
    newItem.picture = picture;
    console.log(newItem);
    list.push(newItem);
    showList();
}

function showList(){
    var myList = document.getElementById("myList");
    let result = "";
    
    for(var counter = 0; counter<list.length; counter++) {
        result += `
        <tr>
            <td>${list[counter].date}</td>
            <td>${list[counter].product}</td>
            <td>${list[counter].urgency}</td>
            <td><img src=${list[counter].picture}/></td>
        </tr>
        `;
        //list.push({"date":Date.now(),"product":"Product","urgency":2,"picture":"Picture"});
    }
    myList.innerHTML = result;
}