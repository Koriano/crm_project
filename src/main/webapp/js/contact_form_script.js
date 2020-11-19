document.getElementById("addPhone").onclick = addPhoneField;
document.getElementById("addMail").onclick = addMailField;
document.getElementById("reserved").onclick = hiddenReferent;

function addPhoneField(evt) {
    let button = evt.target;
    let lastInput = button.previousElementSibling;

    let prefix = "phone";
    let newIndex = parseInt(lastInput.name.substring(prefix.length), 10);

    let newInput = document.createElement("input");
    newInput.type = "tel";
    newInput.name = "phone" + (newIndex+1);
    newInput.size = 20;
    newInput.maxLength = 20;

    let br = document.createElement("br");

    lastInput.parentNode.insertBefore(br, lastInput.nextSibling);
    br.parentNode.insertBefore(newInput, br.nextSibling);
}

function addMailField(evt) {
    let button = evt.target;
    let lastInput = button.previousElementSibling;

    let prefix = "mail";
    let newIndex = parseInt(lastInput.name.substring(prefix.length), 10);

    let newInput = document.createElement("input");
    newInput.type = "email";
    newInput.name = "mail" + (newIndex+1);
    newInput.size = 20;
    newInput.maxLength = 60;

    let br = document.createElement("br");

    lastInput.parentNode.insertBefore(br, lastInput.nextSibling);
    br.parentNode.insertBefore(newInput, br.nextSibling);
}

function hiddenReferent(evt){
    document.getElementById("referentFormPart").hidden = !document.getElementById("reserved").checked;
}

hiddenReferent()