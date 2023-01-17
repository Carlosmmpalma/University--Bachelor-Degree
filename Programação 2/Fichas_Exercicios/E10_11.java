import java.util.*;

public class E10_11 {
   public static void main(String[] args) {
    int n;
    int count=1;
    int i=0;
    double value;
    double sum=0;
    double medium=0;

    Scanner s=new Scanner(System.in);
    System.out.println("Quantos valores reais pertende inserir?");
    n=s.nextInt();

    double[] values=new double[n];

    while(count<=n){
        System.out.format("Qual o valor numero %d\n", count);
        value=s.nextDouble();
        values[i]=value;
        count++;
        i++;
    }
    s.close();

    
    for (double j : values) {
        sum += j;
    }

    for(double j: values){
        medium=medium+Math.pow(j-sum,2);
    }

    System.out.println(Math.sqrt(medium/n));
   }
}
