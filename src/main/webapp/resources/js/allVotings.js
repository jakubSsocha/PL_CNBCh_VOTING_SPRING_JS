import {setButtonStyleTo} from "./buttonStyle.js";

document.addEventListener("DOMContentLoaded", function () {

    const goToAddVotingFormButton = document.getElementById("goToAddVotingForm");
    setButtonStyleTo(goToAddVotingFormButton);

    const buttons = document.getElementsByClassName("buttons");

    for(let i = 0; i < buttons.length; i++) {

        $(buttons[i]).one('click', function () {
            let VotingId = buttons[i].dataset.id;
            loadAndAddToPageGeneralVotingInformationFor(VotingId);
            loadAndAddAllUsersResultsFor(VotingId);
        });

        function loadAndAddToPageGeneralVotingInformationFor(VotingId) {

            $.ajax({
                url: "../voting/" + VotingId,
                data: {},
                type: "GET",
                dataType: "json"
            }).done(function (voting) {
                addToPage(voting);
            }).fail(function (xhr, status, err) {
            }).always(function (xhr, status) {
            });

            function addToPage(votingData) {
                document.getElementById("active" + VotingId).innerText = writeTAKorNIE(votingData.active);
                document.getElementById("secret" + VotingId).innerText = writeTAKorNIE(votingData.secret);
                document.getElementById("closed" + VotingId).innerText = writeTAKorNIE(votingData.closed);
                document.getElementById("createdDate" + VotingId).innerText = votingData.createdDate;
                document.getElementById("modificationDate" + VotingId).innerText = votingData.lastModificationDate;
                document.getElementById("closedDate" + VotingId).innerText = votingData.closedDate;
                document.getElementById("text" + VotingId).innerText = votingData.text;
            }
        }

        function loadAndAddAllUsersResultsFor(VotingId) {

            $.ajax({
                url: "../result/" + VotingId,
                data: {},
                type: "GET",
                dataType: "json"
            }).done(function (ResultsList) {
                addToPageUsers(ResultsList);
            }).fail(function (xhr, status, err) {
            }).always(function (xhr, status) {
            });

            function addToPageUsers(results){
                for(let i = 0; i < results.length; i++){
                    addToHtml(createFieldWithDataFor(results[i]));
                }
            }

            function addToHtml(NewUserField) {
                let usersContainer = document.getElementById("users" +VotingId);
                usersContainer.appendChild(NewUserField);
            }

            function createFieldWithDataFor(result) {
                let NewResultField = document.createElement("div");
                NewResultField.className = "user_list";
                NewResultField.innerText = result.user.firstName + " " + result.user.lastName + ", głos: " + write(result.vote);
                return NewResultField;
            }
        }
    }
});

function writeTAKorNIE(condition){
    if(condition === true){
        return "TAK";
    } else if(condition === false){
        return "NIE";
    }
}

function write(resultVote){
    if(resultVote === null){
        return "";
    }
    if(resultVote.length >= 15){
        return "głos oddany";
    }
        return resultVote;
}
