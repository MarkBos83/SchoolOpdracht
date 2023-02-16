const myButton = document.getElementById("myButton");
const schoolAdded = document.getElementById("schoolAdded");
function addClass(text) {
    schoolAdded.innerHTML = text
    schoolAdded.classList.remove("hide");
    setTimeout(function () {
        schoolAdded.classList.add("hide");
    }, 2000);
};

async function addSchool() {
    const name = document.getElementById("name").value;
    const city = document.getElementById("city").value;
    if (name === "" || city === "") {
        addClass("not all required fields are filled in")
        return;
    }
    const data = {};
    data.name = name
    data.city = city
    dataJSON = JSON.stringify(data);
    document.getElementById("name").value = ""
    document.getElementById("city").value = ""
    addClass("school added");

    let res = await fetch("http://localhost:8080/api/school/new", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: dataJSON
    });
    await res.json();
}

async function getAllSchools() {
    const res = await fetch("http://localhost:8080/api/school/all");
    const data = await res.json();
    makeTable(data);
}

async function makeTable(data) {
    let tableBody = document.getElementById("table");
    tableBody.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].city}</td>
                        <td>${data[i].id}</td>
                   </tr>`;
        tableBody.innerHTML += row;
    }
}

async function getSchool() {
    const name = document.getElementById("school").value;
    let data = ""
    if (name === "") {
        data = [null]
        makeSchoolResults(data);
        return;
    }
    if (!isNaN(name)) {
        let res = await fetch(`http://localhost:8080/api/school/${name}`);
        data = await res.json();
        data = [data]
    } else {
        let res = await fetch(`http://localhost:8080/api/school/name/${name}`);
        data = await res.json();
        console.log(data)
    }
    makeSchoolResults(data);
}

async function makeSchoolResults(data) {
    let element = document.getElementById("searchresults");
    console.log(data)
    if (data.length === 0 || data[0] === null) {
        element.innerHTML = "No results";
        return;
    }

    element.innerHTML = "<ul>"
    for (let i = 0; i < data.length; i++) {
        element.innerHTML += `<li>school: ${data[i].name} <br>
        city: ${data[i].city} <br>
        number of students: ${data[i].numberOfStudents} <br>
        id: ${data[i].id}</li>`
    }
    element.innerHTML += '</ul>'
}