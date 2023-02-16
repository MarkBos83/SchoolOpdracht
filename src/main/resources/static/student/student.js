const myButton = document.getElementById("myButton");
const studentAdded = document.getElementById("studentAdded");
function addClass(text) {
    studentAdded.innerHTML = text
    studentAdded.classList.remove("hide");
    setTimeout(function () {
        studentAdded.classList.add("hide");
    }, 2000);
};

async function addStudent() {
    const name = document.getElementById("name").value;
    const grade = document.getElementById("grade").value;
    let school = document.getElementById("school").value;

    if (name === "" || grade === "" || isNaN(grade) || isNaN(school)) {
        addClass("not all required fields are filled correctly")
        return;
    }
    const data = {};
    data.name = name
    data.grade = grade
    dataJSON = JSON.stringify(data);
    document.getElementById("name").value = ""
    document.getElementById("grade").value = ""
    document.getElementById("school").value = ""
    addClass("student added");
    if(school===""){
        school=9999
    }
    let res = await fetch(`http://localhost:8080/api/school/${school}/newstudent`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: dataJSON
    });
    await res.json();
}

async function getAllStudents() {
    const results = await fetch("http://localhost:8080/api/student/all");
    const data = await results.json();
    console.log(data)
    makeTable(data);
}

async function makeTable(data) {
    let tableBody = document.getElementById("table");
    tableBody.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].schoolname}</td>
                        <td>${data[i].grade}</td>
                        <td>${data[i].id}</td>
                   </tr>`;
        tableBody.innerHTML += row;
    }
}

async function getStudent() {
        const name = document.getElementById("student").value;
        let data = ""
        if (!isNaN(name)) {
            let res = await fetch(`http://localhost:8080/api/student/${name}`);
            data = await res.json();
            data = [data]
        } else {
            let res = await fetch(`http://localhost:8080/api/student/name/${name}`);
            data = await res.json();
        }
        makeStudentResults(data);
}

async function makeStudentResults(data) {
        let element = document.getElementById("searchresults");
        if (data.length === 0 || data[0] === null) {
            element.innerHTML = "No results";
            return;
        }

        element.innerHTML = "<ul>"
        for (let i = 0; i < data.length; i++) {
            element.innerHTML += `<li>student: ${data[i].name} <br>
            grade: ${data[i].grade} <br>
            school: ${data[i].schoolname} <br>
            id: ${data[i].id}</li>`
        }
        element.innerHTML += '</ul>'
}