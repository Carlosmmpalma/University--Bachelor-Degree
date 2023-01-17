public class E6_2_6 {
    public static void main(String[] args) {
        double r=1;
        for (int i=1; i<=20;i++) {
            double p=Math.pow(2,i);
            r=r*p;
            System.out.println(p);
        }
        System.out.println(r);
    }
    
}
