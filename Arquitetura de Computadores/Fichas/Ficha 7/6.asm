.data 
S: .string "Contador hey"

.text 
main:
la t0,S #COlocar endereço do primeiro caracter da string em t0
li t2,0 #COntador

contador:
lb t1,0(t0) #Colocar valor do caracter pertencente ao endereço t0 em t1
beq t1,zero,end #Verificar de o caracter é igual a 0(ou seja final da string)
addi t2,t2,1 #Se nao for igual a 0 adicionamos 1 ao contador
addi t0,t0,1 #t0 passa a ter o endereço do proximo caracter
j contador
end: