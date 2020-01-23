document.addEventListener("DOMContentLoaded", function () {

    const modalButtons = document.getElementsByClassName("buttons");
    const userData = document.getElementById("user_data");
    const deactivateUserModal = document.getElementById("modal_button");

    for(let i=0; i<modalButtons.length;i++){
        modalButtons[i].addEventListener("click", function () {
            let UserId = modalButtons[i].dataset.id;
            deactivateUser(UserId);
        });
    }

    function deactivateUser(id){

        $.ajax({
            url: "../user/" + id,
            data: {},
            type: "GET",
            dataType: "json"
        }).done(function (user) {
            addToModal(user);
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        });

        function addToModal(user){
            userData.innerText = user.name;
            deactivateUserModal.addEventListener("click", function () {
                window.location.href='/user/deactivate/'+user.id;
            });
        }
    }

});
