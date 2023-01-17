li a0,15 #Valor
li t1,32 #Deslocamento	
li t2,1 #Verificar
li t3,0	#Inicializar

jal VERIFICA
VERIFICA:
srl t0,a0,t1
andi t0,t0,0x01
addi t1,t1,-1
blt t1,zero, END
bne t0,t2,VERIFICA
addi t3,t3,1
ret
END:
mv a0,t3
