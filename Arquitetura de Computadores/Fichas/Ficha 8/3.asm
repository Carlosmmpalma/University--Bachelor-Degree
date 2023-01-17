.globl main
.text
main:

li a0,-8

jal abs
j end

abs:
	addi sp,sp,4
	sw ra ,0(sp)
	bltz a0,else
	sub a1,zero,a0
	jal max
	ret
else:
	mv a1,a0
	sub a0,zero,a1
	jal max
	lw ra,0(sp)
	addi sp,sp,4
	ret

max:
	#So a ver
	ret
	
end: