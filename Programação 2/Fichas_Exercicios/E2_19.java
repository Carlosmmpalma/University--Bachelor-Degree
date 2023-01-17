import java.util.Scanner;

 public class E2_19 {
    public static void main(String args[]) {
        //Input
        System.out.println("Insira o nome:");
        Scanner s=new Scanner(System.in);
        String nomep=s.next();
        String nomem=s.next();
        String apelido=s.next();
        s.close();
        //Compute
        //Output
        System.out.println(nomep+" "+nomem.substring(0,1)+". "+apelido);
    }
}