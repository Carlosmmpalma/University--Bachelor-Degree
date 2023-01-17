lui  t0,-1723
#DIREITA
srli t1,t0,24
slli t2,t0,8
srli t2,t2,24
slli t2,t2,8
#ESQUERDA
slli t3,t0, 24
srli t4,t0,8
slli t4,t4,24
srli t4,t4,8
#SOMA
or t1,t1,t2
or t1,t1,t3
or t1,t1,t4