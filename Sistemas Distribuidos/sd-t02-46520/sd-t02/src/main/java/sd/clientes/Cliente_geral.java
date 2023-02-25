package sd.clientes;

import org.json.*;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author carlos
 */
public class Cliente_geral {
    
    private static final String URL = "http://localhost:8080/api";
    
     public static int menu() {
        int option;
        
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Escolha uma opção:\n" +
                "   1 - Criar anuncio\n" +
                "   2 - Listar anuncios por tipo\n" +
                "   3 - Procurar anuncio por descrição e/ou localização\n" +
                "   4 - Procurar anuncio por aid\n" +
                "   5 - Enviar mensagem por aid\n" +
                "   6 - Consultar mensagens para aid\n" +
                "   7 - Sair");
        try {
            System.out.print("Opção: ");
            option = scan.nextInt();
        } catch (Exception e) {
            option = 8;
        }
        return option;
    }
    
     //Registar anuncio
    public static boolean postAnuncio(String estado, String tipo, String tipologia,String genero, String descricao,String preco,String anunciante,String contacto, String localizacao, String data){
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/anuncio").openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            
            String jsonObject = "{\"estado\":\"" + estado + "\",\"tipo\":\"" + tipo + "\",\"tipologia\":\"" + tipologia + "\",\"genero\":\"" + genero + "\",\"descricao\":\"" + descricao + "\",\"preco\":\"" + preco + "\",\"anunciante\":\"" + anunciante + "\",\"contacto\":\"" + contacto + "\",\"localizacao\":\"" + localizacao + "\",\"data\":\"" + data + "\"}";
            
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

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
    
    //Obter lista de anuncios  ativos
    public static List<String> getAnuncios(){
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
                String anuncio= "  Tipo: "+ object.getString("tipo")+
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
    
    //Obter lista de anuncios ativos por tipo
    public static List<String> getAnunciosTipo(String tipo){
        List<String> anuncios= new LinkedList<>();
        
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/anunciosativostipo?tipo="+tipo).openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Tipo: "+ object.getString("tipo")+
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
    
    
    //Procurar anuncio por descrição e opcionalmente localização
    public static List<String> procurarAnuncios(String descricao,String localizacao){
        List<String> anuncios= new LinkedList<>();
        
        try{
             HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/procuraanuncios?descricao=" + descricao+"&localizacao="+localizacao).openConnection();
             
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Tipo: "+ object.getString("tipo")+
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
    
    //Procurar anuncio por aid
    public static List<String> procurarAnuncioAid(int aid){
        List<String> anuncios= new LinkedList<>();
        
        try{
             HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/procuraaid?aid=" + aid).openConnection();
             
             connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Tipo: "+ object.getString("tipo")+
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
    
    //Enviar mensagem
    public static boolean postMensagem(String aidm,String remetente,String mensagem){
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/enviarmensagem").openConnection();
            
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            
            String jsonObject = "{\"aid\":\"" + aidm + "\",\"remetente\":\"" + remetente + "\",\"mensagem\":\"" + mensagem  + "\"}";
            
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

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
    
    //Consultar mensagens por aid
    public static List<String> consultarMensagensPorAid(String aid){
        List<String> mensagens= new LinkedList<>();
        
        try{
             HttpURLConnection connection = (HttpURLConnection) new URL(URL + "/consultarmensagens?aid=" + aid).openConnection();
             
             connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            
            BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String listAnuncios = data.readLine();
            
            data.close();
            
            JSONArray objects = new JSONArray(listAnuncios);
            
            for(int i=0;i<objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                String anuncio= "  Aid: "+ object.getString("aid")+
                                "  Remetente: " +object.getString("remetente")+
                                "  Mensagem: "+object.getString("mensagem");
                mensagens.add(anuncio);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mensagens;
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String estado, tipo, tipologia, genero, descricao, anunciante, contacto,  localizacao, data,preco;
        int aid;
        String aidm,remetente,mensagem;
        boolean controller;
      
       while (true) {
            int option = menu();

            if (option == 7) {
                System.out.println("Desligando o cliente...");
                Thread.sleep(1000);
                break;
            }
            
            switch(option){
                case 1:
                    estado="inativo";
                    
                    System.out.println();
                    System.out.println("Tipo:(Procura/Oferta) ");
                    tipo=scanner.nextLine();
                    
                    System.out.println("Tipologia: ");
                    tipologia=scanner.nextLine();
                    
                    System.out.println("Genero: ");
                    genero=scanner.nextLine();
                    
                    System.out.println("Descrição: ");
                    descricao=scanner.nextLine();
                    
                    System.out.println("Preço: ");
                    preco=scanner.nextLine();
                    
                    System.out.println("Contacto: ");
                    contacto=scanner.nextLine();
                    
                    System.out.println("Anunciante: ");
                    anunciante=scanner.nextLine();
                    
                    System.out.println("Localização: ");
                    localizacao=scanner.nextLine();
                    
                    Date dataHoraAtual = new Date();
                    data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                    
                    controller=postAnuncio(estado, tipo, tipologia, genero, descricao, preco, anunciante, contacto, localizacao, data);
                    if(controller){
                        System.out.println("Anuncio criado com sucesso");
                    }else{
                        System.out.println("Ocorreu um erro ao criar o anuncio");
                    }
                break;
                
                case 2:
                    System.out.println();
                    System.out.println("Indique o tipo");
                    tipo=scanner.nextLine();
                    
                    System.out.println("Lista de anuncios de : "+ tipo);
                    List<String> anuncios=getAnunciosTipo(tipo);
                    
                    if(anuncios.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: anuncios){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;

                case 3:
                    System.out.println();
                    System.out.println("Descrição: ");
                    descricao=scanner.nextLine();
                    
                    System.out.println("Localização: ");
                    localizacao=scanner.nextLine();
                    List<String> list_anuncios=procurarAnuncios(descricao, localizacao);
                    
                    if(list_anuncios.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: list_anuncios){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;
                
                case 4:
                    System.out.println();
                    System.out.println("Aid: ");
                    aid = scanner.nextInt();

                    List<String> list_anunciosAid=procurarAnuncioAid(aid);
                    
                     if(list_anunciosAid.size()==0){
                        System.out.println("Não existem anuncios no sistema");
                    }else{
                        for(String anuncio: list_anunciosAid){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;
                    
                case 5:
                    System.out.println();
                    System.out.println("Aid: ");
                    aidm = scanner.nextLine();
                    
                    System.out.println("Remetente: ");
                    remetente = scanner.nextLine();
                    
                    System.out.println("Mensagem: ");
                    mensagem = scanner.nextLine();
                
                    controller=postMensagem(aidm, remetente, mensagem);
                    
                    if(controller){
                        System.out.println("Mnesagem enviada com sucesso");
                    }else{
                        System.out.println("Ocorreu um erro ao enviar a mensagem");
                    }
                break;
                
                case 6:
                    System.out.println();
                    System.out.println("Aid: ");
                    aidm = scanner.nextLine();

                    List<String> list_mensagensAid=consultarMensagensPorAid(aidm);
                    
                     if(list_mensagensAid.size()==0){
                        System.out.println("Não existem mensagens no sistema");
                    }else{
                        for(String anuncio: list_mensagensAid){
                            System.out.println(anuncio);
                            
                        }
                    }
                break;
            }
       }
    }
}
