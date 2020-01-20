import {setButtonStyleTo} from "./buttonStyle.js";

document.addEventListener("DOMContentLoaded", function () {

    const votingLinks = document.getElementsByClassName("additionalOptions_container");

    for (let i=0; i<votingLinks.length; i++){
        setButtonStyleTo(votingLinks[i]);
    }

});
