import java.util.Scanner;

public class E9_14 {
    public static void main(String[] args) {
        //input
        Scanner scanner = new Scanner(System.in);
        String input=new String();
        input=scanner.nextLine();
        int count=0;

        //resoulçao
        while(input.length()>=1){
            for(int i=0;i<input.length();i++){
                if((int) input.charAt(i)>=65 && (int) input.charAt(i)<=90) count++;
            }
            System.out.println(count);
            input=scanner.nextLine();
            count=0;
        }
        scanner.close();

    }
}
