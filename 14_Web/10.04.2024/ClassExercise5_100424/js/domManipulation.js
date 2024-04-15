function checkLogin() {
    // Get login details from document
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    // Check login details
    if(userName == 'ADMIN' && password == 'ADMIN@ADMIN.com') {
        document.getElementById("displayLoginResult").innerText = "Hello, My Master ${userName}";
        //document.getElementById("displayLoginResult").innerHTML = "<b>Hello, My Master!</b>";
    }
    else {
        document.getElementById("displayLoginResult").innerText = 'Why, who are you???';
    }
}