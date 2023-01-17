import java.util.Scanner;

public class E9_10 {
    public static void main(String[] args){
        //declarar
        Scanner s= new Scanner(System.in);
        String input=new String();
        //input
        input=s.nextLine();
        s.close();

        if(input.length()>1){
            throw new IllegalStateException("Invalid input");
        }else{
            System.out.println("CÓDIGO ASCII: "+(int)input.charAt(0));
        }

    }
}
