function unicos(x){

     const arr = [...new Set(x)];

     return arr;

}



console.log(unicos([1, -2.5, 1,1,3,2,3,2]));