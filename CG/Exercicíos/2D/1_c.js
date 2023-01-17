function map(f, a) {
    var arr = [];
    
    var i=0;
    
    while( i < (a.length)){
        arr.push(f(a[i]));
        i++;
        
    }
    
    return arr;
}

