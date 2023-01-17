function media(x){
    let media=0;

    for(let i=0;i<x.length;i++){
        media+=x[i];
    }
    return media/x.length;
}

console.log(media([1, 2, 3, 4]))