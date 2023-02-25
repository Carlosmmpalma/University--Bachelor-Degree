package sd.clientes;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.json.*;

/**
 *
 * @author carlos
 */
public class Cliente_gestao {
    
    private static final String URL = "http://localhost:8080/api";
    
     public static int menu() {
        int option;
        
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Escolha uma opção:\n" +
                "   1 - Listar anuncios ativos\n" +
                "   2 - Listar anuncios inativos\n" +
                "   3 - Aprovar anuncio por aid\n" +
                "   4 - Desativar anuncio por aid\n" +
                "   5 - Sair");
        try {
            System.out.print("Opção: ");
            option = scan.nextInt();
        } catch (Exception e) {
            option = 6;
        }
        return option;
    }
     
    //Obter lista de anuncios ativos
    public static List<String> getAnunciosAtivos(){
        List<String> anuncios= new LinkedList<>();
        
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/anunciosativos").openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Aid: "+ object.getString("aid")+
                                "  Tipo: "+ object.getString("tipo")+
                                "  Tipologia: " +object.getString("tipologia")+
                                "  Genero: "+object.getString("genero")+
                                "  Descrição: "+object.getString("descricao")+
                                "  Preço: "+object.getString("preco")+
                                "  Anunciante: "+object.getString("anunciante")+
                                "  Contacto: "+object.getString("contacto")+
                                "  Localização: "+object.getString("localizacao");
                anuncios.add(anuncio);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return anuncios;
    }
    
    //Obter lista de anuncios inativos
    public static List<String> getAnunciosInativos(){
        List<String> anuncios= new LinkedList<>();
        
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/anunciosinativos").openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Aid: "+ object.getString("aid")+
                                "  Tipo: "+ object.getString("tipo")+
                                "  Tipologia: " +object.getString("tipologia")+
                                "  Genero: "+object.getString("genero")+
                                "  Descrição: "+object.getString("descricao")+
                                "  Preço: "+object.getString("preco")+
                                "  Anunciante: "+object.getString("anunciante")+
                                "  Contacto: "+object.getString("contacto")+
                                "  Localização: "+object.getString("localizacao");
                anuncios.add(anuncio);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return anuncios;
    }
    
    //Ativar anuncio
    public static boolean ativarAnuncio(String aid){
        
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/ativaranuncio?aid="+aid).openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }catch(IOException e){
                e.printStackTrace();
                
            }
            return response.toString().equals("true");
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    //Desativar anuncio
    public static boolean desativarAnuncio(String aid){
        
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/desativaranuncio?aid="+aid).openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }catch(IOException e){
                e.printStackTrace();
                
            }
            return response.toString().equals("true");
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String estado, tipo, tipologia, genero, descricao, anunciante, contacto,  localizacao, data,preco;
        int aid;
        String aidm,remetente,mensagem;
        boolean controller;
      
       while (true) {
            int option = menu();

            if (option == 5) {
                System.out.println("Desligando o cliente...");
                Thread.sleep(1000);
                break;
            }
            
            switch(option){
                case 1:
                   System.out.println();
                    System.out.println("Lista de anuncios ativos: ");
                    List<String> anunciosativos=getAnunciosAtivos();
                    
                    if(anunciosativos.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: anunciosativos){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;
                
                case 2:
                    System.out.println();
                    System.out.println("Lista de anuncios inativos: ");
                    List<String> anunciosinativos=getAnunciosInativos();
                    
                    if(anunciosinativos.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: anunciosinativos){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;
                
                case 3:
                    System.out.println();
                    System.out.println("Lista de anuncios inativos: ");
                    List<String> anunciosinativos2=getAnunciosInativos();
                    
                    if(anunciosinativos2.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: anunciosinativos2){
                            System.out.println(anuncio);
                            
                        }
                    }
                    
                    System.out.println("Indique o anuncio que pretende ativar: ");
                    aidm=scanner.nextLine();
                    
                    if(ativarAnuncio(aidm)){
                        System.out.println("Anuncio ativado!");
                    }else{
                        System.out.println("Ocorreu um erro ao ativar o anuncio!");
                    }
                break;
                
                case 4:
                    System.out.println();
                    System.out.println("Lista de anuncios ativos: ");
                    List<String> anunciosativos2=getAnunciosAtivos();
                    
                    if(anunciosativos2.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: anunciosativos2){
                            System.out.println(anuncio);
                            
                        }
                    }
                    
                    System.out.println("Indique o anuncio que pretende desativar: ");
                    aidm=scanner.nextLine();
                    
                    if(desativarAnuncio(aidm)){
                        System.out.println("Anuncio desativado!");
                    }else{
                        System.out.println("Ocorreu um erro ao ativar o anuncio!");
                    }
                break;
                    
            }
       }
    }
    
}
