let allStudentdata = ""

async function myfunc(){
    const results = await fetch("http://localhost:8080/api/student/all");
    allStudentdata = await results.json();
}

const myButton = document.getElementById("myButton");
const contactAdded = document.getElementById("contactAdded");
function addClass(text) {
    contactAdded.innerHTML = text
    contactAdded.classList.remove("hide");
    setTimeout(function () {
        contactAdded.classList.add("hide");
    }, 2000);
};

async function addContact() {
    const name = document.getElementById("name").value;
    const phonenumber = document.getElementById("phonenumber").value;
    const student = document.getElementById("student").value;

    if (name === "" || phonenumber === "" || student==="" || isNaN(student)) {
        addClass("not all required fields are filled correctly")
        return;
    }
    const data = {};
    data.name = name
    data.phoneNumber = phonenumber
    dataJSON = JSON.stringify(data);
    document.getElementById("name").value = ""
    document.getElementById("phonenumber").value = ""
    document.getElementById("student").value = ""
    if(student<1 || student> allStudentdata.length){
        addClass("not a valid student_id")
        return;
    }
    addClass("contact added");
    let res = await fetch(`http://localhost:8080/api/contact/${student}/new`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: dataJSON
    });
    await res.json();
}

async function getAllContacts() {
    const results = await fetch("http://localhost:8080/api/contact/all");
    const data = await results.json();
    makeTable(data);
}

async function makeTable(data) {
    let tableBody = document.getElementById("table");
    tableBody.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        let row = `<tr>
                        <td>${data[i].name}</td>
                        <td>${data[i].phoneNumber}</td>
                        <td>${data[i].id}</td>
                   </tr>`;
        tableBody.innerHTML += row;
    }
}

async function getContact() {
        const name = document.getElementById("contact").value;
        let data = ""
        if (!isNaN(name)) {
            let res = await fetch(`http://localhost:8080/api/contact/${name}`);
            data = await res.json();
            data = [data]
        } else {
            let res = await fetch(`http://localhost:8080/api/contact/name/${name}`);
            data = await res.json();
        }
        makeContactResults(data);
}

async function makeContactResults(data) {
        let element = document.getElementById("searchresults");
        if (data.length === 0 || data[0] === null) {
            element.innerHTML = "No results";
            return;
        }

        element.innerHTML = "<ul>"
        for (let i = 0; i < data.length; i++) {
            element.innerHTML += `<li>contact: ${data[i].name} <br>
            phonenumber: ${data[i].phoneNumber} <br>
            id: ${data[i].id}</li>`
        }
        element.innerHTML += '</ul>'
}