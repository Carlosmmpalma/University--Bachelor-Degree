public class E6_2_3 {
    public static void main(String[] args) {
        double r=0;

        for(int i=1;i<=20;i++) {
            double p=Math.pow(2,i)-1;
            r=r+p;
            System.out.println(p);
        }
    }
}
