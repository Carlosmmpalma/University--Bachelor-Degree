function message(text) {
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}

function clip(x, a, b) {
    return x < a ? a : (x > b ? b : x); 
}

function rand_ab(a, b) {
    return a + (b - a) * Math.random();
}

function rand_cr(c, r) {
    return c + r * (Math.random() * 2 - 1);
}

function range(n) {
    return [...Array(n).keys()];
}

function distance(a, b) {
    return Math.hypot(b.pos_x - a.pos_x, b.pos_y - a.pos_y);
}
/*
 ***** GRAPHICS SYSTEM *****
 */
function enter_ref(dx, dy, sx, sy, a) {
    this.save();
    this.translate(dx, dy);
    this.rotate(a);
    this.scale(sx, sy);
}

function leave_ref() {
    this.restore();
}

function render(model) {
    //
    let text = `${model.paused ? "PAUSED&nbsp;" : "RUNNING"} | AGE: ${model.age}`;
    if (model.paused) {
        let num_healthy = model.population.filter(p => p.state === 0).length;
        let num_infectious = model.population.filter(p => p.state === 1).length;
        let num_ill = model.population.filter(p => p.state === 2).length;
        text = `${text} | Healthy: ${num_healthy} Infectious: ${num_infectious} Ill: ${num_ill}`;
    }
    message(text);
    //
    this.enter_ref(0, 0, this.canvas.width, this.canvas.height, 0);
    this.fillStyle = "SkyBlue";
    this.fillRect(0, 0, 1, 1);
    //
    for (let p of model.population) {
        this.enter_ref(p.pos_x, p.pos_y, model.person_size, model.person_size, p.heading);
        this.fillStyle = p.state === 0 ? "seablue" : (p.state === 1 ? "seagreen" : "crimson");
        this.fillRect(0, 0, 1, 1);
        this.leave_ref();
    }
    //
    this.leave_ref();
}

function new_context() {
    let gc = document
        .getElementById("canvas")
        .getContext("2d");

    gc.render = render;
    gc.enter_ref = enter_ref;
    gc.leave_ref = leave_ref;
    gc.canvas.width = 256;
    gc.canvas.height = 256;

    return gc;
}
/*
 ***** PERSON *****
 */
function new_person() {
    let person = {
        pos_x: rand_cr(0.5, 0.45),
        pos_y: rand_cr(0.5, 0.45),
        vel: 0.1,
        heading: rand_ab(0, 2 * Math.PI),
        state: 0, // 0: healthy, 1: infectious, 2: ill
    };

    person.update = person_update;

    return person;
}

function person_update(model) {
    switch (this.state) {
        case 0: // healthy
            break;
        case 1: // infectious
            if (rand_ab(0, 1) <= model.p_h_inf) {
                this.state = 0;
                break;
            }

            if (rand_ab(0, 1) <= model.p_ill_inf) {
                this.state = 2;
                break;
            }
            break;
        case 2: // ill
            if (rand_ab(0, 1) <= model.p_h_ill) {
                this.state = 0;
                break;
            }

            if (rand_ab(0, 1) <= model.p_inf_ill) {
                this.state = 1;
                break;
            }
            break;
        default:
            break;
    }

    let vel = this.vel;
    switch (this.state) {
        case 1: // infectious
            vel = 1.5 * vel;
            break;
        case 2: // ill
            vel = 0.1 * vel;
        default:
            break;
    }
    this.heading += rand_cr(0, Math.PI * 8);
    this.pos_x = clip(this.pos_x + vel * Math.cos(this.heading), 0, 1);
    this.pos_y = clip(this.pos_y + vel * Math.sin(this.heading), 0, 1);
}
/*
 ***** MODEL *****
 */
function new_model() {
    let model = {
        age: 0,
        paused: false, // 0: PAUSED, 1: RUNNING
        person_size: 0.02,
        infection_distance: 0.1,
        infection_prob: 0.7,
        p_h_inf: 0.01,
        p_ill_inf: 0.02,
        p_h_ill: 0.001,
        p_inf_ill: 0.02,
        population: range(100).map(i => new_person()),
    };

    model.population[0].state = 1;

    model.update = update;

    document.addEventListener("keypress", (e) => {
        if (e.key === " ") model.paused = !model.paused;
    });

    return model;
}

function update() {
    if (this.paused) return;
    this.age++;

    this.population.forEach(p => p.update(this));

    let healthy = this.population.filter(p => p.state === 0);
    let infectious = this.population.filter(p => p.state === 1);
    let ill = this.population.filter(p => p.state === 2);

    for (let p of healthy) {
        let near = infectious.filter(q => distance(p, q) < this.infection_distance);
        for (let q of near) {
            if (rand_ab(0, 1) <= this.infection_prob) p.state = 1;
            if (p.state === 1) break;
        }
    }
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