document.addEventListener("DOMContentLoaded", function () {
    let firstName = document.getElementById("firstName");
    let lastName = document.getElementById("lastName");
    let email = document.getElementById("email");
    let password = document.getElementById("password");
    let confirmPassword = document.getElementById("password2");
    let hidePassword = document.getElementById("hidePassword");
    let submitForm = document.getElementById("submitForm");

    let firstNameValidator = false;
    let lastNameValidator = false;
    let emailValidator = false;
    let passwordValidator = false;
    let passwordComparator = false;

    submitForm.addEventListener("click", function (event) {
        if (allConditionsValid()) {
            $('#password2').prop('disabled', true);
            event.returnValue = true;
        } else {
            event.preventDefault();
        }
    });

    firstName.addEventListener("keyup", function () {
        let message = document.getElementById("firstName_validator");
        let firstNameFormat = /[A-Z][a-z]+/;
        if (firstName.value.match(firstNameFormat) && firstName.value.length > 2) {
            setPositiveStyle(firstName, message)
            firstNameValidator = true;
        } else {
            setNegativeStyle(firstName, message, "Błędne dane")
            firstNameValidator = false;
        }
    });

    lastName.addEventListener("keyup", function () {
        let message = document.getElementById("lastName_validator");
        let lastNameFormat = /[A-Z][a-zA-Z-]+/;
        if (lastName.value.match(lastNameFormat) && lastName.value.length > 2) {
            setPositiveStyle(lastName, message);
            lastNameValidator = true;
        } else {
            setNegativeStyle(lastName, message, "Błędne dane");
            lastNameValidator = false;
        }
    });

    email.addEventListener("keyup", function () {
        let message = document.getElementById("email_validator");
        let mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(email.value.match(mailformat)){
            setPositiveStyle(email,message);
            emailValidator = true;
        } else {
            setNegativeStyle(email, message, "To nie jest adres email");
            emailValidator = false;
        }
    });

    password.addEventListener("keyup", function () {
        let message = document.getElementById("password1_validator");
        if(password.value.length > 5){
            setPositiveStyle(password, message);
            passwordValidator = true;
        } else {
            setNegativeStyle(password, message, "Hasło musi mieć minimum 6 znaków");
            passwordValidator = false;
        }
    });

    confirmPassword.addEventListener("keyup", function () {
        let message = document.getElementById("password2_validator");
        if(confirmPassword.value.length > 5 && password.value === confirmPassword.value){
            setPositiveStyle(confirmPassword, message);
            passwordComparator = true;
        } else {
            setNegativeStyle(confirmPassword, message, "Podane hasła nie są takie same");
            passwordComparator = false;
        }
    });

    hidePassword.addEventListener("click", function () {
        if(hidePassword.checked){
            password.setAttribute("type", "password");
            confirmPassword.setAttribute("type", "password");
        } else {
            password.setAttribute("type", "text");
            confirmPassword.setAttribute("type", "text");
        }
    });

    function allConditionsValid() {
        if (firstNameValidator &&
            lastNameValidator &&
            emailValidator &&
            passwordValidator &&
            passwordComparator) {
            return true;
        }
        return false;
    }

    function setPositiveStyle(param, message) {
        param.style.borderColor = "green";
        message.innerText = null;
    }

    function setNegativeStyle(param, message, errortext) {
        param.style.borderColor = "red";
        message.style.color = "red";
        message.innerText = errortext;
    }



})
