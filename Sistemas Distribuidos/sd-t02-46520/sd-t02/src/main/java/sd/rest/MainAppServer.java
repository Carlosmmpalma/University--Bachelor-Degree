package sd.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.*;
import java.net.URI;
import java.util.Properties;

public class MainAppServer {

    private static int getPort(int defaultPort) {
        return defaultPort;
    }
    
    //Multiplas portas para correr o servidor
    private static URI getBaseURI() {
        String baseURI = "";
        try (InputStream input = new FileInputStream("src/main/resources/configs.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            baseURI = prop.getProperty("baseuri");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URI.create(baseURI);
    }
    
    private static URI getBaseURI1() {
        String baseURI = "";
        try (InputStream input = new FileInputStream("src/main/resources/configs.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            baseURI = prop.getProperty("baseuri1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URI.create(baseURI);
    }
    
        private static URI getBaseURI2() {
        String baseURI = "";
        try (InputStream input = new FileInputStream("src/main/resources/configs.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            baseURI = prop.getProperty("baseuri2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URI.create(baseURI);
    }
    
    public static final URI BASE_URI = getBaseURI();
    public static final URI BASE_URI1 = getBaseURI1();
    public static final URI BASE_URI2 = getBaseURI2();


    public static HttpServer startServer() throws IOException {
        // ativar um servico com os REST resources existentes neste pacote:
        ResourceConfig rc = new ResourceConfig().packages("sd.rest");

        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    public static HttpServer startServer1() throws IOException {
        // ativar um servico com os REST resources existentes neste pacote:
        ResourceConfig rc = new ResourceConfig().packages("sd.rest");

        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI1, rc);
    }
    
    public static HttpServer startServer2() throws IOException {
        // ativar um servico com os REST resources existentes neste pacote:
        ResourceConfig rc = new ResourceConfig().packages("sd.rest");

        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI2, rc);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando o servidor...");
        HttpServer httpServer = startServer();
        HttpServer httpServer1 = startServer1();
        HttpServer httpServer2 = startServer2();


        System.out.println("\n## Servidor a correr em  : " + BASE_URI);
        System.out.println("\n## Servidor a correr em  : " + BASE_URI1);
        System.out.println("\n## Servidor a correr em  : " + BASE_URI2);

        System.out.println("\n## Carregue enter para parar o servidor...");

        System.in.read();
        // depois do enter:
        httpServer.stop();
        httpServer1.stop();
        httpServer2.stop();
    }

}
