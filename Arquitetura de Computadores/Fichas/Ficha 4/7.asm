#Inicializar
addi t0, zero,16

#X&X-1
addi t1,t0,-1
and t1,t0,t1

#Condicao
beqz t1,L
addi t2,zero,0 #Nao é potencia
j END
L:addi t2,zero,1 #É potência
END: