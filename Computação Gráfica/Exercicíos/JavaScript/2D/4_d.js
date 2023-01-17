

function emparelhar(x,y){

    let arr=new Array();

    if(x.length!=y.length){
        return arr;
    }else{
        for(let i=0;i<x.length;i++){

        arr[i] = { x: x[i], y: y[i] }
        }
    }
    return arr;
}

console.log(emparelhar([1, 2.1, 4], [5, -2, 3]));
