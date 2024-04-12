function calculateDonation(){
    // Get salary details from document + calculate
    var mySalary = document.getElementById("salary").value;
    var myTable = `
        <table>
            <tr>
                <td>10%</td>
                <td>${mySalary*0.1}</td>
            </tr>
            <tr>
                <td>20%</td>
                <td>${mySalary*0.2}</td>
            </tr>
        </table>
        ` ;
    // Display Calculations
    //document.getElementById("test").innerText = "Test";
    document.getElementById("result").innerHTML = myTable;
}

function showTime(){
    document.getElementById("myTime").innerHTML = 
        `${new Date().getHours()}:${new Date().getMinutes()}:${new Date().getSeconds()}`;
}
setInterval(()=>{
    showTime();
},950)