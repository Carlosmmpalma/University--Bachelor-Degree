.globl main
.text 
main:
li a0,-8

jal abs
j end

abs:
	addi sp,sp-4
	sw ra,0(sp)
	bltz a0,else
	ret
else:
	sub a0,zero,a0
	lw ra,0(sp)
	addi sp,sp,4
	ret
end: