import java.io.*;

public class FileRead {
    public static void main(String[] args){
        try{
        String s;
        BufferedReader br = new BufferedReader(new FileReader("ola"));

        while((s= br.readLine()) != null){
            System.out.println(s);
        }
        br.close();

        }catch(Exception e){
            return;
        }
    }
    
}
