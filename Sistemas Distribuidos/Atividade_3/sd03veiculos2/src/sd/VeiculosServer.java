package sd;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

public class VeiculosServer {
    private int serverPort;	
    
    private List<Registo> repositorio;
    
    public VeiculosServer(int p) {
	serverPort= p;		
	repositorio= new LinkedList<>();
    }
    

    
    public static void main(String[] args){
	System.err.println("SERVER...");
	if (args.length<1) {
		System.err.println("Missing parameter: port number");	
		System.exit(1);
	}
	int p=0;
	try {
	    p= Integer.parseInt( args[0] );
	}
	catch (Exception e) {
		e.printStackTrace();
		System.exit(2);
	}
	
	
	VeiculosServer serv= new VeiculosServer(p);
        serv.servico();   // activa o servico
    }
    
    





    public void servico() {

	try {

	    // inicializa o socket para receber ligacoes
            ServerSocket ss =new ServerSocket(serverPort);
            
	    while (true) {
		// espera uma ligacao
		Socket dataSocket = ss.accept();
		
		try {
		    Object objPedido= null;
		    // le os dados do pedido, como um OBJECTO "objPedido"		
                    ObjectInputStream ois=new ObjectInputStream(dataSocket.getInputStream());
                    objPedido=ois.readObject();
                    
                    ObjectOutputStream oos=new ObjectOutputStream(dataSocket.getOutputStream()); 
		    
		    // apreciar o objecto com o pedido recebido:
		    if (objPedido==null)
			System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
		    
		    if (objPedido instanceof PedidoDeConsulta) {
			PedidoDeConsulta pc= (PedidoDeConsulta) objPedido ;
			
			// procurar o registo associado a matricula pretendido
			// pesquisar no servidor (List, mais tarde num ficheiro)
                        Object resposta="DESCONHECIDO";
                        for(Registo r:repositorio){
                            if(pc.getMatricula().equals(r.getMatricula())){
                                resposta=r;
                                break;
                            }
                        }
                        
			
			// enviar objecto Cliente via socket		    
			// se encontra devolve o registo, se não, devolve um novo objecto ou string a representar que nao encontrou
                        oos.writeObject(resposta);

			
		    }
		    else if (objPedido instanceof PedidoDeRegisto) {
			PedidoDeRegisto pr= (PedidoDeRegisto) objPedido;
                        
                        
			// ver se ja existia registo desta matricula
			// adicionar ao rep local (se nao e' uma repeticao)
                        //detetar registo previo da mesma matricula
                        boolean present=false;
                        for(Registo r:repositorio){
                            if(r.getMatricula().equals(pr.getRegisto().getMatricula())){
                                present=true;
                                repositorio.remove(r);
                                break;
                            }
                        }
                        repositorio.add(pr.getRegisto());
                                
			
			// responder ao cliente
                        String resp="ADICIONADO";
                        if(present){
                            resp+="JA ESTAVA NA LISTA";
                        }
                        oos.writeObject(resp);

		    }
		    else
			System.out.println("PROBLEMA");
		    
                }
                catch (Exception exNoAtendimento) {
                    exNoAtendimento.printStackTrace();
                }
                finally { 
                    // fechar o socket de dados dedicado a este cliente:
                    try {
                        dataSocket.close();
                                
                    }
                    catch (Exception e002) {
                    }
                }
                
		
	    
		
	    }  // ... ciclo de atendimento
            
            
	
	}
	catch (Exception problemaBindAccept) {
	    problemaBindAccept.printStackTrace();
	}

    }


}
