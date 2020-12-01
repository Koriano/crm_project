document.getElementById("type").onclick = updateFormsDisplay;

function updateFormsDisplay(){
    var dropdown = document.getElementById("type");
    document.getElementById("siretDiv").hidden = !(dropdown.options[dropdown.selectedIndex].text === "Entreprise");
    document.getElementById("newTypeDiv").hidden = !(dropdown.options[dropdown.selectedIndex].text === "Nouveau type entite...");
}
updateFormsDisplay();