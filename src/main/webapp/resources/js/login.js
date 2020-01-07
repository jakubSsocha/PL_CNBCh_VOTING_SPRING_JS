document.addEventListener("DOMContentLoaded", function () {

    const MIN_PASSWORD_LENGTH = 6;
    const MIN_EMAIL_LENGTH = 6;

    const EMAIL_FORM_INPUT = document.getElementById("loginInput_email");
    const PASSWORD_FORM_INPUT = document.getElementById("loginInput_password");
    const SUBMIT_FORM_BUTTON = document.getElementById("loginInput_submit");

    let isEmailValidationResultPositive = false;
    let isPasswordValidationResultPositive = false;

    EMAIL_FORM_INPUT.addEventListener("keyup", function () {
        isEmailValidationResultPositive = validate(EMAIL_FORM_INPUT);
    });

    PASSWORD_FORM_INPUT.addEventListener("keyup", function () {
        isPasswordValidationResultPositive = validate(PASSWORD_FORM_INPUT);
    });

    SUBMIT_FORM_BUTTON.addEventListener("click", function (event) {
        sendFormIf(allConditionsValid());
    });

    function sendFormIf(allConditionsValid) {
        if (allConditionsValid) {
            this.event.returnValue = true;
        } else {
            this.event.preventDefault();
        }
    }

    function allConditionsValid() {
        if (isEmailValidationResultPositive &&
            isPasswordValidationResultPositive) {
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
            paramUnderValidationValueLengthGreaterOrEqual(minInputLength, paramUnderValidationValue)) {

            setFormFieldPositiveStyle(inputUnderValidation, errorMessageHandler);
            return true;
        } else {
            setFormFieldNegativeStyle(inputUnderValidation, errorMessageHandler, errorMessageText);
            return false;
        }

        function setValidationParameters(inputUnderValidation) {
            switch (inputUnderValidation) {
                case EMAIL_FORM_INPUT:
                    regexp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    paramUnderValidationValue = EMAIL_FORM_INPUT.value;
                    minInputLength = MIN_EMAIL_LENGTH;
                    errorMessageHandler = document.getElementById("loginInput_email_validator");
                    errorMessageText = "Niepoprawny adres e-mail";
                    break;
                case PASSWORD_FORM_INPUT:
                    regexp = /(.*?)/;
                    paramUnderValidationValue = PASSWORD_FORM_INPUT.value;
                    minInputLength = MIN_PASSWORD_LENGTH;
                    errorMessageHandler = document.getElementById("loginInput_password_validator");
                    errorMessageText = "Hasło musi mieć minimum 6 znaków";
                    break;
            }
        }
    }

    function paramUnderValidationValueLengthGreaterOrEqual(min, paramUnderValidationValue) {
        if (paramUnderValidationValue.length >= min) {
            return true;
        } else {
            return false;
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

});
