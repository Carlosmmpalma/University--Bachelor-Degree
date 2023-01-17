import java.io.*;
import java.util.Scanner;

public class FileWrite {
    public static void main(String[] args){
        try{
        String input;
        int i=0;
        BufferedWriter reader = new BufferedWriter(new FileWriter("ola"));
        Scanner s = new Scanner(System.in);
        while(i<10){
            input = s.nextLine();
            reader.write(input+"\n");
            i++;
        }
        s.close();
        reader.close();
        }catch(Exception e){
            return;
        }
    }
}
