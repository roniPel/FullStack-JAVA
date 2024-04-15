function calc() {
    var rWidth = document.getElementById("width").value;
    var rHeight = document.getElementById("height").value;
    var perimeter = (2*rHeight)+(2*rWidth);
    var area = rWidth*rHeight;
    console.log("Area: "+area);
    console.log("Perimeter: "+perimeter);
}