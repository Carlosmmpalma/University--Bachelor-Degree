import java.util.Scanner;

public class E3_14 {
    public static void main(String[] args) {
        double r,V;

        Scanner s=new Scanner(System.in);
        r=s.nextDouble();
        s.close();

        V=Math.pow(r, 3)*Math.PI*(4.0/3.0);

        System.out.println("VOLUME: "+V);
    }
}
