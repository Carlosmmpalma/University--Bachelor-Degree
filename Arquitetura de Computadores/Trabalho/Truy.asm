.data
BUF: .space 90

M: .string "\n"

.text

la a0,BUF
li a7,8
addi a1,a1,90
ecall

addi sp,sp,-12
sw a0,4(sp)
addi a0,a0,100
lw t1,4(sp)


li a7,8
addi a1,a1,90
ecall
sw a0,8(sp)

lw t2,8(sp)
addi sp,sp,12

mv a0,t1
li a7,4
ecall

mv a0,t2
li a7,4
ecall

