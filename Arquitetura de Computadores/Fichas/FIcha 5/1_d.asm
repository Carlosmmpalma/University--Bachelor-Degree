#Inicializar
li t0,1231
li t2,1
li t3,2

#Switch
CASE1:
bne t0,t2,CASE2
li t1,10
j END
CASE2:
bne t0,t3,DEFAULT
li t1,15
j END
DEFAULT:
li t1,0
END:
