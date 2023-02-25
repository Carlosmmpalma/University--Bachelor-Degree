/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */

//INTERFACE REMOTA
public interface Metodos extends java.rmi.Remote {
    
    public String registo(String tipo,String localizacao,int preco,String genero,String anunciante,String tipologia) throws  java.rmi.RemoteException, Exception;
    public ArrayList listar_oferta() throws java.rmi.RemoteException, Exception;
    public ArrayList listar_procuro() throws java.rmi.RemoteException, Exception;
    public ArrayList listar_anunciante(String anunciante) throws java.rmi.RemoteException, Exception;
    public String list_aid(int aid) throws java.rmi.RemoteException,Exception;
    public String enviar_msg(int aid, String msg) throws java.rmi.RemoteException,Exception;
    public ArrayList consultar_msg(int aid) throws java.rmi.RemoteException, Exception;
    public ArrayList listar_ativos() throws java.rmi.RemoteException, Exception;
    public ArrayList listar_inativos() throws java.rmi.RemoteException, Exception;
    public String ativar_anuncio(int aid) throws java.rmi.RemoteException, Exception;
    public String alterar_estado(int aid, String estado) throws java.rmi.RemoteException, Exception;
}
