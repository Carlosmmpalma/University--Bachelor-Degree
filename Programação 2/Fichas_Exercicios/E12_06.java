
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class E12_06 {
    public static void main(String[] args){
        try{
        Scanner s=new Scanner(System.in);
        BufferedWriter bw=new BufferedWriter(new FileWriter("talk"));
        String word="",stop="stop", STOP="STOP";


        while(word.equals(stop)==false && word.equals(STOP)==false){
            word=s.nextLine();
            bw.write(word+"\n");
        }
        s.close();
        bw.close();
        }catch(Exception e){
            return;
        }
    }
}
