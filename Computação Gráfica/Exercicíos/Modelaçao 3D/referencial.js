//
//  AXIS
//
function axis_arrow(color) {
    //
    // <cylinder height="2" radius="0.01">
    //
    let material = new THREE.MeshBasicMaterial({
        color: color
    });
    material.side = THREE.DoubleSide;
    //
    let
        radius = 0.01,
        height = 2.0,
        radialSegments = 32,
        heightSegments = 1;
    let cylinder_geo = new THREE.CylinderBufferGeometry(
        radius, radius, height,
        radialSegments, heightSegments);
    let cylinder = new THREE.Mesh(cylinder_geo, material);

    let tip_geo = new THREE.ConeBufferGeometry(
        2.0 * radius, height / 20,
        radialSegments, 32);
    let tip = new THREE.Mesh(tip_geo, material);
    tip.position.copy(new THREE.Vector3(0, 1, 0));

    cylinder.add(tip);

    return cylinder;
}

function referential() {
    //
    //  Lights
    //  
    //  255, 214, 170 -> #FFD6AA
    let ambient_light = new THREE.AmbientLight(0xFFD6AA);
    //
    //  X AXIS
    //
    let x_axis = axis_arrow("crimson");
    let x_rotation = new THREE.Quaternion();
    x_rotation.setFromAxisAngle(new THREE.Vector3(0, 0, 1), -0.5 * Math.PI);
    x_axis.quaternion.copy(x_rotation);
    //
    //  Y AXIS
    //
    let y_axis = axis_arrow("seagreen");
    //
    //  Z AXIS
    //
    let z_axis = axis_arrow("steelblue");
    let z_rotation = new THREE.Quaternion();
    z_rotation.setFromAxisAngle(new THREE.Vector3(1, 0, 0), 0.5 * Math.PI);
    z_axis.quaternion.copy(z_rotation);

    return [ambient_light, x_axis, y_axis, z_axis];
}
/**
 * Setup the rendering context and build a model
 **/
function init(objects) {
    //
    let renderer = new THREE.WebGLRenderer({
        alpha: true,
        antialias: true,
    });
    //renderer.setClearColor("khaki")
    let size = Math.min(parent.innerWidth - 32, parent.innerHeight - 32, 1024);
    renderer.setSize(size, size);
    let div_container = document.getElementById("container");
    div_container.appendChild(renderer.domElement);
    //

    let scene = new THREE.Scene();
    //
    //  Camera (and TrackballControls)
    //
    let camera = new THREE.PerspectiveCamera(
        45, // abertura
        512 / 512, // proporÃ§Ã£o largura/altura
        1, // corte perto
        10000 // corte longe
    );
    camera.position.set(0, 0, 8);
    camera.lookAt(scene.position);
    //
    let controls = new THREE.OrbitControls(camera, renderer.domElement);
    div_container.addEventListener("keypress", (e) => {
        if (e.key == "R" || e.key == "r") controls.reset()
    });
    //
    //
    for (let object of objects) {
        scene.add(object);
    }

    return {
        camera: camera,
        scene: scene,
        renderer: renderer,
        controls: controls
    }
}
/**
 * Animate the model
 */
function animate(step) {
    requestAnimationFrame(() => animate(step));
    step.controls.update();
    step.renderer.render(step.scene, step.camera);
}
/**
 *  Entry function
 */
function main() {
    let model = referential();
    let step = init(model);
    animate(step);
}