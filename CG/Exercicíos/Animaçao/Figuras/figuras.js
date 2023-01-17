function message(text) {
    let c = document.getElementById("console");
    c.innerHTML = text;
}

function begin_ref(dx, dy, sx, sy, a) {
    this.save();
    this.translate(dx, dy);
    this.scale(sx, sy);
    this.rotate(a);
}

function end_ref() {
    this.restore();
}

function start_animation(model) {
    let loop = (ts) => {
        model = this.update(model);
        this.render(model);
        requestAnimationFrame(loop);
    };
    requestAnimationFrame(loop);
}

function urect() {
    return [
        {x: -1, y: -1}, 
        {x:  1, y: -1},
        {x:  1, y:  1},
        {x: -1, y:  1}
    ]
}

function sector(num_points, start_angle, end_angle) {
    let step = (end_angle - start_angle) / (num_points - 1);
    let points = new Array(num_points);
    let angle = start_angle;
    for (let i = 0; i < num_points; i++) {
        points[i] = {
            x: Math.cos(angle),
            y: Math.sin(angle)
        };
        angle += step;
    }
    return points;
}

function pie(points) {
    let o = [{x: 0, y: 0}]
    return o.concat(points).concat(o);
}

function ident() {
    return {dx: 0, dy: 0, sx: 1, sy: 1, a: 0}
}

function tr(dx, dy, sx, sy, a) {
    return {
        dx: dx, dy: dy,
        sx: sx, sy: sy,
        a: a
    }
}
function scale(t, sx, sy) {
    t.sx = sx;
    t.sy = sy;
    return t;
}

function move(t, dx, dy) {
    t.dx = dx;
    t.dy = dy;
    return t;
}

function rot(t, a) {
    t.a = a;
    return t;
}

function draw_figure(fig) {
    if (fig.hasOwnProperty("transform")) {
        this.begin_ref(
            fig.transform.dx || 0,
            fig.transform.dy || 0,
            fig.transform.sx || 1,
            fig.transform.sx || 1,
            fig.transform.a || 0,
            );
    }

    let path = undefined;
    if (fig.hasOwnProperty("shape")) {
        path = new Path2D();
        path.moveTo(fig.shape[0].x, fig.shape[0].y);
        for (let p of fig.shape.slice(1)) {
            path.lineTo(p.x, p.y);
        }
    }

    if (fig.hasOwnProperty("style")) {
        if (fig.style.hasOwnProperty("lineWidth")) {
            this.lineWidth = fig.style.lineWidth;
        }
        if (fig.style.hasOwnProperty("fill")) {
            this.fillStyle = fig.style.fill;
            if (path !== undefined) this.fill(path);
        }
        if (fig.style.hasOwnProperty("stroke")) {
            this.strokeStyle = fig.style.stroke;
            if (path !== undefined) this.stroke(path);
        }
    }

    if (fig.hasOwnProperty("children")) {
        for (let child of fig.children) this.draw_figure(child);
    }

    if (fig.hasOwnProperty("transform")) this.end_ref();
}

function render(model) {
    let now = performance.now();
    let elapsed = now - model.time.last;
    model.time.last = now;

    let target_ts = now + 1000 / model.time.target_fps;
    while (performance.now() < target_ts) {};

    this.begin_ref(0,0, this.canvas.width, this.canvas.height, 0);

    this.draw_figure({
        transform: ident(),
        style: {fill: "Gainsboro", stroke: "Gainsboro", lineWidth: 0.00},
        shape: urect()
    });

    this.draw_figure({
        transform: tr(
            model.bounds.cx, model.bounds.cy,
            model.bounds.width, model.bounds.height, 0),
        shape: urect(),
        style: {fill: "SkyBlue"}
    });

    for (let fig of model.figures) {
        this.draw_figure(fig);
    }

    this.end_ref();
}

function expand(context) {
    context.begin_ref = begin_ref;
    context.end_ref = end_ref;
    context.render = render;
    context.update = update;
    context.draw_figure = draw_figure;
    context.start_animation = start_animation;
    context.canvas.width = 512;
    context.canvas.height= 512;
    return context;
}

function update(model) {
    //
    //  TIME
    //
    let now = performance.now();
    let dt = (now - model.time.last) * 0.001; // ELAPSED FRACTION OF A SECOND
    model.time.last = now;
    model.time.step++;
    //
    //  FIGURES  
    //
    model.figures[0].transform.a += dt * Math.PI;
    //
    //  MESSAGES
    //
    message(`STEP: ${model.time.step} ROOT FIGURES: ${model.figures.length}`);
    //
    return model;
}

function main() {
    let gc = document.getElementById("canvas").getContext("2d");
    gc = expand(gc);
    let model = {
        time: {
            start: performance.now(),
            last: performance.now(),
            step: 0,
            target_fps: 60,
         },
        bounds: {
            cx: 0.5,
            cy: 0.5,
            width: 0.49,
            height: 0.49
        },
        figures: [
            { 
                transform: {
                    dx: 0.5,
                    dy: 0.5,
                    sx: 0.1,
                    sy: 0.1,
                    a: 0
                },
                style: {
                    fill: "crimson",
                    stroke: "khaki",
                    lineWidth: 0.025,
                },
                shape: pie(sector(10, 0, 0.5 * Math.PI, true)),
                children: [
                    {   transform: tr(0, 0, 0.25, 0.25, 0),
                        shape: urect(),
                        style: {fill: "SeaGreen"}
                    }
                ]
            },
        ]
    };
    gc.start_animation(model);

}