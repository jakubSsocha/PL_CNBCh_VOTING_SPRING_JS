document.addEventListener("DOMContentLoaded", function () {

    const VOTING_OPTIONS_RADIOBUTTONS = document.getElementsByClassName("votingOptions");
    const SUBMIT_BUTTON = document.getElementById("button");

    let voteNotEmpty = false;

    SUBMIT_BUTTON.addEventListener("click", function (event) {
        voteNotEmpty = checkIfNotEmpty();
        sendFormIf(allConditionsValid());

        function checkIfNotEmpty() {
            for (var i = 0, length = VOTING_OPTIONS_RADIOBUTTONS.length; i < length; i++) {
                if (VOTING_OPTIONS_RADIOBUTTONS[i].checked) {
                    return true;
                }
            }
            return false;
        }

        function sendFormIf(allConditionsValid) {
            if (allConditionsValid) {
                event.returnValue = true;
            } else {
                event.preventDefault();
            }
        }

        function allConditionsValid() {
            if (voteNotEmpty) {
                return true;
            } else {
                return false;
            }
        }
    });

});
