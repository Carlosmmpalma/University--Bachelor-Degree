/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Anuncio;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */

@Repository
public class Anunop {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void saveAnuncio(final Anuncio a){
        // Obter a data e hora atual
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO anuncios (tipo, estado, tipo_alojamento, genero, preco, detalhes, user_name, contacto, zona, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Parametros para inserir na table
        Object[] params = {a.getTipo(), a.getEstado(), a.getTipo_alojamento(), a.getGenero(), a.getPreco(), a.getDetalhes(), a.getUser_name(), a.getContacto(), a.getZona(), timestamp};

        jdbcTemplate.update(sql, params);
        
    }
    
    public String getAnuns(String tipo, String zona, String user_name, String estado) throws JsonProcessingException {
        
        if(tipo.equals("todos")){
            String sql = "SELECT * FROM anuncios WHERE estado= ? ";
            List<Object> params = new ArrayList<>();
            params.add(estado);
            
            List<Map<String, Object>> anuncios = jdbcTemplate.queryForList(sql, params.toArray());

            ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(anuncios);
        
        }else{
        String sql = "SELECT * FROM anuncios WHERE tipo = ? AND estado= ? ";
        List<Object> params = new ArrayList<>();
        params.add(tipo);
        params.add(estado);

        if (!zona.equals("")) {
            sql += "AND zona = ? ";
            params.add(zona);
        }

        if (!user_name.equals("")) {
            sql += "AND user_name = ? ";
            params.add(user_name);
        }

        sql += "ORDER BY aid";

        List<Map<String, Object>> anuncios = jdbcTemplate.queryForList(sql, params.toArray());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(anuncios);
        }
    }
    
    public String getAnunsperState(String estado) throws JsonProcessingException{
        String sql= "SELECT * FROM anuncios WHERE estado= ? ";
        
        List<Map<String, Object>> anuncios = jdbcTemplate.queryForList(sql, estado);
        
        ObjectMapper mapper = new ObjectMapper();
        
        return mapper.writeValueAsString(anuncios);

    }

    
    public String getAnunsHome(String tipo) throws JsonProcessingException{
        
        String sql = "SELECT * FROM anuncios WHERE tipo = ? AND estado='ativo' ORDER BY aid DESC LIMIT 3";

        List<Map<String, Object>> anuncios = jdbcTemplate.queryForList(sql, tipo);
        
        ObjectMapper mapper = new ObjectMapper();
         
        return mapper.writeValueAsString(anuncios);
    }   
    
    public void saveMensagem (int aid, String nome, String mensagem, String user_name){
        String sql = "INSERT INTO mensagens (user_name, aid, nome,mensagem) VALUES (?, ?, ?, ?)";
        
        // Parametros para inserir na table
        Object[] params = {user_name,aid,nome,mensagem};

        jdbcTemplate.update(sql, params);
    }
    
    public String getMensagens(int aid, String user_name) throws JsonProcessingException{
        String sql = "SELECT * FROM mensagens WHERE aid= ? AND user_name= ?";
        
         Object[] params = {aid,user_name};
         
         List<Map<String, Object>> mensagens = jdbcTemplate.queryForList(sql,params);
         
         ObjectMapper mapper = new ObjectMapper();

         return mapper.writeValueAsString(mensagens);
    }
    
    public void changeEstado(int aid, String new_estado) throws JsonProcessingException{
        String sql = "UPDATE anuncios SET estado= ? WHERE aid= ? ";
        
        Object[] params = {new_estado,aid};
        
        jdbcTemplate.update(sql, params);

    }
    
}
