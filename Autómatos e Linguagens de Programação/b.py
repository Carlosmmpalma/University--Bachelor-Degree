def controls(delta):
    lista=[]
    lista.append(delta[0][0])

    for i in range(len(delta)):
        for j in range(2,len(delta[i])):
            lista.append(delta[i][j])

    return set(lista)


delta = [   (0, "a", 1), (0, "b", 3),
            (1, "a", 3), (1, "b", 2),
            (2, "a", 2), (2, "b", 2),
            (3, "a", 3), (3, "b", 3)    ]

print(controls(delta))