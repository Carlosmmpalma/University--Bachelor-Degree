.data 

S: 
.word 1,5,-2,4

.text

main:
la t0,S #Endereço do array
li t1,5 #Tamanho do array
li t4,0 #Variavel auxiliar

func:#Percorrer o array
lw t2,0(t0) #Guardar primeiro valor do array em t2
addi t0,t0,4 #Passar para proximo valor do array(words 4 em 4)
bgt t2,t4,copy# Se t2 é supeior a t4 colocamos o valor de t2 em t4
addi t1,t1,-1#Decrementamos o contador do tamanho do array
bne t1,zero,func#Enquanto contador do array for diferente de 0 o ciclo continua
j end
copy:#Funcao para copiar valor para outro registo
mv t4,t2
j func
end: