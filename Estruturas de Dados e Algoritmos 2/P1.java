import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1{
public static int n=1;
public static void main(String[] args) throws IOException{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int max=-2147483648;
    int num=0;

    int children=Integer.parseInt(reader.readLine());
    if(children<1 || children>300000) throw new IOException();

    for(int i=0; i<children; i++){
        String str=reader.readLine();
        String[] arr=str.split(" ");

        if(Integer.parseInt(arr[0])<0 || Integer.parseInt(arr[0])>10) throw new IOException();
       
        for(int j=1; j<arr.length; j++){
             num=Integer.parseInt(arr[j]);
           if(num>max){
               max=num;
           }
            
        }
    }
    System.out.println(max);
}
}