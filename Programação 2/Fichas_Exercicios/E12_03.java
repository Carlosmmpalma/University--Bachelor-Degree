import java.util.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class E12_03{
    public static void main(String[] args) throws IOException{
        String filename;

        Scanner scanner =new Scanner(System.in); 
        Desktop desktop = Desktop.getDesktop(); 

        filename = scanner.nextLine();
        scanner.close();
        
        
        {  //constructor of file class having file as argument  
        File file = new File(filename);   

        if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
        {  
        System.out.println("not supported");  
        return;  
        }   
        if(file.exists())         //checks file exists or not  
        desktop.open(file);              //opens the specified file  
        }  
        catch(Exception e)  
        {  
         File file2 =new File("default.dat");
        desktop.open(file2);
        }  
    }
}