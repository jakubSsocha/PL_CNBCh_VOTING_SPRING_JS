export function validate(InputField) {

    const COLOR_POSITIVE = "green";
    const COLOR_NEGATIVE = "red";

    const HTML_INPUT_HANDLER = InputField.htmlHandler;
    const INPUT_TEXT_TO_VALID = HTML_INPUT_HANDLER.value;
    const REGEXP = InputField.regexp;
    const MIN_INPUT_TEXT_LENGTH = InputField.minInputLength;
    const HTML_ERRORMESSAGE_HANDLER = InputField.messageHandler;
    const ERRORMESSAGE = InputField.errorMessage;

    if (INPUT_TEXT_TO_VALID.match(REGEXP) &&
        inputTextLengthGreaterOrEqualThan(MIN_INPUT_TEXT_LENGTH)) {

        setHtmlInputBorder(COLOR_POSITIVE);
        print(null);
        return true;
    } else {

        setHtmlInputBorder(COLOR_POSITIVE);
        print(ERRORMESSAGE);
        return false;
    }

    function inputTextLengthGreaterOrEqualThan(min) {
        if (INPUT_TEXT_TO_VALID.length >= min) {
            return true;
        } else {
            return false;
        }
    }

    function setHtmlInputBorder(color) {
        HTML_INPUT_HANDLER.style.borderColor = color;
    }

    function setErrorMessageFont(color) {
        HTML_ERRORMESSAGE_HANDLER.style.color = color;
    }

    function print(errorMessage) {
        if (errorMessage === null) {
            HTML_ERRORMESSAGE_HANDLER.innerText = null;
        } else {
            setErrorMessageFont(COLOR_NEGATIVE);
            HTML_ERRORMESSAGE_HANDLER.innerText = errorMessage;
        }
    }
}
