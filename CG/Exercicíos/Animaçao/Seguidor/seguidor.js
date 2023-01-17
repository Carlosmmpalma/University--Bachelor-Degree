function message(text) {
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}

function clip(x, a, b) {
    return x < a ? a : (x > b ? b : x);
}

/*
    GRAPHICS SYSTEM
*/
function enter_ref(dx, dy, sx, sy, a) {
    this.save();
    this.translate(dx, dy);
    this.rotate(a);
    this.scale(sx, sy);
}

function leave_ref() { this.restore(); }

function render(model) {
    //
    message(`STATUS | ${model.age} steps | follower: ${model.follower.state} | ${model.hunted ? "HUNTED" : "ALIVE"}`);
    //
    //  MODEL REFERENTIAL
    //
    this.enter_ref(0,0,
        this.canvas.width, this.canvas.height, 0);
    //  BACKGROUND
    this.fillStyle = "khaki";
    this.fillRect(0,0, 1,1);
    // LEADER
    let leader = model.leader;
    this.enter_ref(leader.pos_x, leader.pos_y, leader.size, leader.size, 0);
    this.fillStyle = "crimson";
    this.fillRect(0,0,1,1);
    this.leave_ref();
    // FOLLOWER
    let follower = model.follower;
    this.enter_ref(follower.pos_x, follower.pos_y, follower.size, follower.size, 0);
    this.fillStyle = "seagreen";
    this.fillRect(0,0,1,1);
    this.leave_ref();
    // FOLLOWER
    //
    this.leave_ref();
}

/*
    MODEL
*/

function distance() {
    return Math.hypot(
        this.leader.pos_x - this.follower.pos_x, 
        this.leader.pos_y - this.follower.pos_y);
}

function update() {
    let leader = this.leader;
    let follower = this.follower;
    let distance = this.distance();
    if (distance < this.catch_distance) this.hunted = true;

    if (this.hunted) return;

    this.age += 1;
    //
    //  FOLLOWER
    //
    if (follower.dizziness > 0) {
        follower.dizziness -= 1;
        if (follower.dizziness === 0) follower.state = 0;
    } 
    else if (distance < this.near_distance) {
        follower.state = 2;
        follower.dizziness = follower.dizziness_timeout;
    }
    else if (leader.action !== 0 && follower.state === 0) follower.state = 1;

    if (follower.state == 1) { // HUNT
        follower.heading = Math.atan2(
            leader.pos_y - follower.pos_y,
            leader.pos_x - follower.pos_x);
    } else if (follower.state == 2) { // DAZZLED
        follower.heading += 0.5 * Math.PI * Math.random();
    }

    // FOLLOWER MOVEMENT
    if (follower.state !== 0) {
        let vel_x = follower.vel * Math.cos(follower.heading);
        let vel_y = follower.vel * Math.sin(follower.heading);

        follower.pos_x = clip(follower.pos_x + vel_x, 0, 1 - follower.size);
        follower.pos_y = clip(follower.pos_y + vel_y, 0, 1 - follower.size);
    }
    //
    //  LEADER
    //
    switch (leader.action) {
        case 1: /* UP */ leader.pos_y -= leader.vel_y; break;
        case 2: /* DOWN */ leader.pos_y += leader.vel_y; break;
        case 3: /* RIGHT */ leader.pos_x += leader.vel_x; break;
        case 4: /* LEFT */ leader.pos_x -= leader.vel_x; break;
        default: break;
    }
    leader.action = 0;
    //  BOUNCE ON BOUNDS
    leader.pos_x = clip(leader.pos_x, 0, 1 - leader.size);
    leader.pos_y = clip(leader.pos_y, 0, 1 - leader.size);
}

function new_model() {
    let model = {
        age: 0,
        leader: {
            pos_x: 0.5,
            pos_y: 0.5,
            vel_x: 0.025,
            vel_y: 0.025,
            size: 0.05,
            action: 0, // 0: STOP; 1: UP; 2: DOWN; 3: RIGHT; 4: UP
        },
        follower: {
            pos_x: 0.1, pos_y: 0.1,
            vel: 0.01, heading: 0,
            size: 0.03,
            dizziness: 0,
            dizziness_timeout: 50,
            state: 0    // 0: PAUSE; 1: HUNT; 2: DAZZLED 
        },
        near_distance: 0.075,
        catch_distance: 0.0125,
        hunted: false,
    };
    model.update = update;
    model.distance = distance;
    document.addEventListener("keydown", (e) => {
        switch (e.key) {
            case "w": case "W": model.leader.action = 1; break;
            case "s": case "S": model.leader.action = 2; break;
            case "d": case "D": model.leader.action = 3; break;
            case "a": case "A": model.leader.action = 4; break;
            default: model.leader.action = 0; break;
        }
    });

    return model;
}

/*
    MAIN
*/

function main() {
    let gc = document.getElementById("canvas").getContext("2d");
    gc.canvas.width = 256;
    gc.canvas.height = 256;

    gc.render = render;
    gc.enter_ref = enter_ref;
    gc.leave_ref = leave_ref;
    
    let model = new_model();
    let step = (ts) => {
        gc.render(model);
        model.update();
        requestAnimationFrame(step);
    }
    requestAnimationFrame(step);
}