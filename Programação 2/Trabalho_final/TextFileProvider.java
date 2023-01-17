import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class TextFileProvider extends AbstractProvider{

    private List<String> list = new ArrayList<String>(); //Lista para adicionar elementos
    
    public TextFileProvider(String fileName) throws java.io.IOException{
        File file1 = new File(fileName);
        if(!file1.exists()) throw new IOException(); //Verificar se ficheiro existe, caso contrário levanta excepçao.

        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line; //String para linha
            String[] lines; //Array de strings para guardar as palavras
            while ((line = br.readLine()) != null) { //Percorrer ficheiro
                line=normalized(line); //Normalizar o texto
                lines=line.split(""); //Adiciona cada palavra espaçada no array

                for(String s:lines) { //Percorrer array
                    list.add(s); //Adiciona à lista
                }
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
