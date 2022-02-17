function validate() {
    let name =
        document.forms["RegForm"]["fullname"];
    let email =
        document.forms["RegForm"]["email"];
    let password =
        document.forms["RegForm"]["password"];
    let confirmPassword =
        document.forms["RegForm"]["confirmPassword"];

    if (name.value === "") {
        document.getElementById("nameP").innerHTML += " Введіть ім'я";
        name.focus();
        return false;
    }

    if (email.value === "") {
        document.getElementById("emailP").innerHTML += " Введіть логін";
        email.focus();
        return false;
    }

    if (password.value === "") {
        document.getElementById("passwordP").innerHTML += " Введіть пароль";
        password.focus();
        return false;
    }

    if (confirmPassword.value === "") {
        document.getElementById("confPasswordP").innerHTML += " Введіть підтвердження пароля";
        confirmPassword.focus();
        return false;
    }

    if(password.value !== confirmPassword.value){
        document.getElementById("confPasswordP").innerHTML += " <br> Введіть однакові паролі";
        return false;
    }

    return true;
}