import java.util.*;

public class E3_12 {
    public static void main(String[] args) {
        double w,h,BMI;
        Scanner s=new Scanner(System.in);

        System.out.println("PESO: ");
        w=s.nextDouble();
        System.out.println("Altura: ");
        h=s.nextDouble();
        s.close();

        BMI=w/Math.pow(h/100,2);

        System.out.println("BMI: "+BMI);
        
    }
}
