def leadsTo(s,delta):
    lista=[]
    
    for i in range(len(delta)):
        for j in range(len(delta[i])):
            if(delta[i][j]=="s"):
                lista.append(delta[i][j+1])
    return set(lista)

delta = [
  (0, "a", 1), (0, "b", 3),
  (1, "a", 3), (1, "b", 2),
  (2, "a", 2), (2, "b", 2),
  (3, "a", 3), (3, "b", 3)    ]

print(leadsTo("a", delta))