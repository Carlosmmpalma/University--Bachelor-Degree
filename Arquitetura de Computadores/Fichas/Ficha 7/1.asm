.data
S:
.string "hello world"

.text
main:
la t0,S

funcao:
lb t1, 0(t0)
beq t1,zero,end
addi  t1,t1,-32
sb t1,0(t0)
addi t0,t0,1
j funcao
end:
