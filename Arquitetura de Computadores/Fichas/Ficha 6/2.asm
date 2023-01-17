#Inicializar
li a0,260
li a1,270
li a2,200

#Comparar
bgt a0,a1, CASE1
mv a0,a1
j CASE2
CASE1:
bgt a0,a2,END
mv a0,a2
j END
CASE2:
bgt a0,a2,END 
mv a0,a2
END:
