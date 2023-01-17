
addi sp,sp,40
li a7,8
li a1,100
ecall
sw a0,4(sp)
li a7,8
li a1,100
ecall
sw a0,8(sp)
lw t1,4(sp)
