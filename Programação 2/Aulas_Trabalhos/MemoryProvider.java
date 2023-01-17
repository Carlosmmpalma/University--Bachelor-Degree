import java.text.Normalizer;
import java.util.List;
import java.util.Random;
import java.util.*;

public class MemoryProvider extends AbstractProvider {  
    private List<String> list = new ArrayList<String>();

    public List<String> getWords(){
        Set<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);   
        Collections.sort(list);
        return list;
    }
    public void addWord(String word){
        String word1=normalized(word);
        if(word1.length()>0) list.add(word1);
    }
    private String normalized(String word) {
        String a=word.trim().replaceAll("\\s", "");

        String string = Normalizer.normalize(a, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{Punct}", "");
        
        return string.toLowerCase();
    }
  }