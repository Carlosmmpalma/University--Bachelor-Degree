import java.io.IOException;

public class Ex3 {
    public static void main(String[] args) throws IOException {
        try{
        byte[] b= new byte[128];
        int lidos= System.in.read(b);
        String s= new String(b, 0, lidos -1); // ou -2 no windows
        int valor= Integer.parseInt(s.substring(0,lidos-1));
        System.out.println("valor: "+valor); 
        }catch(NumberFormatException e){
            System.out.println("\nOcorreu um erro");
        }
    }
}
