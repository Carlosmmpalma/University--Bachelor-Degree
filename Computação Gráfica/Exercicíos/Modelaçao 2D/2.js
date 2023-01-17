function main() {
    let gc = document
        .getElementById("acanvas")
        .getContext("2d");
    gc.fillStyle = "steelblue";
    gc.fillRect(0, 0, 100, 100);
    gc.beginPath();
    gc.moveTo(0, 50);
    gc.arcTo(0, 0, 50, 0, 50);
    gc.moveTo(50, 0);
    gc.arcTo(100, 0, 100, 50, 50);
    gc.moveTo(100, 50);
    gc.arcTo(100, 100, 50, 100, 50);
    gc.moveTo(50, 100);
    gc.arcTo(0, 100, 0, 50, 50);

    gc.strokeStyle = "red";
    gc.stroke();
}