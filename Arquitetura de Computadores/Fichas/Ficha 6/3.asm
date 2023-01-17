#Inicializaçao
li a0, 10
li a1,9
li t0,0

#Comparaçao e soma
jal COMPARAR
COMPARAR:
bgt a0,a1,ELSE
add t0,t0,a0
addi a0,a0,1
ret
ELSE:
bne t0,zero,END
li a0,0
j FIM
END:
add a0,zero,t0
FIM:
