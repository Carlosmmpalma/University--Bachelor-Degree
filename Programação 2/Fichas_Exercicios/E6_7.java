import java.util.Scanner;
public class E6_7 {

    public static boolean isPrimo(int n){
        return (n>1 && n%n==0 && n%1==0); 
    }
    public static void main(String[] args){
        System.out.println("Indique um numero:");
        Scanner s = new Scanner(System.in);
        int num=s.nextInt();
        s.close();

        while(num>=0){
            if(isPrimo(num)){
                System.out.println("Primo");
            }else{
                System.out.println("Nao é primo");
            }
            num--;
        }

    }
}
