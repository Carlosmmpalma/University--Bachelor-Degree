.globl main

.data
	W:.word 22,32,54,65,22,54
	
.text
main:
la a0,W
li a1,6
jal array_max
j end

array_max:
	li t1,1
	mv t2,a1
	lb t0,0(a0)
	addi sp,sp,-8
	sw ra,4(sp)
	for:
	lb a1,4(a0)
	addi a0,a0,4
	jal max
	addi t1,t1,1
	blt t1,t2,for
	lw ra,4(sp)
	addi sp,sp,8
	ret

max:
	sw ra,0(sp)
	blt t0,a1,else
	ret
else:	
	mv t0,a1
	lw ra,0(sp)
	ret
	end: