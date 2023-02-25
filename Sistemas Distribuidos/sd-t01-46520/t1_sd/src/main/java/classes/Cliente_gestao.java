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
public class Cliente_gestao implements java.rmi.Remote {
    
    
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
            System.out.println("1-Listar anuncios ativos");
            System.out.println("2-Listar anuncios inativos");
            System.out.println("3-Obter detalhes por aid");
            System.out.println("4-Aprovar anuncio por aid");
            System.out.println("5-Alterar estado de anuncio");
            System.out.println("6- SAIR\n");
            System.out.print("INDIQUE UTILIZANDO O RESPETIVO NUMERO A OPERAÇÃO A REALIZAR: ");
            c=s.nextInt();
            System.out.println();
            
            switch(c){
                //listar ativos
                case 1:
                    rl=obj.listar_ativos();
                    System.out.println("LISTA DE ANUNCIOS ATIVOS ");
                    for(int i=0; i<rl.size();i++){
                        System.out.println(rl.get(i));          
                    }
                break;  
                
                //listar inativos
                case 2:
                    rl=obj.listar_inativos();
                    System.out.println("LISTA DE ANUNCIOS INATIVOS: ");
                    for(int i=0; i<rl.size();i++){
                        System.out.println(rl.get(i));          
                    }
                break;  
                
                //detalhar anuncio por aid
                case 3:
                    System.out.print("Indique o aid: ");
                    int aid1=s.nextInt();
                    System.out.println();
                    r=obj.list_aid(aid1);
                    System.out.println("ANUNCIO: "+r);
                break;
                
                //ativar anuncio por aid
                case 4:
                    System.out.print("Indique o aid: ");
                    int aid2=s.nextInt();
                    System.out.println();
                    r=obj.ativar_anuncio(aid2);
                    System.out.println(r);
                break;
                
                //alterar estado de anuncio
                case 5:
                    System.out.print("Indique o aid: ");
                    int aid3=s.nextInt();
                    System.out.print("\nIndique o novo estado: ");
                    String n_estado=s.next();
                    System.out.println();
                    r=obj.alterar_estado(aid3,n_estado);
                    System.out.println(r);
                break;
                //sair
                case 6:
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
