li a0,2
li t2,2
li t3,3

jal ra, Collatz
j END
Collatz:
add t4,zero,a0
slli t4,t4,31

bne t4,zero,ELSE
div a0,a0,t2
ret
ELSE:
mul a0,a0,t3
addi a0,a0,1
ret
END:
 
