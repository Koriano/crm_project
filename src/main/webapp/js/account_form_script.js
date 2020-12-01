document.getElementById("password_change").onclick = hiddenChangePassword;

function hiddenChangePassword(){
    console.log(document.getElementById("change_password_form_part"));
    console.log(document.getElementById("password_change"));

    document.getElementById("change_password_form_part").hidden = !document.getElementById("password_change").checked;
}

hiddenChangePassword();