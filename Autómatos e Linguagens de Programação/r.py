def is_relation(x,n,u):
    s=set()
    for c in x:
        for xi in c:
            s.add(xi)
    return s

print(is_relation({(0,0),(1,1)},2,{0,1}))
