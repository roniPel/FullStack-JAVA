const translation = {
    "Shani": "שאאאאני",
    "Tamir": "טאאאאאאאמיר",
    "Ori" : "הפצוע",
    "max" :" מאקש"
};

localStorage.setItem("trans",JSON.stringify(translation));
var myTrans = JSON.parse(localStorage.getItem("trans"));

const studentName = "Shani";
console.log(myTrans[studentName]);