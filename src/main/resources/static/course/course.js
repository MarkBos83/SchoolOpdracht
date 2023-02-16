const myButton = document.getElementById("myButton");
const courseAdded = document.getElementById("courseAdded");
function addClass(text) {
    courseAdded.innerHTML = text
    courseAdded.classList.remove("hide");
    setTimeout(function () {
        courseAdded.classList.add("hide");
    }, 2000);
};

async function addCourse() {
    const name = document.getElementById("name").value;
    const credits = document.getElementById("credits").value;

    if (name === "" || credits === "") {
        addClass("not all required fields are filled correctly")
        return;
    }
    const data = {};
    data.name = name
    data.credits = credits
    dataJSON = JSON.stringify(data);
    document.getElementById("name").value = ""
    document.getElementById("credits").value = ""
    addClass("course added");
    let res = await fetch(`http://localhost:8080/api/course/new`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: dataJSON
    });
    await res.json();
}

async function getAllCourses() {
    const results = await fetch("http://localhost:8080/api/course/all");
    const data = await results.json();
    makeTable(data);
}

async function makeTable(data) {
    let tableBody = document.getElementById("table");
    tableBody.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].credits}</td>
                        <td>${data[i].id}</td>
                   </tr>`;
        tableBody.innerHTML += row;
    }
}

async function getCourse() {
        const name = document.getElementById("course").value;
        let data = ""
        if (!isNaN(name)) {
            let res = await fetch(`http://localhost:8080/api/course/${name}`);
            data = await res.json();
            data = [data]
        } else {
            let res = await fetch(`http://localhost:8080/api/course/name/${name}`);
            data = await res.json();
        }
        makeCourseResults(data);
}

async function makeCourseResults(data) {
        let element = document.getElementById("searchresults");
        if (data.length === 0 || data[0] === null) {
            element.innerHTML = "No results";
            return;
        }

        element.innerHTML = "<ul>"
        for (let i = 0; i < data.length; i++) {
            element.innerHTML += `<li>course: ${data[i].name} <br>
            credits: ${data[i].credits} <br>
            id: ${data[i].id}</li>`
        }
        element.innerHTML += '</ul>'
}