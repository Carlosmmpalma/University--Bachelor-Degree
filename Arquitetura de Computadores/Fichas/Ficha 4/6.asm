#Colocar valores nos registos
lui t0, 0x21384
addi t0, t0, 0x4f5

lui t1, 0x21384
addi t1, t1, 0x45c

#Mover bits para direita 
srli t0,t0,8
srli t1,t1,8

#Comparar valores
bne t0,t1,L
addi t2,zero,1
j END
L:addi t2,zero,0
END: