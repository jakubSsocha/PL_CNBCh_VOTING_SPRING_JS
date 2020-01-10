document.addEventListener("DOMContentLoaded", function () {

    const buttons = document.getElementsByClassName("buttons");

    for(let i = 0; i < buttons.length; i++) {

        $(buttons[i]).one('click', function () {
            setBasicInformationForVotingWithButtonId(i);
            setAllResultForVotingWithButtonId(i);
        });

        function setBasicInformationForVotingWithButtonId(i) {

            let id = buttons[i].dataset.id;

            $.ajax({
                url: "../voting/" + id,
                data: {},
                type: "GET",
                dataType: "json"
            }).done(function (result) {
                document.getElementById("active" + id).innerText = writeTAKorNIE(result.active);
                document.getElementById("secret" + id).innerText = writeTAKorNIE(result.secret);
                document.getElementById("closed" + id).innerText = writeTAKorNIE(result.closed);
                document.getElementById("createdDate" + id).innerText = result.createdDate;
                document.getElementById("modificationDate" + id).innerText = result.lastModificationDate;
                document.getElementById("closedDate" + id).innerText = result.closedDate;
                document.getElementById("text" + id).innerText = result.text;
            }).fail(function (xhr, status, err) {
            }).always(function (xhr, status) {
                console.log("operacja wykonana");
            });
        }

        function setAllResultForVotingWithButtonId(i) {

            let id = buttons[i].dataset.id;

            $.ajax({
                url: "../result/" + id,
                data: {},
                type: "GET",
                dataType: "json"
            }).done(function (result) {
                createAndAddToHtmlUsersListFrom(result);
            }).fail(function (xhr, status, err) {
            }).always(function (xhr, status) {
                console.log("operacja wykonana");
            });

            function createAndAddToHtmlUsersListFrom(result){
                for(let u = 0; u < result.length; u++){
                    addToHtml(createFieldWithData(result[u]));
                }
            }

            function createFieldWithData(result) {
                var newDiv = document.createElement("div");
                newDiv.setAttribute("style","border-color: blue");
                newDiv.innerText = result.user.firstName + " " + result.user.lastName + " głos: " + result.vote;
                return newDiv;
            }

            function addToHtml(NewUserField) {
                let usersContainer = document.getElementById("users" +id);
                usersContainer.appendChild(NewUserField);
            }

        }
    }

    function writeTAKorNIE(condition){
        if(condition === true){
            return "TAK";
        } else if(condition === false){
            return "NIE";
        }
    }
});
