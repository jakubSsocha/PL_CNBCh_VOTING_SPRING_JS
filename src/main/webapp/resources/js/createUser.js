document.addEventListener("DOMContentLoaded", function () {

    const MIN_STRING_LENGTH = 3;
    const MIN_PASSWORD_LENGTH = 6;
    const MIN_EMAIL_LENGTH = 6;

    const FIRST_NAME_FORM_INPUT = document.getElementById("firstName");
    const LAST_NAME_FORM_INPUT = document.getElementById("lastName");
    const EMAIL_FORM_INPUT = document.getElementById("email");
    const PASSWORD_FORM_INPUT = document.getElementById("password");
    const CONFIRM_PASSWORD_FORM_INPUT = document.getElementById("password2");
    const SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX = document.getElementById("hidePassword");
    const SUBMIT_FORM_BUTTON = document.getElementById("submitForm");

    let isFirstNameValidationResultPositive = false;
    let isLastNameValidationResultPositive = false;
    let isEmailValidationResultPositive = false;
    let isPasswordValidationResultPositive = false;
    let isPasswordComparatorValidationResultPositive = false;

    FIRST_NAME_FORM_INPUT.addEventListener("keyup", function () {
        isFirstNameValidationResultPositive = validate(FIRST_NAME_FORM_INPUT);
    });

    LAST_NAME_FORM_INPUT.addEventListener("keyup", function () {
        isLastNameValidationResultPositive = validate(LAST_NAME_FORM_INPUT);
    });

    EMAIL_FORM_INPUT.addEventListener("keyup", function () {
        isEmailValidationResultPositive = validate(EMAIL_FORM_INPUT);
    });

    PASSWORD_FORM_INPUT.addEventListener("keyup", function () {
        isPasswordValidationResultPositive = validate(PASSWORD_FORM_INPUT);
    });

    CONFIRM_PASSWORD_FORM_INPUT.addEventListener("keyup", function () {
        isPasswordComparatorValidationResultPositive = comparePasswords();
    });

    SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX.addEventListener("click", function () {
        if (SHOW_OR_HIDE_PASSWORDS_FORM_CHECKBOX.checked) {
            PASSWORD_FORM_INPUT.setAttribute("type", "password");
            CONFIRM_PASSWORD_FORM_INPUT.setAttribute("type", "password");
        } else {
            PASSWORD_FORM_INPUT.setAttribute("type", "text");
            CONFIRM_PASSWORD_FORM_INPUT.setAttribute("type", "text");
        }
    });

    SUBMIT_FORM_BUTTON.addEventListener("click", function (event) {
        sendFormIf(allConditionsValid());
    });

    function sendFormIf(allConditionsValid) {
        if (allConditionsValid) {
            disableConfirmPasswordInputSending()
            this.event.returnValue = true;
        } else {
            this.event.preventDefault();
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

    function validate(inputUnderValidation) {

        let regexp;
        let paramUnderValidationValue;
        let minInputLength;
        let errorMessageHandler;
        let errorMessageText;

        setValidationParameters(inputUnderValidation);

        if (paramUnderValidationValue.match(regexp) &&
            paramUnderValidationValueLengthGreaterOrEqual(minInputLength)) {

            setFormFieldPositiveStyle(inputUnderValidation, errorMessageHandler);
            return true;
        } else {
            setFormFieldNegativeStyle(inputUnderValidation, errorMessageHandler, errorMessageText);
            return false;
        }

        function setValidationParameters(inputUnderValidation) {
            switch (inputUnderValidation) {
                case FIRST_NAME_FORM_INPUT:
                    regexp = /[A-Z][a-z]+/;
                    paramUnderValidationValue = FIRST_NAME_FORM_INPUT.value;
                    minInputLength = MIN_STRING_LENGTH;
                    errorMessageHandler = document.getElementById("firstName_validator");
                    errorMessageText = "To pole może zawierać tylko wielkie i małe litery";
                    break;
                case LAST_NAME_FORM_INPUT:
                    regexp = /[A-Z][a-zA-Z-]+/;
                    paramUnderValidationValue = LAST_NAME_FORM_INPUT.value;
                    minInputLength = MIN_STRING_LENGTH;
                    errorMessageHandler = document.getElementById("lastName_validator");
                    errorMessageText = "To pole może zawierać tylko wielkie i małe litery oraz '-' ";
                    break;
                case EMAIL_FORM_INPUT:
                    regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    paramUnderValidationValue = EMAIL_FORM_INPUT.value;
                    minInputLength = MIN_EMAIL_LENGTH;
                    errorMessageHandler = document.getElementById("email_validator");
                    errorMessageText = "Niepoprawny adres e-mail";
                    break;
                case PASSWORD_FORM_INPUT:
                    regexp = /(.*?)/;
                    paramUnderValidationValue = PASSWORD_FORM_INPUT.value;
                    minInputLength = MIN_PASSWORD_LENGTH;
                    errorMessageHandler = document.getElementById("password1_validator");
                    errorMessageText = "Hasło musi mieć minimum 6 znaków";
                    break;
            }
        }

        function paramUnderValidationValueLengthGreaterOrEqual(min) {
            if (paramUnderValidationValue.length >= min) {
                return true;
            } else {
                return false;
            }
        }
    }

    function comparePasswords() {
        let errorMessageHandler = document.getElementById("password2_validator");
        if (passwordsAreEqual()) {
            setFormFieldPositiveStyle(CONFIRM_PASSWORD_FORM_INPUT, errorMessageHandler);
            return true;
        } else {
            setFormFieldNegativeStyle(CONFIRM_PASSWORD_FORM_INPUT, errorMessageHandler, "Podane hasła nie są takie same");
            return  false;
        }

        function passwordsAreEqual() {
            return PASSWORD_FORM_INPUT.value === CONFIRM_PASSWORD_FORM_INPUT.value;
        }
    }

    function setFormFieldPositiveStyle(inputFormField, validationFailMessageHandler) {
        inputFormField.style.borderColor = "green";
        validationFailMessageHandler.innerText = null;
    }

    function setFormFieldNegativeStyle(inputFormField, validationFailMessageHandler, validationFailMessageText) {
        inputFormField.style.borderColor = "red";
        validationFailMessageHandler.style.color = "red";
        validationFailMessageHandler.innerText = validationFailMessageText;
    }

})
