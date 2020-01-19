import {validate} from "./formInputValidator.js";

document.addEventListener("DOMContentLoaded", function () {

    const MIN_STRING_LENGTH = 1;

    const NAME_FORM_INPUT = {
        htmlHandler: document.getElementById("name"),
        regexp: /(.*?)/,
        minInputLength: MIN_STRING_LENGTH,
        messageHandler: document.getElementById("name_validator"),
        errorMessage: "To pole nie może być puste"
    };

    const DESCRIPTION_FORM_INPUT = {
        htmlHandler: document.getElementById("description"),
        regexp: /(.*?)/,
        minInputLength: MIN_STRING_LENGTH,
        messageHandler: document.getElementById("description_validator"),
        errorMessage: "To pole nie może być puste"
    };

    const SUBMIT_FORM_BUTTON = document.getElementById("submitForm");

    let isNameValidationResultPositive = validate(NAME_FORM_INPUT);
    let isDescriptionValidationResultPositive = validate(DESCRIPTION_FORM_INPUT);

    NAME_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isNameValidationResultPositive = validate(NAME_FORM_INPUT);
    });

    DESCRIPTION_FORM_INPUT.htmlHandler.addEventListener("keyup", function () {
        isDescriptionValidationResultPositive = validate(DESCRIPTION_FORM_INPUT);
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
            if (isNameValidationResultPositive &&
                isDescriptionValidationResultPositive) {
                return true;
            }
            return false;
        }
    });

});
