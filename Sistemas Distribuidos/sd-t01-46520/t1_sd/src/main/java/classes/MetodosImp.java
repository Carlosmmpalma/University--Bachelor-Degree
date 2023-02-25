package classes;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author carlos
 */

public class MetodosImp extends UnicastRemoteObject  implements Metodos{
    
     // deve existir um construtor
    public MetodosImp() throws java.rmi.RemoteException {
        super();
    }
    
     Scanner s= new Scanner(System.in);
    
    public String registo(String tipo,String localizacao,int preco,String genero,String anunciante,String tipologia) throws  java.rmi.RemoteException, Exception{
       
        String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
      
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();

        //Obter ultimo aid
        int aid=0;
        
        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(aid) FROM t1");
            while(rs.next()){
             aid= rs.getInt("max");
            }
            rs.close(); // muito importante depois da consulta!
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        
        var data=java.time.LocalDate.now();
        var estado="inativo";
        aid++;
        //Inserir anuncio na bd
        try { 
            stmt.executeUpdate("insert into t1 values('"+aid+"','"+localizacao+"','"+preco+"','"+data+"','"+anunciante+"','"+tipologia+"','"+tipo+"','"+estado+"')");
            // o objeto java.util.Date será convertido para String com toString(). Se não for aceite pelo Postgres, use um DateFormat.
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
       }
        pc.disconnect();
        
        //Retornar aid do anuncio
        String aid_cliente="AID: "+ Integer.toString(aid);
        
        return "Anuncio registado, " +aid_cliente;
    }
    
    public ArrayList listar_oferta() throws java.rmi.RemoteException, Exception{
        
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
      
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
         try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE tipo='oferta'");

            while (rs.next()) {
                int aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                String st="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         
         return res;
    }
    
    public ArrayList listar_procuro() throws java.rmi.RemoteException, Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
          
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
        
         try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE tipo='procura'");

            while (rs.next()) {
                int aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                String st="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         return res;
    }
    
    public ArrayList listar_anunciante(String anunciante) throws java.rmi.RemoteException, Exception{
        
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
         try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE anunciante='"+anunciante+"'");

            while (rs.next()) {
                int aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante2=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                String st="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante2+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         
         return res;
    }
    
    public String list_aid(int aid) throws java.rmi.RemoteException,Exception{
        
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
          //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        //Guardar resultados
        String res="";
        
        try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE aid='"+aid+"'");

            while (rs.next()) {
                aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante2=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                res="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante2+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         
         return res;   
    }
    
    
     public String enviar_msg(int aid, String msg) throws java.rmi.RemoteException,Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
          //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
       //Inserir mensagem na bd
        try { 
            stmt.executeUpdate("insert into m values('"+aid+"','"+msg+"')");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
       }

         pc.disconnect();
         
         return "Mensagem enviada";   
    }
     
     
    public ArrayList consultar_msg(int aid) throws java.rmi.RemoteException, Exception{
        
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
         try {

            ResultSet rs = stmt.executeQuery("SELECT * from m WHERE aid='"+aid+"'");

            while (rs.next()) {
                String st=rs.getString("mensagens");
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         
         return res;
    }
    
    public ArrayList listar_ativos() throws java.rmi.RemoteException, Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
          
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
        
         try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE estado='ativo'");

            while (rs.next()) {
                int aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                String st="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         return res;
    }
    
     public ArrayList listar_inativos() throws java.rmi.RemoteException, Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
          
        //Guardar resultados
        ArrayList<String> res=new ArrayList<>();
        
        
        try {

            ResultSet rs = stmt.executeQuery("SELECT * from t1 WHERE estado='inativo'");

            while (rs.next()) {
                int aid= rs.getInt("aid");
                String localizacao = rs.getString("localizacao");
                int preco =rs.getInt("preco");
                java.sql.Timestamp data=rs.getTimestamp("data");
                String anunciante=rs.getString("anunciante");
                String tipologia=rs.getString("tipologia");
                String tipo=rs.getString("tipo");
                String st="Aid: "+aid+" Localização: "+localizacao+" Preço: "+preco+"€"+" Data: "+data+ " Anunciante: "+anunciante+ " Tipologia: "+tipologia+" Tipo: "+tipo;
                res.add(st);
            }
   
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         return res;
    }
     
    public String ativar_anuncio(int aid) throws java.rmi.RemoteException, Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        try {

            ResultSet rs = stmt.executeQuery("UPDATE t1 SET estado='ativo' WHERE aid='"+aid+"'");
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         return "Anuncio ativo";
    }
    
    public String alterar_estado(int aid, String estado) throws java.rmi.RemoteException, Exception{
         String host = "", db = "", user = "", pw = "";
        
        //Obter properties para ligar a bd
        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //Criar instancia de PostgresConnector
        PostgresConnector pc = new PostgresConnector( host,db,user,pw );
        MetodosImp a= new MetodosImp();
        
        // estabelecer a ligacao à bd
        pc.connect();
        Statement stmt = pc.getStatement();
        
        try {

            ResultSet rs = stmt.executeQuery("UPDATE t1 SET estado='"+estado+"' WHERE aid='"+aid+"'");
            rs.close(); // muito importante depois da consulta!
        }
        catch (SQLException e) {
            System.err.println("Problems retrieving data from db...");
        }
         
         pc.disconnect();
         return "Estado do anuncio alterado";
    }
    
}
