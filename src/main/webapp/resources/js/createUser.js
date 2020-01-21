import {validate} from "./formInputValidator.js";

document.addEventListener("DOMContentLoaded", function () {

    const COLOR_POSITIVE = "green";
    const COLOR_NEGATIVE = "red";

    const MIN_STRING_LENGTH = 3;
    const MIN_PASSWORD_LENGTH = 6;
    const MIN_EMAIL_LENGTH = 6;

    const FIRST_NAME_FORM_INPUT = {
        htmlHandler: document.getElementById("firstName"),
        regexp: /(.*?)/,
        minInputLength: MIN_STRING_LENGTH,
        messageHandler: document.getElementById("firstName_validator"),
        errorMessage: "To pole może zawierać tylko wielkie i małe litery"
    };

    const LAST_NAME_FORM_INPUT = {
        htmlHandler: document.getElementById("lastName"),
        regexp: /(.*?)/,
        minInputLength: MIN_STRING_LENGTH,
        messageHandler: document.getElementById("lastName_validator"),
        errorMessage: "To pole może zawierać tylko wielkie i małe litery oraz '-' "
    };

    const EMAIL_FORM_INPUT = {
        htmlHandler: document.getElementById("email"),
        regexp: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
        minInputLength: MIN_EMAIL_LENGTH,
        messageHandler: document.getElementById("email_validator"),
        errorMessage: "Niepoprawny adres e-mail"
    };

    const PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("password"),
        regexp: /(.*?)/,
        minInputLength: MIN_PASSWORD_LENGTH,
        messageHandler: document.getElementById("password1_validator"),
        errorMessage: "Hasło musi mieć minimum 6 znaków"
    };

    const SECOND_PASSWORD_FORM_INPUT = {
        htmlHandler: document.getElementById("password2"),
        errorHandler: document.getElementById("password2_validator"),
        errorMessage: "Podane hasła nie są takie same"

    };
    const SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX = document.getElementById("hidePassword");
    const SUBMIT_FORM_BUTTON = document.getElementById("submitForm");

    let isFirstNameValidationResultPositive = false;
    let isLastNameValidationResultPositive = false;
    let isEmailValidationResultPositive = false;
    let isPasswordValidationResultPositive = false;
    let isPasswordComparatorValidationResultPositive = false;

    FIRST_NAME_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isFirstNameValidationResultPositive = validate(FIRST_NAME_FORM_INPUT);
    });

    LAST_NAME_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isLastNameValidationResultPositive = validate(LAST_NAME_FORM_INPUT);
    });

    EMAIL_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isEmailValidationResultPositive = validate(EMAIL_FORM_INPUT);
    });

    PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isPasswordValidationResultPositive = validate(PASSWORD_FORM_INPUT);
    });

    SECOND_PASSWORD_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isPasswordComparatorValidationResultPositive = comparePasswords();
    });

    SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX.addEventListener("click", function () {
        makePasswordsPrivateOrPublic();

        function makePasswordsPrivateOrPublic() {
            if (passwordsArePrivate()) {
                makePrivate(PASSWORD_FORM_INPUT);
                makePrivate(SECOND_PASSWORD_FORM_INPUT);
            } else {
                makePublic(PASSWORD_FORM_INPUT);
                makePublic(SECOND_PASSWORD_FORM_INPUT);
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
            if (isFirstNameValidationResultPositive &&
                isLastNameValidationResultPositive &&
                isEmailValidationResultPositive &&
                isPasswordValidationResultPositive &&
                isPasswordComparatorValidationResultPositive) {
                return true;
            }
            return false;
        }
    });

    function comparePasswords() {

        if (passwordsAreEqual()) {
            setHtmlInputBorder(COLOR_POSITIVE);
            print(null);
            return true;
        } else {
            setHtmlInputBorder(COLOR_NEGATIVE);
            print(SECOND_PASSWORD_FORM_INPUT.errorMessage);
            return false;
        }

        function passwordsAreEqual() {
            return PASSWORD_FORM_INPUT.htmlHandler.value === SECOND_PASSWORD_FORM_INPUT.htmlHandler.value;
        }

        function setHtmlInputBorder(color) {
            SECOND_PASSWORD_FORM_INPUT.htmlHandler.style.borderColor = color;
        }

        function setErrorMessageFont(color) {
            SECOND_PASSWORD_FORM_INPUT.errorHandler.style.color = color;
        }

        function print(errorMessage) {
            if (errorMessage === null) {
               SECOND_PASSWORD_FORM_INPUT.errorHandler.innerText = null;
            } else {
                setErrorMessageFont(COLOR_NEGATIVE);
                SECOND_PASSWORD_FORM_INPUT.errorHandler.innerText = errorMessage;
            }
        }
    }

});
