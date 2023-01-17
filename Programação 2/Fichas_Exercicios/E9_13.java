import java.util.Scanner;

public class E9_13 {
    public static void main(String[] args) {
        //input
        Scanner scanner = new Scanner(System.in);
        String input=new String();
        input=scanner.nextLine();
        int count=0;

        //resoulçao
        while(input.length()>=1){
            for(int i=0;i<input.length();i++){
                if(Character.isUpperCase(input.charAt(i))) count++;
            }
            System.out.println(count);
            input=scanner.nextLine();
            count=0;
        }
        scanner.close();

    }
    
}

