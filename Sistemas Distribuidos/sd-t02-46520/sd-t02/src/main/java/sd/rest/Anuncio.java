package sd.rest;

import javax.xml.bind.annotation.*;

/**
 *
 * @author carlos
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anuncio")
public class Anuncio {
    
    @XmlElement(required = false)
    protected String aid;
    
    @XmlElement(required = true)
    protected String estado;
    
    @XmlElement(required = true)
    protected String tipo;
    
    @XmlElement(required = true)
    protected String tipologia;
    
    @XmlElement(required = true)
    protected String genero;
    
    @XmlElement(required = true)
    protected String descricao;
    
    @XmlElement(required = true)
    protected String preco;
    
    @XmlElement(required = true)
    protected String anunciante;
    
    @XmlElement(required = true)
    protected String contacto;
    
    @XmlElement(required = true)
    protected String localizacao;
    
    @XmlElement(required = true)
    protected String data;
    
    public Anuncio(){
        this.estado="";
        this.tipo="";
        this.tipologia="";
        this.genero="";
        this.descricao="";
        this.preco="";
        this.anunciante="";
        this.contacto="";
        this.localizacao="";

    }
    
    public Anuncio(String estado, String tipo, String tipologia,String genero, String descricao,String preco,String anunciante,String contacto, String localizacao, String data){
        this.estado=estado;
        this.tipo=tipo;
        this.tipologia=tipologia;
        this.genero=genero;
        this.descricao=descricao;
        this.preco=preco;
        this.anunciante=anunciante;
        this.contacto=contacto;
        this.localizacao=localizacao;
        this.data=data;
    }
    
    public Anuncio(String aid,String estado, String tipo, String tipologia,String genero, String descricao,String preco,String anunciante,String contacto, String localizacao, String data){
        this.aid=aid;
        this.estado=estado;
        this.tipo=tipo;
        this.tipologia=tipologia;
        this.genero=genero;
        this.descricao=descricao;
        this.preco=preco;
        this.anunciante=anunciante;
        this.contacto=contacto;
        this.localizacao=localizacao;
        this.data=data;
    }
    
      public String getAid(){
        return aid;
    }
    
    public void setAid(String aid){
        this.aid=aid;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado=estado;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    
    public String getTipologia(){
        return tipologia;
    }
    
    public void setTipologia(String tipologia){
        this.tipologia=tipologia;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero=genero;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao=descricao;
    }
    
    public String getPreco(){
        return preco;
    }
    
    public void setPreco(String preco){
        this.preco=preco;
    }
    
    public String getAnunciante(){
        return anunciante;
    }
    
    public void setAnunciante(String anunciante){
        this.anunciante=anunciante;
    }
    
    public String getContacto(){
        return contacto;
    }
    
    public void setContacto(String contacto){
        this.contacto=contacto;
    }
    
    public String getLocalizacao(){
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao){
        this.localizacao=localizacao;
    }
    
    public String getData(){
        return data;
    }
    
    public void setData(String data){
        this.data=data;
    }
    
    
}
