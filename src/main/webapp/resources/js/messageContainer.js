document.addEventListener("DOMContentLoaded", function () {

    let handlerFadeOutTime = 10000;

    setTimeout(function() {
        $("#messageHandler").fadeOut().empty();
    }, handlerFadeOutTime);

})
