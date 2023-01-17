#Inicializar
addi t1,zero,0
addi t2,zero,10

#Condiçoes
addi t0,zero,1
jal FOR 
bgt t0,t2, END
FOR:
add t1,t1,t0
addi t0,t0,1
ret
END: