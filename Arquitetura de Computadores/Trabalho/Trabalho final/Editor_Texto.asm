.globl main 

.data #Diretiva
FILENAME: .string "teste.txt" 
TEXT: .space 1000 #Alocaçao de espaço para escrita de texto
COMAND: .space 10 #Alocaçao de espaço para escrita de variaveis

.text #Programa
main:	
	
	la s1,FILENAME #Registo seguro
	addi sp,sp,-40

	switch:
		la a0,COMAND
		li a7, 12 # Scanf Char(letra)
		ecall
		li t0,105 #t0=i
		bne a0,t0,case1 #"branch if not equal" para case1
		jal insert  
		j switch
	case1:
		li t0,97 #t0=a
		bne a0,t0,case2 #"branch if not equal" para case2
		jal append
		j switch
	case2:
		li t0, 99 #to=c
		bne a0,t0,case3 #"branch if not equal" para case3
		jal change
		j switch
	case3:
		li t0, 100 #t0=d
		bne a0,t0,case4 #"branch if not equal" para case4
		jal delete
		j switch
	case4:
		li t0, 112 #t0=p
		bne a0,t0,case5 #"branch if not equal" para case5
		jal print
		j switch
	case5:
		li t0, 101 #t0=e
		bne a0,t0,case6 #"branch if not equal" para case6
		jal edit
		j switch
	case6:
		li t0, 102 #t0=f
		bne a0,t0,case7 #"branch if not equal" para case7
		jal file
		j switch
	case7:
		li t0,119 #t0=w
		bne a0,t0,case8 #"branch if not equal" para case8
		jal write
		j switch
	case8:
		li t0,113 #t0=q
		bne a0,t0,case9 #"branch if not equal" para case9
		jal quit
	case9:
		li t0,81 #t0=Q
		bne a0,t0,default #"branch if not equal" para default
		jal QUIT
	default:
		j switch
		
	addi sp,sp,40
		
insert:
	la a0,COMAND
	li a7, 12 # Scanf Char(numero da linha)
	ecall
	mv t2,a0 #Guardar caracter em registo temporário
	sw ra,0(sp) #Armazena endereço correspondente à linha 21	
	
	switch1: #Inserir texto na linha 1
		li t0,49 #Caracter "1" ASCII
		la a0,TEXT #Espaço alocado para colocar o texto
		bne t2,t0,line2 #"branch if not equal" para line2
		li a7,8
		li a1,20
		ecall #scanf
		sw a0,4(sp) #Ponteiro para linha 1
		lw ra,0(sp) #Carregar em return adress o endereço de retorno
		ret
		
	line2:  #Inserir texto na linha 2
		li t0,50 #Caracter "2" ASCII
		bne t2,t0,line3 #"branch if not equal" para line3
		addi a0,a0,100 #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,8(sp)  #Ponteiro para linha 2
	 	lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line3: #Inserir texto na linha 3
	 	li t0,51 #Caracter "3" ASCII
		bne t2,t0,line4 #"branch if not equal" para line4
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,12(sp)  #Ponteiro para linha 3
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line4: #Inserir texto na linha 4
	 	li t0,52 #Caracter "4" ASCII
		bne a0,t0,line5 #"branch if not equal" para line5
		addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,16(sp)  #Ponteiro para linha 4
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line5: #Inserir texto na linha 5
	 	li t0,53 #Caracter "5" ASCII
		bne a0,t0,line6 #"branch if not equal" para line6
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,20(sp)  #Ponteiro para linha 5
	 	beq a0,t1,default1
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line6:  #Inserir texto na linha 6
	 	li t0,54 #Caracter "6" ASCII
		bne a0,t0,line7 #"branch if not equal" para line7
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,24(sp)  #Ponteiro para linha 6
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line7: #Inserir texto na linha 7
	 	li t0,55 #Caracter "7" ASCII
		bne a0,t0,line8 #"branch if not equal" para line8
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,28(sp)  #Ponteiro para linha 7
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line8: #Inserir texto na linha 8
	 	li t0,56 #Caracter "8" ASCII
		bne a0,t0,line9 #"branch if not equal" para line9
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,32(sp)  #Ponteiro para linha 8
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 line9: #Inserir texto na linha 9
	 	li t0,57 #Caracter "9" ASCII
		bne a0,t0,default1 #Se n for igual a nenhuma das linhas retorna à main
	 	addi a0,a0,100  #Espaço vazio(gap) caso o utilizador pretenda adicionar texto posteriormente
	 	li a7,8
	 	li a1,20
	 	ecall #scanf
	 	sw a0,36(sp)  #Ponteiro para linha 9
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	 default1:
	 	lw ra,0(sp)  #Carregar em return adress o endereço de retorno
	 	ret
	 	
			
append:	
		sw ra,0(sp) #Armazena endereço correspondente à linha 26
		li t2,0 # Contador
		la a0,COMAND #Espaço alocado para definir linha
		li a7, 12 # Scanf Char(numero da linha)
		ecall
		mv t1,a0 #Guardar input dado pelo utilizador
		la a0,TEXT #Espaço alocado para colocar o texto
		li t4,49  #Caracter "1" ASCII
		bne t4,t1,append2 #"branch if not equal" to append2
		lw a0, 4(sp) #Ponteiro para linha 1
		mv t3,a0 #Variável auxiliar para guardar endereço correspondente primeira linha
		j loop
	append2:
		li t4,50  #Caracter "2" ASCII
		bne t4,t1,append3 #"branch if not equal" to append3
		lw a0, 8(sp) #Ponteiro para linha 2
		mv t3,a0 
		j loop
	append3:
		li t4,51  #Caracter "3" ASCII
		bne t4,t1,append4 #"branch if not equal" to append4
		lw a0, 12(sp) #Ponteiro para linha 3
		mv t3,a0 
		j loop
	append4:
		li t4,52  #Caracter "4" ASCII
		bne t4,t1,append5 #"branch if not equal" to append5
		lw a0, 16(sp) #Ponteiro para linha 4
		mv t3,a0 
		j loop
	append5:
		li t4, 53  #Caracter "5" ASCII
		bne t4,t1,append6 #"branch if not equal" to append6
		lw a0, 20(sp) #Ponteiro para linha 5
		mv t3,a0 
		j loop
	append6:
		li t4,54  #Caracter "6" ASCII
		bne t4,t1,append7 #"branch if not equal" to append7
		lw a0, 24(sp) #Ponteiro para linha 6
		mv t3,a0 
		j loop
	append7:
		li t4,55  #Caracter "7" ASCII
		bne t4,t1,append8 #"branch if not equal" to append8
		lw a0, 28(sp)  #Ponteiro para linha 7
		mv t3,a0
		j loop
	append8:
		li t4,56  #Caracter "8" ASCII
		bne t4,t1,append9 #"branch if not equal" to append9
		lw a0, 32(sp) #Ponteiro para linha 8
		mv t3,a0 
		j loop
	append9:
		li t4,57  #Caracter "9" ASCII
		lw a0, 36(sp) #Ponteiro para linha 9
		mv t3,a0 
		
		
loop:	#funçao auxiliar de contagem de caracteres
	lbu t0,0(t3) #Carregar primeiro caracter em t3
	addi t3,t3,1 #Avancar na palavra
	addi t2,t2,1 #Incremento no contador	
	bne t0,zero,loop #Verifica se é caracter nulo
	addi t2,t2,-2 #Retira loop a mais e caracter nulo
	add a0,a0,t2 #Avança até ao final da frase
	li a7,8
	li a1,20
	ecall #scanf
	lw ra,0(sp) #load word do endereço guardado em 175 
	ret 

change:
		sw ra,0(sp) #Armazena endereço correspondente à linha 26
		la a0,COMAND #Espaço alocado para definir linha
		li a7, 12 # Scanf Char(numero da linha)
		ecall
		mv t1,a0 #Guardar input dado pelo utilizador
		la a0,TEXT #Espaço alocado para colocar o texto
		li t4,49  #Caracter "1" ASCII
		bne t4,t1,change2 #"branch if not equal"  para change2
		lw a0, 4(sp) #Ponteiro para linha 1
		li a7,8
		li a1,20
		ecall 
		lw ra,0(sp)
		ret
	change2:
		li t4,50  #Caracter "2" ASCII
		bne t4,t1,change3 #"branch if not equal" para change3
		lw a0, 8(sp) #Ponteiro para linha 2
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change3:
		li t4,51  #Caracter "3" ASCII
		bne t4,t1,change4 #"branch if not equal" para change4
		lw a0, 12(sp) #Ponteiro para linha 3
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change4:
		li t4,52  #Caracter "4" ASCII
		bne t4,t1,change5 #"branch if not equal" para change5
		lw a0, 16(sp) #Ponteiro para linha 4
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change5:
		li t4, 53  #Caracter "5" ASCII
		bne t4,t1,change6 #"branch if not equal" para change6
		lw a0, 20(sp) #Ponteiro para linha 5
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change6:
		li t4,54  #Caracter "6" ASCII
		bne t4,t1,change7 #"branch if not equal" para change7
		lw a0, 24(sp) #Ponteiro para linha 6
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change7:
		li t4,55  #Caracter "7" ASCII
		bne t4,t1,change8 #"branch if not equal" para change8
		lw a0, 28(sp)  #Ponteiro para linha 7
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change8:
		li t4,56  #Caracter "8" ASCII
		bne t4,t1,change9 #"branch if not equal" para change9
		lw a0, 32(sp) #Ponteiro para linha 8
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret
	change9:
		li t4,57  #Caracter "9" ASCII
		lw a0, 36(sp) #Ponteiro para linha 9
		li a7,8
		li a1,20
		ecall
		lw ra,0(sp)
		ret

delete:
		sw ra,0(sp) #Armazena endereço correspondente à linha 36
		la a0,COMAND #Espaço alocado para definir linha
		li a7, 12 # Scanf Char(numero da linha)
		ecall
		mv t1,a0 #Guardar input dado pelo utilizador
		la a0,TEXT #Espaço alocado para colocar o texto
		li t4,49 #Caracter "1" ASCII
		bne t4,t1,delete2 #"branch if not equal" to delete2
		lw a0, 4(sp) #Ponteiro para linha 1
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete2:
		li t4,50 #Caracter "2" ASCII
		bne t4,t1,delete3 #"branch if not equal" to delete3
		lw a0, 8(sp) #Ponteiro para linha 2
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete3:
		li t4,51 #Caracter "3" ASCII
		bne t4,t1,delete4 #"branch if not equal" to delete4
		lw a0, 12(sp) #Ponteiro para linha 3
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete4:
		li t4,52 #Caracter "4" ASCII
		bne t4,t1,delete5 #"branch if not equal" to delete5
		lw a0, 16(sp) #Ponteiro para linha 4
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete5:
		li t4,53 #Caracter "5" ASCII
		bne t4,t1,delete6 #"branch if not equal" to delete6
		lw a0, 20(sp) #Ponteiro para linha 5
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete6:
		li t4,54 #Caracter "6" ASCII
		bne t4,t1,delete7 #"branch if not equal" to delete7
		lw a0, 24(sp) #Ponteiro para linha 6
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete7:
		li t4,55 #Caracter "7" ASCII
		bne t4,t1,delete8 #"branch if not equal" to delete8
		lw a0, 28(sp) #Ponteiro para linha 7
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete8:
		li t4,56 #Caracter "8" ASCII
		bne t4,t1,delete9 #"branch if not equal" to delete9
		lw a0, 32(sp) #Ponteiro para linha 8
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

	delete9:
		li t4,57 #Caracter "9" ASCII
		bne t4,t1,deleteall #"branch if not equal" to deleteall
		lw a0, 36(sp) #Ponteiro para linha 9
		addi a0,zero,0 #Apagar linha correspondente
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret
	deleteall:
		addi a0,zero,0 #Apagar todas as linhas
		lw ra,0(sp)  #Carregar em return adress o endereço de retorno
		ret

print:
	sw ra,0(sp) #Armazena endereço correspondente à linha 41
	la a0,COMAND #Espaço alocado para definir linha
	li a7,12  # Scanf Char(numero da linha)
	ecall
	mv t2,a0 #Guardar input dado pelo utilizador
	la a0,TEXT #Espaço alocado para colocar o texto
	li t1,49 #Caracter "1" ASCII
	bne t2,t1,print2 #"branch if not equal"para print2
	lw a0,4(sp) #Ponteiro para linha 1
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print2:
	li t1,50  #Caracter "2" ASCII
	bne t2,t1,print3 #"branch if not equal"para print3
	lw a0,8(sp) #Ponteiro para linha 2
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print3:
	li t1,51 #Caracter "3" ASCII
	bne t2,t1,print4 #"branch if not equal"para print4
	lw a0,12(sp) #Ponteiro para linha 3
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print4:
	li t1,52 #Caracter "4" ASCII
	bne t2,t1,print5 #"branch if not equal"para print5
	lw a0,16(sp) #Ponteiro para linha 4
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print5:
	li t1,53 #Caracter "5" ASCII
	bne t2,t1,print6 #"branch if not equal"para print6
	lw a0,20(sp) #Ponteiro para linha 5
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print6:
	li t1,54 #Caracter "6" ASCII
	bne t2,t1,print7  #"branch if not equal"para print7
	lw a0,24(sp) #Ponteiro para linha 6
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print7:
	li t1,55 #Caracter "7" ASCII
	bne t2,t1,print8 #"branch if not equal"para print8
	lw a0,28(sp) #Ponteiro para linha 7
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print8:
	li t1,56 #Caracter "8" ASCII
	bne t2,t1,print9 #"branch if not equal"para print9
	lw a0,32(sp) #Ponteiro para linha 8
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
print9:
	li t1,57 #Caracter "9" ASCII
	bne t2,t1,printall #"branch if not equal"para printall
	lw a0,36(sp) #Ponteiro para linha 9
	li a7,4 #Printar linha correspondente
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret
printall:            #Printar todas as linhas
	lw a0,4(sp)
	li a7,4
	ecall
	lw a0,8(sp)
	li a7,4
	ecall
	lw a0,12(sp)
	li a7,4
	ecall
	lw a0,16(sp)
	li a7,4
	ecall
	lw a0,20(sp)
	li a7,4
	ecall
	lw a0,24(sp)
	li a7,4
	ecall
	lw a0,28(sp)
	li a7,4
	ecall
	lw a0,32(sp)
	li a7,4
	ecall
	lw a0,36(sp)
	li a7,4
	ecall	
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret


edit:
	sw ra,0(sp) #Armazena endereço correspondente à linha 46
	li a7,1024 #Abrir ficheiro
	mv a0,s1 #nome do ficheiro
	li a1,1 #tipo de abertura do ficheiro
	ecall
	mv s2,a0 #guarda em s2 o file descriptor
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret

file:
	sw ra,0(sp) #Armazena endereço correspondente à linha 51
	li a7,8 #scanf (input para nome do ficheiro)
	li a1,50 # tamanho maximo do nome para o ficheiro
	ecall
	mv s1,a0 # guardar em s1 
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret

write:
	sw ra,0(sp) #Armazena endereço correspondente à linha 56
	li a7,1024 #Abre o ficheiro
	mv a0,s1 # guardar em a0
	li a1,1 #Modo de "apenas escrita"
	ecall
	li a7,57 #Fechar ficheiro
	ecall
	lw ra,0(sp) #Carregar em return adress o endereço de retorno
	ret

quit:
	mv s2,a0 #guarda em s2/Descritor do ficheiro
	la a1,TEXT #Endereço do buffer
	li a2,1000 #Maximo tamanho de leitura
	li a7,64 #Escreve no ficheiro o conteudo do buffer
	ecall
	li a7,10 #Sai do programa
	ecall

QUIT:	
	li a7,10 #Fecha programa
	ecall
	
				
