import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleFileProvider extends AbstractProvider{

    private List<String> list = new ArrayList<String>(); //Lista para adicionar elementos

    public SimpleFileProvider(String fileName) throws java.io.IOException{
        File file1 = new File(fileName);
        if(!file1.exists()) throw new IOException();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(normalized(line));
            }
            
        }

    }

    public List<String> getWords() {
        Set<String> set = new HashSet<>(list);//HashSet para remover duplicado
        list.clear();
        list.addAll(set);   
        Collections.sort(list); //Ordenar lista
        return list;
    }

    private String normalized(String word) { //NORMALIZED
        String a=word.trim().replaceAll("\\s", "");

        String string = Normalizer.normalize(a, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{Punct}", "");
        
        return string.toLowerCase();
    }
}
    
    