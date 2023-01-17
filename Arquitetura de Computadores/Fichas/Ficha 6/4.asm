#Inicializaçao
li a0, 1
li a1,9
li t0,0

#Comparaçao e soma
jal COMPARAR
COMPARAR:
bgt a0,a1,ELSE
slli t1,a0,31
bne t1,zero,RET
add t0,t0,a0
RET:
addi a0,a0,1
ret
ELSE:
bne t0,zero,END
li a0,0
j FIM
END:
add a0,zero,t0
FIM:
