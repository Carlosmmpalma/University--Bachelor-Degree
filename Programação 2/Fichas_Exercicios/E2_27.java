import java.util.Scanner;

 public class E2_27 {
  public static void main(String[] args) {
         //variáveis
    String titulo;
    //input
    Scanner s=new Scanner(System.in);
    titulo=s.nextLine();
    s.close();
      //output
    System.out.println(titulo.length());
    System.out.println(titulo.substring(0,1));
    System.out.println(titulo.substring(titulo.length()-1,titulo.length()));
  }
}