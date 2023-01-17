public class Primes{
    public static boolean isPrime(int n){
        int i=2;
        int count=0;
        while(i<n){
            if(n%i==0)
                count++;
        i++;
        }
        if(count==0 && n>1 && n%n==0 && n%1==0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        isPrime(11);
    }
}