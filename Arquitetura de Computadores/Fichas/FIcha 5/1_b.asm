#Inicializar
addi t0,zero,2

#Condiçao
bgt t0,zero,ELSE
add t1,zero,t0
j END
ELSE:add t1,zero,t0
END: