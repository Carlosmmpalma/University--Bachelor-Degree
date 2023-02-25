/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author carlos
 */


public class Server {

    public static void main(String args[]) {
        
        
	int regPort= 1099; // default RMIRegistry port

        try (InputStream input = new FileInputStream("recursos/configs.properties")) {

            Properties prop = new Properties();

            // loading properties file
            prop.load(input);
            regPort = Integer.parseInt(prop.getProperty("regPortServer"));

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	try {

	    // remote object creation
	    Metodos obj= new MetodosImp();

	    java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);

	    registry.rebind("metodos", obj);

            // server is working and expecting connections
            System.out.println("Servidor Ok");
	} 
	catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}