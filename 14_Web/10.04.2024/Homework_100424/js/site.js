function clock(){
    var hours = checkTime(new Date().getHours());
    var minutes = checkTime(new Date().getMinutes());
    var seconds = checkTime(new Date().getSeconds());
    document.getElementById("myClock").innerHTML = 
    `${hours}:${minutes}:${seconds}`;
}

function checkTime(i) {
    return (i < 10) ? "0" + i : i;
}

setInterval(()=>{
    clock();
},900);