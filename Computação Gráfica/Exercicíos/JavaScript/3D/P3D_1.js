function dist(p, q) {

    let f;

    f = Math.sqrt(Math.pow(q.x - p.x, 2) + Math.pow(q.y - p.y, 2) + Math.pow(q.z - p.z, 2));

    return f;
}

console.log(dist({
    x: 4,
    y: -8,
    z: -9
}, {
    x: 2,
    y: -3,
    z: -5
}));