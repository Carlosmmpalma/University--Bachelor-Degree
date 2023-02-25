package sd;
import java.io.*;
import java.net.*;

public class EchoServer {

    private int serverPort;	
    
    public EchoServer(int p) {
	serverPort= p;		
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
	
	
	EchoServer serv= new EchoServer(p);
	serv.servico();   // activa o servico
    }

    
    // activa o servidor no porto indicado em "serverPort"
    public void servico() {
	ServerSocket ssock=null;
        byte[] b=new byte[256];

        try{
            
        ssock=new ServerSocket(serverPort);
        
            while(true){
            
            Socket s= ssock.accept();
            
            //ler pedido do cliente
            InputStream socketIn= s.getInputStream();
            int lidos= socketIn.read(b);
            String req= new String(b,0,lidos);
            System.out.println("CLIENTE: "+req);
            
            //escrever a resposta
            OutputStream socketOut= s.getOutputStream();
            socketOut.write(req.getBytes());
            
            s.close();
            }
        
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            if(ssock != null)
                try{
                ssock.close();
                }catch(IOException e2){
                    e2.printStackTrace();
                }
        }
        
    }


}
