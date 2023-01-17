.globl main

.text 
main:
	li a0,3
	li a1,5
	li a2,88
	jal max3
	j end
	
max3:
	addi sp,sp,-8
	sw ra, 4(sp)
	jal max
	mv a1,a2
	jal max
	lw ra,4(sp)
        addi sp,sp,8
	ret
	
	
max:
	sw ra, 0(sp)
	bgt a0,a1,else
	mv a0,a1
	ret
        else:
        lw ra,0(sp)
        ret
end:
