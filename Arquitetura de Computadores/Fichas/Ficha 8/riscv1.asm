.globl main
.text

main:
li a0, 5
li a1,10

jal max
j end

max:
addi sp,sp,-4
sw ra,0(sp)
bgt a0,a1,else
mv a0,a1
else:
lw ra,0(sp)
ret
end: