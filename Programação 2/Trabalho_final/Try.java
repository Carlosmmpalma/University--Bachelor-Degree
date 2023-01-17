import java.io.*;
import java.text.*;
import java.util.*;

public class Try{

    public static String normalized(String word) {
        String a=word.trim().replaceAll("\\s", "");

        String string = Normalizer.normalize(a, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{Punct}", "");
        
        return string.toLowerCase();
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{

        List<String> list = new ArrayList<String>();

        try(BufferedReader br = new BufferedReader(new FileReader("AAA.txt"))) 
        {
            String line;
            String[] lines;
            while ((line = br.readLine()) != null) {
                line=line.trim();
                lines=line.split(" ");
                
                for(String s:lines) {
                    list.add(normalized(s));
                }
            }
        }
    
        Set<String> set = new HashSet<>(list);//HashSet para remover duplicado
        list.clear();
        list.addAll(set);   
        Collections.sort(list); //Ordenar lista
        
        System.out.println(list);
    }
}