import java.text.Normalizer;
import java.util.List;

import java.util.*;

public class MemoryProvider extends AbstractProvider {  
    private List<String> list = new ArrayList<String>(); //Lista para adicionar elementos

    public List<String> getWords(){
        Set<String> set = new HashSet<>(list);//HashSet para remover duplicados
        list.clear();
        list.addAll(set);   
        Collections.sort(list); //Ordenar lista
        return list;
    }
    public void addWord(String word){
        String word1=normalized(word); //Normalizar a palavra
        if(word1.length()>0) list.add(word1); //se palavra <0 nao adicionar 
    }
    private String normalized(String word) { //NORMALIZED
        String a=word.trim().replaceAll("\\s", "");

        String string = Normalizer.normalize(a, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{Punct}", "");
        
        return string.toLowerCase();
    }
  }