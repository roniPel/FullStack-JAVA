var taskList = [];

function Task(tName, tResponsibility, tUrgency) {
    this.tName = tName;
    this.tResponsibility = tResponsibility;
    this.tUrgency = tUrgency;
}

const addTask = () => {
    taskList.push(
        new Task(
            document.getElementById("tName").value,
            document.getElementById("tResponsibility").value,
            document.getElementById("tUrgency").value
        )
    )
    createTable();
    document.getElementById("myForm").reset();
}

const createTable = () => {
    var tableList = document.getElementById("tableList");
    var result = "";

    for (let counter = 0; counter < taskList.length; counter++) {
        result += `
            <tr>
                <td>${new Date()}</td>
                <td>${taskList[counter].tName}</td>
                <td>${taskList[counter].tResponsibility}</td>
                <td>${taskList[counter].tUrgency}</td>
            </tr>
        `;
    }
    tableList.innerHTML = result;

    //Save in storage
    localStorage.setItem("localSavedList",JSON.stringify(taskList));

    //Download from storage
    var myList = JSON.parse(localStorage.getItem("localSavedList"));
    console.log(myList);
}
