document.addEventListener("DOMContentLoaded", function () {

    const buttons = document.getElementsByClassName("buttons");

    for (let i = 0; i < buttons.length; i++) {

        $(buttons[i]).one('click', function () {
            let UserId = buttons[i].dataset.id;
            loadAndAddToPageExtendedInformationFor(UserId);
        });

    }

    function loadAndAddToPageExtendedInformationFor(UserId) {

        $.ajax({
            url: "../user/" + UserId,
            data: {},
            type: "GET",
            dataType: "json"
        }).done(function (user) {
            addToPage(user);
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        });

        function addToPage(user) {
            document.getElementById("status" + UserId).innerText = setStatus(user.active);
            document.getElementById("email" + UserId).innerText = user.email;
            document.getElementById("createdDate" + UserId).innerText = user.createdDate;
            writeRoles(user);
            writeResults(user);
        }

        function writeRoles(user) {
            let rolesContainer = document.getElementById("roles" + UserId);
            for (let i = 0; i < user.roleList.length; i++) {
                let NewResultField = document.createElement("p");
                NewResultField.className = "additionalOptions_text"
                NewResultField.innerText = user.roleList[i];
                rolesContainer.appendChild(NewResultField);
            }
        }

        function writeResults(user) {
            let rolesContainer = document.getElementById("results" + UserId);
            for (let i = 0; i < user.resultList.length; i++) {
                let NewResultField = document.createElement("li");
                NewResultField.innerText = user.resultList[i].voting.name +
                ": " + writeVote(user.resultList[i].vote);
                rolesContainer.appendChild(NewResultField);
            }
        }
    }

})
;

function setStatus(active) {
    if (active) {
        return "aktywny";
    } else {
        return "nieaktywny";
    }
}

function writeVote(vote) {
    if (vote == null) {
        return "";
    }
    if (vote.length > 15){
        return "głos oddany";
    }
    return vote;
}
