package sd.rest;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author carlos
 */
public class DbOperations {
    
    //Iniciar a bd com as configurações
    PostgresConnector setDbProperties() throws Exception {
        String host = "", db = "", user = "", pw = "";
        try (InputStream input = new FileInputStream("src/main/resources/configs.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            host = prop.getProperty("host");
            db = prop.getProperty("db");
            user = prop.getProperty("user");
            pw = prop.getProperty("password");
        }
        return new PostgresConnector(host, db, user, pw);

    }
    
    //Inserir anuncio na bd
    protected boolean registarAnuncio(Anuncio a) throws SQLException{
        String sql = "INSERT INTO anuncios (estado,tipo,tipologia,genero,descricao,preco,anunciante,contacto,localizacao,data) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);

            stmt.setString(1, a.getEstado());
            stmt.setString(2, a.getTipo());
            stmt.setString(3, a.getTipologia());
            stmt.setString(4, a.getGenero());
            stmt.setString(5, a.getDescricao());
            stmt.setString(6, a.getPreco());
            stmt.setString(7, a.getAnunciante());
            stmt.setString(8, a.getContacto());
            stmt.setString(9, a.getLocalizacao());
            stmt.setString(10, a.getData());
            stmt.executeUpdate();
            
            pc.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
    
    //Consultar todos os anuncios tendo em conta o tipo
    protected List<Anuncio> listarAnunciosPorTipo(String tipo) throws SQLException{
        String sql ="SELECT * FROM anuncios WHERE estado=? AND tipo=? ";
        
        List<Anuncio> anuncios=new LinkedList<>();
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();;
            
            PreparedStatement stmt = pc.prepareStatement(sql);
            stmt.setString(1, "ativo");
            stmt.setString(2,tipo);
            
           ResultSet rs=stmt.executeQuery();
           
           while(rs.next()){
               anuncios.add(new Anuncio(rs.getString("estado"),rs.getString("tipo"),rs.getString("tipologia"),rs.getString("genero"),
                                        rs.getString("descricao"),rs.getString("preco"),rs.getString("anunciante"),rs.getString("contacto"),rs.getString("localizacao"),rs.getString("data")));
           }
           pc.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return anuncios;
    }
    
    //Consultar todos os anuncios tendo em conta um estado
    protected List<Anuncio> listarAnuncios(String estado) throws SQLException{
        String sql ="SELECT * FROM anuncios WHERE estado=?";
        
        List<Anuncio> anuncios=new LinkedList<>();
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();;
            
            PreparedStatement stmt = pc.prepareStatement(sql);
            stmt.setString(1, estado);
            
           ResultSet rs=stmt.executeQuery();
           
           while(rs.next()){
               anuncios.add(new Anuncio(rs.getString("aid"),rs.getString("estado"),rs.getString("tipo"),rs.getString("tipologia"),rs.getString("genero"),
                                        rs.getString("descricao"),rs.getString("preco"),rs.getString("anunciante"),rs.getString("contacto"),rs.getString("localizacao"),rs.getString("data")));
           }
           pc.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return anuncios;
    }
    
    //Consultar um anuncio tendo em conta alguns parametros
    protected List<Anuncio> procurarAnuncio(String descricao,String localizacao) throws SQLException{
        String sql="SELECT * FROM anuncios WHERE descricao LIKE '%' || ? || '%'";
        
        List<Anuncio> anuncios=new LinkedList<>();
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            if(!localizacao.equals("")){
                sql+="AND localizacao='"+localizacao+"'";
            }
            
            PreparedStatement stmt = pc.prepareStatement(sql);
            stmt.setString(1, descricao);
            
           ResultSet rs=stmt.executeQuery();
           while(rs.next()){
               anuncios.add(new Anuncio(rs.getString("estado"),rs.getString("tipo"),rs.getString("tipologia"),rs.getString("genero"),
                                        rs.getString("descricao"),rs.getString("preco"),rs.getString("anunciante"),rs.getString("contacto"),rs.getString("localizacao"),rs.getString("data")));
           }
           pc.disconnect();
            
        }catch(Exception e){
            e.printStackTrace();
        }
       return anuncios;
    }
    
    //Consultar um anuncio tendo em conta o aid
    protected List<Anuncio> procurarPorAid(int aid) throws SQLException{
        String sql="SELECT * FROM anuncios WHERE aid=?";
        
        List<Anuncio> anuncios=new LinkedList<>();
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);
            stmt.setInt(1, aid);
            
           ResultSet rs=stmt.executeQuery();
           while(rs.next()){
               anuncios.add(new Anuncio(rs.getString("estado"),rs.getString("tipo"),rs.getString("tipologia"),rs.getString("genero"),
                                        rs.getString("descricao"),rs.getString("preco"),rs.getString("anunciante"),rs.getString("contacto"),rs.getString("localizacao"),rs.getString("data")));
           }
           pc.disconnect();
            
        }catch(Exception e){
            e.printStackTrace();
        }
       return anuncios;
        
    }
    
    //Ativar anuncio
     protected boolean ativarAnuncio(String aid) throws SQLException{
        String sql="UPDATE anuncios SET estado=? WHERE aid=?";
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);

            int aid1=Integer.parseInt(aid);
            
            stmt.setString(1, "ativo");
            stmt.setInt(2,aid1);
            
            stmt.executeUpdate();
            
            pc.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
     
    //Desativar anuncio
     protected boolean desativarAnuncio(String aid) throws SQLException{
        String sql="UPDATE anuncios SET estado=? WHERE aid=?";
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);

            int aid1=Integer.parseInt(aid);
            
            stmt.setString(1, "inativo");
            stmt.setInt(2,aid1);
            
            stmt.executeUpdate();
            
            pc.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
    
    //Registar mensagem na bd
     protected boolean enviarMensagem(Mensagem m) throws SQLException{
        String sql="INSERT INTO mensagens(aid,remetente,mensagem) VALUES (?,?,?)";
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);

            int aid=Integer.parseInt(m.getAid());
            
            stmt.setInt(1,aid);
            stmt.setString(2, m.getRemetente());
            stmt.setString(3, m.getMensagem());
            
            stmt.executeUpdate();
            
            pc.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
    
    //Consultar mensagem para um respetivo aid
    protected List<Mensagem> consultarMensagens(String aid) throws SQLException{
        String sql="SELECT * FROM mensagens WHERE aid=?";
        
        List<Mensagem> mensagens=new LinkedList<>();
        
        try{
            PostgresConnector pc=setDbProperties();
            pc.connect();
            
            PreparedStatement stmt = pc.prepareStatement(sql);
            
            int aid2=Integer.parseInt(aid);

            stmt.setInt(1, aid2);
            
           ResultSet rs=stmt.executeQuery();
           while(rs.next()){
               mensagens.add(new Mensagem(rs.getString("aid"),rs.getString("remetente"),rs.getString("mensagem")));
           }
           pc.disconnect();
            
        }catch(Exception e){
            e.printStackTrace();
        }
       return mensagens;
        
    }
}
