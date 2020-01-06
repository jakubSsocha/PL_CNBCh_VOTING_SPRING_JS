document.addEventListener("DOMContentLoaded", function () {

    let email = document.getElementById("loginInput_email");
    let password = document.getElementById("loginInput_password");
    let button = document.getElementById("loginInput_submit");

    let mailValidator = false;
    let passwordValidator = false;

    button.addEventListener("click", function (event) {
        if(mailValidator && passwordValidator){
            event.returnValue = true;
        } else {
            event.preventDefault();
        }
    });

    email.addEventListener("keyup", function () {
        let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        let validator = document.getElementById("loginInput_email_validator")
        if(email.value.match(mailformat)){
            email.style.borderColor = "green";
            validator.innerText = null;
            mailValidator = true;
        } else{
            email.style.borderColor = "red";
            validator.style.color = "red";
            validator.innerText = "Niepoprawny format adresu email";
        }
    });

    password.addEventListener("keyup", function () {
        let validator = document.getElementById("loginInput_password_validator")
        if(password.value.length > 6){
            password.style.borderColor = "green";
            validator.innerText = null;
            passwordValidator = true;
        } else {
            password.style.borderColor = "red";
            validator.style.color = "red";
            validator.innerText = "Hasło musi mieć minimum 6 znaków";
        }
    });

});
