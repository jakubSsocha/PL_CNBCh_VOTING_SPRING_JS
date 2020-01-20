import {validate} from "./formInputValidator.js";

document.addEventListener("DOMContentLoaded", function () {

    const COLOR_POSITIVE = "green";
    const COLOR_NEGATIVE = "red";

    const MIN_PASSWORD_LENGTH = 6;

    const OLD_PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("oldPassword"),
        regexp: /(.*?)/,
        minInputLength: MIN_PASSWORD_LENGTH,
        messageHandler: document.getElementById("oldPassword_validator"),
        errorMessage: "Hasło musi mieć minimum 6 znaków"
    };

    const NEW_PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("newPassword"),
        regexp: /(.*?)/,
        minInputLength: MIN_PASSWORD_LENGTH,
        messageHandler: document.getElementById("newPassword_validator"),
        errorMessage: "Hasło musi mieć minimum 6 znaków"
    };

    const REPEATED_NEW_PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("repeatedNewPassword"),
        regexp: /(.*?)/,
        minInputLength: MIN_PASSWORD_LENGTH,
        messageHandler: document.getElementById("repeatedNewPassword_validator"),
        errorMessage: "Hasło musi mieć minimum 6 znaków"
    };

    const SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX = document.getElementById("hidePassword");
    const SUBMIT_FORM_BUTTON = document.getElementById("submitForm");

    let isOldPasswordValidationResultPositive = false;
    let isNewPasswordValidationResultPositive = false;
    let isRepeatedNewPasswordValidationResultPositive = false;

    OLD_PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isOldPasswordValidationResultPositive = validate(OLD_PASSWORD_FORM_INPUT);
    });

    NEW_PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isNewPasswordValidationResultPositive = validate(NEW_PASSWORD_FORM_INPUT);
    });

    REPEATED_NEW_PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isRepeatedNewPasswordValidationResultPositive = validate(REPEATED_NEW_PASSWORD_FORM_INPUT);
        isRepeatedNewPasswordValidationResultPositive = comparePasswords();

        function comparePasswords() {

            if (passwordsAreEqual()) {
                setHtmlInputBorder(COLOR_POSITIVE);
                print(null);
                return true;
            } else {
                setHtmlInputBorder(COLOR_NEGATIVE);
                print("Hasła nie są takie same");
                return false;
            }

            function passwordsAreEqual() {
                return NEW_PASSWORD_FORM_INPUT.htmlHandler.value === REPEATED_NEW_PASSWORD_FORM_INPUT.htmlHandler.value;
            }

            function setHtmlInputBorder(color) {
                REPEATED_NEW_PASSWORD_FORM_INPUT.htmlHandler.style.borderColor = color;
            }

            function setErrorMessageFont(color) {
                REPEATED_NEW_PASSWORD_FORM_INPUT.errorHandler.style.color = color;
            }

            function print(errorMessage) {
                if (errorMessage === null) {
                    REPEATED_NEW_PASSWORD_FORM_INPUT.errorHandler.innerText = null;
                } else {
                    setErrorMessageFont(COLOR_NEGATIVE);
                    REPEATED_NEW_PASSWORD_FORM_INPUT.errorHandler.innerText = errorMessage;
                }
            }
        }
    });

    SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX.addEventListener("click", function () {
        makePasswordsPrivateOrPublic();

        function makePasswordsPrivateOrPublic() {
            if (passwordsArePrivate()) {
                makePrivate(OLD_PASSWORD_FORM_INPUT);
                makePrivate(NEW_PASSWORD_FORM_INPUT);
                makePrivate(REPEATED_NEW_PASSWORD_FORM_INPUT);
            } else {
                makePublic(OLD_PASSWORD_FORM_INPUT);
                makePublic(NEW_PASSWORD_FORM_INPUT);
                makePublic(REPEATED_NEW_PASSWORD_FORM_INPUT);
            }
        }

        function passwordsArePrivate() {
            if (SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX.checked) {
                return true;
            } else {
                return false;
            }
        }

        function makePrivate(password) {
            password.htmlHandler.setAttribute("type", "password")
        }

        function makePublic(password) {
            password.htmlHandler.setAttribute("type", "text");
        }
    });

    SUBMIT_FORM_BUTTON.addEventListener("click", function (event) {
        sendFormIf(allConditionsValid());

        function sendFormIf(allConditionsValid) {
            if (allConditionsValid) {
                disableConfirmPasswordInputSending()
                event.returnValue = true;
            } else {
                event.preventDefault();
            }

            function disableConfirmPasswordInputSending() {
                $('#password2').prop('disabled', true);
            }
        }

        function allConditionsValid() {
            if (isOldPasswordValidationResultPositive &&
                isNewPasswordValidationResultPositive &&
                isRepeatedNewPasswordValidationResultPositive) {
                return true;
            }
            return false;
        }
    });


});
