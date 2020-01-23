import {setButtonStyleTo} from "./buttonStyle.js";

document.addEventListener("DOMContentLoaded", function () {

    const votingLinks = document.getElementsByClassName("link_big");

    console.log(votingLinks.length);

    for (let i=0; i<votingLinks.length; i++){
        setButtonStyleTo(votingLinks[i]);
    }

});
