
    var students = ["Roni", "Shani", "Tamir", "Tim", "Zeev", "Eylon", "sigal", "Amit", "Ori", "Ori2"];
    var grades = [95, 98, 95, 97, 92, 96, 94, 95, 97, 99];

    //JSON - Java Script Object Notation
    var data = [
        {"name": "Student1", "grade": 85},
        {"name": "Student1", "grade": 88},
        {"name": "Student1", "grade": 98},
        {"name": "Student1", "grade": 95}
    ];

    function displayStudents() {
        let result = "";

        for(var counter = 0; counter<students.length;counter++) {
            result += `
            <tr>
                <td>${students[counter]}</td><td>${grades[counter]}</td>
            </tr>
            `;
        }

        myTable.innerHTML = result;
}
displayStudents();