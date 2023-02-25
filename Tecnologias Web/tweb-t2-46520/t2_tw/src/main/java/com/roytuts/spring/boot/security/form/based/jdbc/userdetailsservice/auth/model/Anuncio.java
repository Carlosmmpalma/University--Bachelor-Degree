/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlos
 */

public class Anuncio {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private String tipo;
    private String estado;
    private String tipo_alojamento;
    private String genero;
    private int preco;
    private String detalhes;
    private String user_name;
    private int contacto;
    private String zona;
    
    public Anuncio(String tipo, String estado, String tipo_alojamento, String genero, int preco,String detalhes,String user_name,int contacto,String zona){
        this.tipo=tipo;
        this.estado=estado;
        this.tipo_alojamento=tipo_alojamento;
        this.genero=genero;
        this.preco=preco;
        this.detalhes=detalhes;
        this.user_name=user_name;
        this.contacto=contacto;
        this.zona=zona;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public String getTipo_alojamento(){
        return this.tipo_alojamento;
    }
    
    public String getGenero(){
        return this.genero;
    }
    
    public int getPreco(){
        return this.preco;
    }
    
    public String getDetalhes(){
        return this.detalhes;
    }
    
    public String getUser_name(){
        return this.user_name;
    }
    
    public int getContacto(){
        return this.contacto;
    }
    
    public String getZona(){
        return this.zona;
    }
    
}
