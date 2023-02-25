package sd.rest;

import jakarta.ws.rs.*;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author carlos
 */

@Path(value = "/api")
public class AnuncioResource {
    private DbOperations dbOperations;
    
    public AnuncioResource(){
        this.dbOperations=new DbOperations();
    }
    
    @Path("/anuncio")
    @POST
    @Consumes({"application/json"})
    public synchronized boolean criarAnuncio(Anuncio a) throws SQLException{
        System.out.println("Criando um novo anuncio");
        
        return this.dbOperations.registarAnuncio(a);
    }
    
    @Path("/anunciosativos")
    @GET
    @Produces({"application/json"})
    public synchronized List<Anuncio> listarAnunciosAtivos() throws SQLException{
        System.out.println("Listando anuncios ativos");
        
        return this.dbOperations.listarAnuncios("ativo");
    }
    
    @Path("/anunciosinativos")
    @GET
    @Produces({"application/json"})
    public synchronized List<Anuncio> listarAnunciosInativos() throws SQLException{
        System.out.println("Listando anuncios inativos");
        
        return this.dbOperations.listarAnuncios("inativo");
    }
    
    @Path("/anunciosativostipo")
    @GET
    @Produces({"application/json"})
    public synchronized List<Anuncio> listarAnunciosTipo(@QueryParam("tipo") String tipo) throws SQLException{
        System.out.println("Listando anuncios do tipo "+tipo);
        
        return this.dbOperations.listarAnunciosPorTipo(tipo);
    }
    
    @Path("procuraanuncios")
    @GET
    @Produces({"application/json"})
    public synchronized List<Anuncio> procurarAnuncios(@QueryParam("descricao") String descricao,@QueryParam("localizacao") String localizacao) throws SQLException{
        System.out.println("Procurando anuncios por descricao");

        return this.dbOperations.procurarAnuncio(descricao, localizacao);
    }
    
    @Path("procuraaid")
    @GET
    @Produces({"application/json"})
    public synchronized List<Anuncio> procurarAnunciosPorAid(@QueryParam("aid") int aid) throws SQLException{
        System.out.println("Procurando anuncios por aid");

        return this.dbOperations.procurarPorAid(aid);
    }
    
    @Path("/ativaranuncio")
    @GET
    @Consumes({"application/json"})
    public synchronized boolean ativarAnuncio(@QueryParam("aid") String aid) throws SQLException{
        System.out.println("Ativando anuncio");
                
        return this.dbOperations.ativarAnuncio(aid);
    }
    
    @Path("/desativaranuncio")
    @GET
    @Consumes({"application/json"})
    public synchronized boolean desativarAnuncio(@QueryParam("aid") String aid) throws SQLException{
        System.out.println("Desativando anuncio");
                
        return this.dbOperations.desativarAnuncio(aid);
    }
    
    @Path("enviarmensagem")
    @POST
    @Produces({"application/json"})
    public synchronized boolean enviarMensagem(Mensagem m) throws SQLException{
        System.out.println("Enviando mensagem");

        return this.dbOperations.enviarMensagem(m);
    }
    
    @Path("consultarmensagens")
    @GET
    @Produces({"application/json"})
    public synchronized List<Mensagem> consultarMensagens(@QueryParam("aid") String aid) throws SQLException{
        System.out.println("Procurando anuncios por aid");

        return this.dbOperations.consultarMensagens(aid);
    }
}
