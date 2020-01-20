export function setButtonStyleTo(button){

    const backgroundColor = "forestgreen";
    const fontColor = "white";

    button.addEventListener("mouseover", function () {
        this.style.backgroundColor = backgroundColor;
        this.style.color = fontColor;
    });

    button.addEventListener("mouseout", function () {
        this.style.backgroundColor = "white";
        this.style.color = "black";
    });

}
