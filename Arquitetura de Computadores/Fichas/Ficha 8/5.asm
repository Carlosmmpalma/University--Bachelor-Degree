.globl main

.data
S: .word 1,2,3,4,5,6,7,8,9

.text

main:

la a0,S
li a1,9
jal soma_array
j end

soma_array:
	li t2,0 #Soma
	li t1,0 #i
	for:
	lb t0,0(a0) #Carregar primeiro byte
	add t2,t2,t0 #soma
	addi t1,t1,1 #contador
	addi a0,a0,4 #proximo byte do array de words
	bne t1,a1,for
ret
end:	
	
	
