obj = {
    A: 0,
    B: 0,
    C: 0
};

function eqna_s(x1, y1, x2, y2) {

    let m = (y2 - y1) / (x2 - x1);

    let b = y1 - (m * x1);

    if (x1 === x2 && y1 === y2) {

        return null;

    } else if (x1 === x2) {

        obj.A = 1.0;
        obj.B = 0.0;
        obj.C = -x1;

    } else {
        obj.A = -m;
        obj.B = 1.0;
        obj.C = -b;

    }
    return obj;

}

console.log(eqna_s(3.0, 2.0, 1.0, 2.0));