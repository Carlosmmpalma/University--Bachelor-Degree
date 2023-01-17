function message(text) {
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}
/**
 * MODEL
 */

function new_model() {
    let model = {
        age: 0,
        angle: 0.0,
        angle_step: 0.01 * Math.PI,
    }

    model.update = update;

    return model;
}

function update() {
    this.age += 1;
    this.angle += this.angle_step;
}
/**
 * CONTEXT
 */
function new_context() {
    let svg_object = document.getElementById("svg-object-angle");
    let x3d_object = document.getElementById("x3d-object");

    let context = {
        svg_object: svg_object,
        x3d_object: x3d_object,
    };

    context.render = render;

    return context;
}   

function render(model) {
    message(`AGE: ${model.age}`);
    this.x3d_object.setAttribute(
        "rotation", `0 0.5 1 ${model.angle}`);
    let deg = model.angle * 180.0 / Math.PI;
    this.svg_object.setAttribute(
        "transform", `rotate(${deg}, 64, 64)`);
}

/*
*   MAIN
*/
function main() {
    message("I'm Alive.");

    let gc = new_context();
    let model = new_model();

    let step = (ts) => {
        model.update();
        gc.render(model);
        requestAnimationFrame(step);
    }
    requestAnimationFrame(step);
}