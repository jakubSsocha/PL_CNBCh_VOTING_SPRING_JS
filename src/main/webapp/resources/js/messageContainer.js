document.addEventListener("DOMContentLoaded", function () {

    let handlerFadeOutTime = 5000;

    setTimeout(function() {
        $("#messageHandler").fadeOut().empty();
    }, handlerFadeOutTime);

})
