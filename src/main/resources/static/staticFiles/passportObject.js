function expandDeveloper() {
    var divDeveloper = document.getElementById("developer");
    var buttonDeveloper = document.getElementById("btnDeveloper");
    if (btnDeveloper.innerText === "V") {
        divDeveloper.removeAttribute("hidden");
        btnDeveloper.innerText = "^";
    } else {
        divDeveloper.setAttribute("hidden", "");
        btnDeveloper.innerText = "V";
    }
}
function expandBuilder() {
    var divBuilder = document.getElementById("builder");
    var buttonBuilder = document.getElementById("btnBuilder");
    if (buttonBuilder.innerText === "V") {
        divBuilder.removeAttribute("hidden");
        buttonBuilder.innerText = "^";
    } else {
        divBuilder.setAttribute("hidden", "");
        buttonBuilder.innerText = "V";
    }
}
function expandPreparer() {
    var divPreparer = document.getElementById("preparer");
    var buttonPreparer = document.getElementById("btnPreparer");
    if (buttonPreparer.innerText === "V") {
        divPreparer.removeAttribute("hidden");
        buttonPreparer.innerText = "^";
    } else {
        divPreparer.setAttribute("hidden", "");
        buttonPreparer.innerText = "V";
    }
}
function expandCustomer() {
    var divCustomer = document.getElementById("customer");
    var buttonCustomer = document.getElementById("btnCustomer");
    if (buttonCustomer.innerText === "V") {
        divCustomer.removeAttribute("hidden");
        buttonCustomer.innerText = "^";
    } else {
        divCustomer.setAttribute("hidden", "");
        buttonCustomer.innerText = "V";
    }
}
function expandContractor() {
    var divContractor = document.getElementById("contractor");
    var buttonContractor = document.getElementById("btnContractor");
    if (buttonContractor.innerText === "V") {
        divContractor.removeAttribute("hidden");
        buttonContractor.innerText = "^";
    } else {
        divContractor.setAttribute("hidden", "");
        buttonContractor.innerText = "V";
    }
}
function expandContractor2() {
    var divContractor2 = document.getElementById("contractor2");
    var buttonContractor2 = document.getElementById("btnContractor2");
    if (buttonContractor2.innerText === "V") {
        divContractor2.removeAttribute("hidden");
        buttonContractor2.innerText = "^";
    } else {
        divContractor2.setAttribute("hidden", "");
        buttonContractor2.innerText = "V";
    }
}
function expandProjector() {
    var divProjector = document.getElementById("projector");
    var buttonProjector = document.getElementById("btnProjector");
    if (buttonProjector.innerText === "V") {
        divProjector.removeAttribute("hidden");
        buttonProjector.innerText = "^";
    } else {
        divProjector.setAttribute("hidden", "");
        buttonProjector.innerText = "V";
    }
}
function expandAnotherPerson() {
    var divAnotherPerson = document.getElementById("anotherPerson");
    var buttonAnotherPerson = document.getElementById("btnAnotherPerson");
    if (buttonAnotherPerson.innerText === "V") {
        divAnotherPerson.removeAttribute("hidden");
        buttonAnotherPerson.innerText = "^";
    } else {
        divAnotherPerson.setAttribute("hidden", "");
        buttonAnotherPerson.innerText = "V";
    }
}