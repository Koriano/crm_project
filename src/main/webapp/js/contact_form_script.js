document.getElementById("addPhone").onclick = addPhoneField;
document.getElementById("addMail").onclick = addMailField;
document.getElementById("reserved").onclick = hiddenReferent;

function addPhoneField() {
    let phone_inputs = document.getElementById("phoneInputs")

    let lastInput = phone_inputs.lastElementChild;

    let prefix = "phone";
    let newIndex = parseInt(lastInput.name.substring(prefix.length), 10);

    let newInput = document.createElement("input");
    newInput.type = "tel";
    newInput.name = "phone" + (newIndex+1);
    newInput.maxLength = 20;
    newInput.classList.add("form-control");
    newInput.classList.add("mt-2")
    newInput.placeholder = "01.23.45.67.89";

    phone_inputs.append(newInput);
}

function addMailField() {
    let mail_inputs = document.getElementById("mailInputs")

    let lastInput = mail_inputs.lastElementChild;

    let prefix = "mail";
    let newIndex = parseInt(lastInput.name.substring(prefix.length), 10);

    let newInput = document.createElement("input");
    newInput.type = "email";
    newInput.name = "mail" + (newIndex+1);
    newInput.maxLength = 60;
    newInput.classList.add("form-control");
    newInput.classList.add("mt-2")
    newInput.placeholder = "email@exemple.com";

    mail_inputs.append(newInput);
}

function hiddenReferent(){
    document.getElementById("referentFormPart").hidden = !document.getElementById("reserved").checked;
}

hiddenReferent();