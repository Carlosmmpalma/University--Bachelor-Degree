//RENDER
var renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);


//CAMARA
var camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 500);
camera.position.set(0, 0, 250);
camera.lookAt(0, 0, 0);
var angle = 0;
var radius = 200;


//CENA
var scene = new THREE.Scene();


/*
    FUNDO
*/
//PARDES
var fundo2 = new THREE.PlaneGeometry(10000, 10000, 100, 100);
var fundo_material2 = new THREE.MeshLambertMaterial({ color: 0xcaf1de });
var fundo_mesh2 = new THREE.Mesh(fundo2, fundo_material2);
fundo_mesh2.position.z = -200;
scene.add(fundo_mesh2);

var fundo3 = new THREE.PlaneGeometry(10000, 10000, 100, 100);
var fundo_material3 = fundo_material2;
var fundo_mesh3 = new THREE.Mesh(fundo3, fundo_material3);
fundo_mesh3.rotation.x = Math.PI;
fundo_mesh3.position.y = 200;
fundo_mesh3.position.z = 200;
scene.add(fundo_mesh3);


//CHAO
var fundo = new THREE.PlaneGeometry(10000, 10000, 100, 100);
var fundo_material = new THREE.MeshLambertMaterial({ color: 0xacddde });
var fundo_mesh = new THREE.Mesh(fundo, fundo_material);
fundo_mesh.rotation.x = -90 * Math.PI / 180;
fundo_mesh.position.y = -30;
scene.add(fundo_mesh);


//LUZ
var light = new THREE.PointLight(0xFFFFFF, 1, 600);
light.position.set(-50, 0, 100);
scene.add(light);


//EXTRUSAO
var extrudeSettings = {
    steps: 1,
    depth: 1,
    bevelEnabled: true,
    bevelThickness: 1,
    bevelSize: 0,
    bevelOffset: 0,
    bevelSegments: 1
}


