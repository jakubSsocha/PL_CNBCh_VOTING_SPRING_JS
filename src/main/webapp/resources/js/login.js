import {validate} from "./formInputValidator.js";

document.addEventListener("DOMContentLoaded", function () {

    const MIN_PASSWORD_LENGTH = 6;
    const MIN_EMAIL_LENGTH = 6;

    const EMAIL_FORM_INPUT = {
        htmlHandler: document.getElementById("loginInput_email"),
        regexp: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
        minInputLength: MIN_EMAIL_LENGTH,
        messageHandler: document.getElementById("loginInput_email_validator"),
        errorMessage: "Niepoprawny adres e-mail"
    };

    const PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("loginInput_password"),
        regexp: /(.*?)/,
        minInputLength: MIN_PASSWORD_LENGTH,
        messageHandler: document.getElementById("loginInput_password_validator"),
        errorMessage: "Hasło musi mieć minimum 6 znaków"
    };

    const SUBMIT_BUTTON = document.getElementById("loginInput_submit");

    let isEmailValidationResultPositive = false;
    let isPasswordValidationResultPositive = false;

    EMAIL_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isEmailValidationResultPositive = validate(EMAIL_FORM_INPUT);
    });

    PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isPasswordValidationResultPositive = validate(PASSWORD_FORM_INPUT);
    });

    SUBMIT_BUTTON.addEventListener("click", function (event) {
        sendFormIf(allConditionsValid());

        function sendFormIf(allConditionsValid) {
            if (allConditionsValid) {
                event.returnValue = true;
            } else {
                event.preventDefault();
            }
        }

        function allConditionsValid() {
            if (isEmailValidationResultPositive &&
                isPasswordValidationResultPositive) {
                return true;
            } else {
                return false;
            }
        }
    });

});
