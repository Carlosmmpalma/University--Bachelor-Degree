/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sd.rest;

import javax.xml.bind.annotation.*;

/**
 *
 * @author carlos
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mensagem")
public class Mensagem {
    
    @XmlElement(required = true)
    protected String aid;
    
    @XmlElement(required = true)
    protected String remetente;
    
    @XmlElement(required = true)
    protected String mensagem;
    
    public Mensagem(){
        this.aid="";
        this.remetente="";
        this.mensagem="";
    }
    
    public Mensagem(String aid,String remetente,String mensagem){
        this.aid=aid;
        this.remetente=remetente;
        this.mensagem=mensagem;
    }
    
    public String getAid(){
        return aid;
    }
    
    public void setAid(String aid){
        this.aid=aid;
    }
    
    public String getRemetente(){
         return remetente;
    }
    
    public void setRemetente(String remetente){
        this.remetente=remetente;
    }
    
    public String getMensagem(){
        return mensagem;
    }
    
    public void setMensagem(String mensagem){
        this.mensagem=mensagem;
    }
}
