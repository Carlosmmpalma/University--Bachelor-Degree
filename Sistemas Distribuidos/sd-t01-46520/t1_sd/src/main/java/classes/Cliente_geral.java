/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author carlos
 */

public interface Cliente_geral extends java.rmi.Remote {
    
      public static void main(String args[]) {
        Scanner s=new Scanner(System.in);
        String regHost = "";
	String regPort = "";
        
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // loading properties file
            prop.load(input);
            regHost = prop.getProperty("regHost");
            regPort = prop.getProperty("regPort");

        } catch (Exception e) {
            e.printStackTrace();
        }

	try {
	    // remote object
	    Metodos obj =(Metodos) java.rmi.Naming.lookup("rmi://" 
                      + regHost +":"+regPort +"/metodos");
	
        // connected successfully 
        System.out.println("BEM VINDO:");
        
        while(true){
            String r="";
            ArrayList<String> rl=new ArrayList();
            int c=0;
            
            //Menu
            System.out.print("\nMENU:\n");
            System.out.println("1-Registar novo anuncio de oferta");
            System.out.println("2-Registar novo anuncio de procura");
            System.out.println("3-Listar anuncios do tipo oferta");
            System.out.println("4-Listar anuncios do tipo procura");
            System.out.println("5-Listar todos os anuncios de um anunciante");
            System.out.println("6-Obter detalhes de um anuncio por aid");
            System.out.println("7-Enviar mensagem por aid");
            System.out.println("8-Consultar mensagens por aid");
            System.out.println("9- SAIR\n");
            System.out.print("INDIQUE UTILIZANDO O RESPETIVO NUMERO A OPERAÇÃO A REALIZAR: ");
            c=s.nextInt();
            System.out.println();
            
            switch(c){
                //registo oferta
                case 1:
                    String tipo,localizacao,genero,anunciante,tipologia;
                    int preco;
                    
                    System.out.println("DETALHES DO ANUNCIO: ");
                    tipo ="oferta";
                    System.out.print("Localização: ");
                    localizacao=s.next();
                    System.out.print("Preço: ");
                    preco=s.nextInt();
                    System.out.print("Género: ");
                    genero=s.next();
                    System.out.print("Anunciante: ");
                    anunciante=s.next();
                    System.out.print("Tipologia: ");
                    tipologia=s.next();
                    
                    r=obj.registo(tipo, localizacao, preco, genero, anunciante, tipologia);
                    System.out.println(r+"\n");
                break;
                    
                //registo procura    
                case 2:
                    tipo ="procura";
                    
                    System.out.println("DETALHES DO ANUNCIO: ");
                    System.out.print("Localização: ");
                    localizacao=s.next();
                    System.out.print("Preço: ");
                    preco=s.nextInt();
                    System.out.print("Género: ");
                    genero=s.next();
                    System.out.print("Anunciante: ");
                    anunciante=s.next();
                    System.out.print("Tipologia: ");
                    tipologia=s.next();
                    
                    r=obj.registo(tipo, localizacao, preco, genero, anunciante, tipologia);
                    System.out.println(r+"\n");
                break;
                    
                //listar ofertas    
                case 3:
                    rl=obj.listar_oferta();
                    System.out.println("LISTA DE OFERTAS: ");
                    for(int i=0; i<rl.size();i++){
                        System.out.println(rl.get(i));          
                    }  
                break;   
                
                //listar procuras
                case 4:
                    rl=obj.listar_procuro();
                    System.out.println("LISTA DE PROCURAS: ");
                    for(int i=0; i<rl.size();i++){
                        System.out.println(rl.get(i));          
                    }
                break;  
                
                //listar anuncios de um anunciante
                case 5:
                    System.out.print("Indique o anunciante: ");
                    String anun=s.next();
                    System.out.println();
                    System.out.println("LISTA DE ANUNCIOS DE "+anun+":");
                    rl=obj.listar_anunciante(anun);
                    for(int i=0; i<rl.size();i++){
                        System.out.println(rl.get(i));          
                    }
                break;  
                
                //detalhes de anuncio por aid
                case 6:
                    System.out.print("Indique o aid: ");
                    int aid1=s.nextInt();
                    System.out.println();
                    r=obj.list_aid(aid1);
                    System.out.println("ANUNCIO: "+r);
                break;
                    
                //enviar menssagem
                case 7:
                    System.out.print("Indique o aid: ");
                    int aid2=s.nextInt();
                    System.out.println();
                    System.out.print("Indique a mensagem: ");
                    String men=s.next();
                    System.out.println();

                    r=obj.enviar_msg(aid2, men);
                    System.out.println(r);
                break;
                
                //consultar mensagens
                case 8:
                    System.out.print("Indique o aid: ");
                    int aid3=s.nextInt();
                    System.out.println();
                    rl=obj.consultar_msg(aid3);
                    for(int i=0; i<rl.size();i++){
                        System.out.println("MENSAGEM "+(i+1)+": "+rl.get(i));          
                    }
                break;
                
                //sair
                case 9:
                    System.out.println("ADEUS");
                    System.exit(0);
                default:
                    System.out.println("Comando inválido");
                break;
            }
            
            
        }
        }catch(Exception e){
               e.printStackTrace();
        }
      }
}