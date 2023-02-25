package SD;

public class Exe{
    
    public static void main(String[] args){
        System.out.println("System java version: " + System.getProperty("java.version"));
        System.out.println("System java vendor: " + System.getProperty("java.vendor"));
        System.out.println("System classpath: " + System.getProperty("java.class.path"));
        System.out.println("System file separator: " + System.getProperty("file.separator"));
        System.out.println("System encoding: " + System.getProperty("file.encoding"));
        System.out.println("System master name: " + System.getProperty("user.name"));
    }
}