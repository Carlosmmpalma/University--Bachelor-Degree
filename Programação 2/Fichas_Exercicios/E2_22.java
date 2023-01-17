import java.util.Scanner;
import javax.swing.*;

public class E2_22 {
  public static void main(String[] args) {
    //Variáveis
      String titulo;
      int W,H;
    //Input
      Scanner s=new Scanner(System.in);
        titulo=s.nextLine();
        W=s.nextInt();
        H=s.nextInt();
        s.close();
    //Output
      JFrame window=new JFrame(titulo);
        window.setSize(W,H);
        window.setVisible(true);
  }
}