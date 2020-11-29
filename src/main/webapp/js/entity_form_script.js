document.getElementById("type").onclick = updateSiretFormDisplay;

function updateSiretFormDisplay(){
    var dropdown = document.getElementById("type");
    document.getElementById("siretDiv").hidden = !(dropdown.options[dropdown.selectedIndex].text === "Entreprise");
}

updateSiretFormDisplay()