/*
    MODELO 3D DA PALAVRA : Bairrada
*/
function letterB() {
    //Letra B
    var path_B = new THREE.Shape();
    path_B.lineTo(0, 20);
    path_B.moveTo(0, 10);
    path_B.quadraticCurveTo(17, 5, 7, -2);
    path_B.quadraticCurveTo(2, -2.5, 0, 0);
    path_B.lineTo(0, 2);
    path_B.moveTo(0, 20);
    path_B.quadraticCurveTo(17, 15, 6, 8);
    path_B.quadraticCurveTo(2, 7.5, 0, 11);
    path_B.moveTo(1, 13);
    path_B.bezierCurveTo(2, -12, 15, 5, 1, 8);

    var geometry = new THREE.ExtrudeGeometry(path_B, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_B = new THREE.Mesh(geometry, material);


    var targetPositionB = new THREE.Vector3(-80, 0, 0);
    var tweenB = new TWEEN.Tween(path_B.position).to(targetPositionB, 1000);
    tweenB.start();

    scene.add(path_B);
}


function letterA() {
    //Letra A
    var path_A = new THREE.Shape();
    path_A.moveTo(0, 0);
    path_A.quadraticCurveTo(0.5, -2, 3, -3);
    path_A.lineTo(5, -1);
    path_A.quadraticCurveTo(3, -1, 3, 0);
    path_A.lineTo(3, 13);
    path_A.lineTo(4, 14);
    path_A.lineTo(-2, 17); //(-2,-3)
    path_A.quadraticCurveTo(-8, 15, -9, 14);
    path_A.lineTo(-8, 13);
    path_A.lineTo(-5, 15);
    path_A.lineTo(0, 14);
    path_A.lineTo(0, 0);

    path_A.moveTo(0, 10);
    path_A.quadraticCurveTo(-17, 5, -6, -3);
    path_A.quadraticCurveTo(-2, -2.5, 0, 0);
    path_A.lineTo(0, 2);
    path_A.bezierCurveTo(-10, -4, -10, 8, 0, 8);

    var geometry = new THREE.ExtrudeGeometry(path_A, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_A = new THREE.Mesh(geometry, material);

    path_A.position.set(-300, 0, 0);

    var targetPositionA = new THREE.Vector3(-50, 0, 0);
    var tweenA = new TWEEN.Tween(path_A.position).to(targetPositionA, 1000);
    tweenA.start();


    scene.add(path_A);
}


function letteri() {
    //Letra i
    var path_i = new THREE.Shape();
    path_i.moveTo(2, 0);
    path_i.lineTo(2, 18);
    path_i.lineTo(-2, 18);
    path_i.lineTo(-2, 0);

    var path_i1 = new THREE.Shape();
    path_i1.arc(0, 25, 4, 0, 2 * Math.PI);


    var geometry = new THREE.ExtrudeGeometry(path_i, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_i = new THREE.Mesh(geometry, material);

    var geometry = new THREE.ExtrudeGeometry(path_i1, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_i1 = new THREE.Mesh(geometry, material);

    path_i.position.set(-300, -3, 0);
    path_i1.position.set(-300, -3, 0);


    var targetPositioni = new THREE.Vector3(-30, -3, 0);
    var tweeni = new TWEEN.Tween(path_i.position).to(targetPositioni, 1000);
    tweeni.start();

    var targetPositioni1 = new THREE.Vector3(-30, -3, 0);
    var tweeni1 = new TWEEN.Tween(path_i1.position).to(targetPositioni1, 1000);
    tweeni1.start();


    scene.add(path_i);
    scene.add(path_i1);
}


function letterR() {
    //Letra R
    var path_R = new THREE.Shape();
    path_R.moveTo(0, 0);
    path_R.lineTo(0, 20);
    path_R.lineTo(2, 20);
    path_R.lineTo(2, 18);
    path_R.quadraticCurveTo(5, 20, 15, 20);
    path_R.lineTo(15, 18);
    path_R.quadraticCurveTo(5, 16, 2, 10);
    path_R.lineTo(2, 0);


    var geometry = new THREE.ExtrudeGeometry(path_R, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });;
    var path_R = new THREE.Mesh(geometry, material);


    path_R.position.set(-300, -3, 0);


    var targetPositionR = new THREE.Vector3(-20, -3, 0);
    var tweenR = new TWEEN.Tween(path_R.position).to(targetPositionR, 1000);
    tweenR.start();


    scene.add(path_R);
}


function letterR1() {
    //Letra R
    var path_R1 = new THREE.Shape();
    path_R1.moveTo(0, 0);
    path_R1.lineTo(0, 20);
    path_R1.lineTo(2, 20);
    path_R1.lineTo(2, 18);
    path_R1.quadraticCurveTo(5, 20, 15, 20);
    path_R1.lineTo(15, 18);
    path_R1.quadraticCurveTo(5, 16, 2, 10);
    path_R1.lineTo(2, 0);


    var geometry = new THREE.ExtrudeGeometry(path_R1, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_R1 = new THREE.Mesh(geometry, material);


    path_R1.position.set(-300, -3, 0);


    var targetPositionR1 = new THREE.Vector3(0, -3, 0);
    var tweenR1 = new TWEEN.Tween(path_R1.position).to(targetPositionR1, 1000);
    tweenR1.start();


    scene.add(path_R1);
}


function letterA1() {
    //Letra A
    var path_A1 = new THREE.Shape();
    path_A1.moveTo(0, 0);
    path_A1.quadraticCurveTo(0.5, -2, 3, -3);
    path_A1.lineTo(5, -1);
    path_A1.quadraticCurveTo(3, -1, 3, 0);
    path_A1.lineTo(3, 13);
    path_A1.lineTo(4, 14);
    path_A1.lineTo(-2, 17); //(-2,-3)
    path_A1.quadraticCurveTo(-8, 15, -9, 14);
    path_A1.lineTo(-8, 13);
    path_A1.lineTo(-5, 15);
    path_A1.lineTo(0, 14);
    path_A1.lineTo(0, 0);

    path_A1.moveTo(0, 10);
    path_A1.quadraticCurveTo(-17, 5, -6, -3);
    path_A1.quadraticCurveTo(-2, -2.5, 0, 0);
    path_A1.lineTo(0, 2);
    path_A1.bezierCurveTo(-10, -4, -10, 8, 0, 8);


    var geometry = new THREE.ExtrudeGeometry(path_A1, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_A1 = new THREE.Mesh(geometry, material);


    path_A1.position.set(-300, 0, 0);


    var targetPositionA1 = new THREE.Vector3(28, 0, 0);
    var tweenA1 = new TWEEN.Tween(path_A1.position).to(targetPositionA1, 1000);
    tweenA1.start();


    scene.add(path_A1);
}


function letterD() {
    //Letra D
    var path_D = new THREE.Shape();
    path_D.moveTo(0, 0);
    path_D.quadraticCurveTo(0.5, -2, 3, -3);
    path_D.lineTo(5, -1);
    path_D.quadraticCurveTo(3, -1, 3, 0);
    path_D.lineTo(3, 24);
    path_D.lineTo(-2, 22);
    path_D.quadraticCurveTo(0, 21.5, 0, 20);

    path_D.lineTo(0, 14);
    path_D.lineTo(-5, 16);
    path_D.bezierCurveTo(-14, 10, -14, 1, -3, -3);
    path_D.quadraticCurveTo(-1, -2, 0, 0);
    path_D.lineTo(0, 2);
    path_D.bezierCurveTo(-8, -5, -13, 10, -4, 14);
    path_D.lineTo(0, 12);
    path_D.lineTo(0, 2);


    var geometry = new THREE.ExtrudeGeometry(path_D, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_D = new THREE.Mesh(geometry, material);

    path_D.position.set(-300, 0, 0);

    var targetPositionD = new THREE.Vector3(50, 0, 0);
    var tweenD = new TWEEN.Tween(path_D.position).to(targetPositionD, 1000);
    tweenD.start();

    scene.add(path_D);
}


function letterA2() {
    //Letra A
    var path_A2 = new THREE.Shape();
    path_A2.moveTo(0, 0);
    path_A2.quadraticCurveTo(0.5, -2, 3, -3);
    path_A2.lineTo(5, -1);
    path_A2.quadraticCurveTo(3, -1, 3, 0);
    path_A2.lineTo(3, 13);
    path_A2.lineTo(4, 14);
    path_A2.lineTo(-2, 17); //(-2,-3)
    path_A2.quadraticCurveTo(-8, 15, -9, 14);
    path_A2.lineTo(-8, 13);
    path_A2.lineTo(-5, 15);
    path_A2.lineTo(0, 14);
    path_A2.lineTo(0, 0);

    path_A2.moveTo(0, 10);
    path_A2.quadraticCurveTo(-17, 5, -6, -3);
    path_A2.quadraticCurveTo(-2, -2.5, 0, 0);
    path_A2.lineTo(0, 2);
    path_A2.bezierCurveTo(-10, -4, -10, 8, 0, 8);


    var geometry = new THREE.ExtrudeGeometry(path_A2, extrudeSettings);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF7F50 });
    var path_A2 = new THREE.Mesh(geometry, material);


    path_A2.position.set(-300, 0, 0);


    var targetPositionA2 = new THREE.Vector3(68, 0, 0);
    var tweenA2 = new TWEEN.Tween(path_A2.position).to(targetPositionA2, 1000);
    tweenA2.start();



    scene.add(path_A2);
}


/*
    ANIMAÇÃO
*/
function animate(time) {
    requestAnimationFrame(animate);

    if (TWEEN.update(time) == true) {
        TWEEN.update(time);
    } else {
        camera.position.x = radius * Math.cos(angle);
        camera.position.z = radius * Math.sin(angle);
        angle += 0.015;
        camera.lookAt(0, 0, 0);
    }

    renderer.render(scene, camera);
}


//MAIN
function main() {
    //Construção da Palavra: Bairrada
    letterB();
    letterA();
    letteri();
    letterR();
    letterR1();
    letterA1();
    letterD();
    letterA2();

    animate();
}