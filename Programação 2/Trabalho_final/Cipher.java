import java.text.Normalizer;
import java.util.List;
import java.util.Random;
import java.util.*;

abstract class AbstractProvider {
        abstract List<String> getWords();
}

public class Cipher{

public static String normalized(String naturalText){
       
        String a=naturalText.trim().replaceAll("\\s", "");

        String string = Normalizer.normalize(a, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        string = string.replaceAll("\\p{Punct}", "");
        
        return string.toLowerCase();
}

public static String encode(String plainText, int cols){

        Random r = new Random(); //Criar objeto da classe Random

        //caso length de plainText n seja multiplo de cols
        while(plainText.length()%cols!=0){
                char c = (char)(r.nextInt(26) + 'a'); //caracter aleatório para adicionar até que numero de caracteres de plainText seja multiplo de cols
                plainText=plainText+c; 
        }

        int n=plainText.length()/cols; //numero de linhas

        char[][] matriz=new char[n][cols]; //declaraçao da matriz para codificar a string

        int l=0; //contador para percorrer a string
        String f = new String(); //declaraçao da string utilizada para retornar a string final

                //codificar a string normalizada na matriz
                for(int i=0; i<n; i++){
                        for(int j=0; j<cols; j++){
                                matriz[i][j]=plainText.charAt(l);
                                l++;
                        } 
                }
        
        //Colocar na string de retorno a primeira coluna por linha, a segunda etc...
        for(int i=0; i<cols; i++){
                for(int j=0; j<n; j++){
                        f=f+matriz[j][i];
                }
        }


        return f;
}

public static List<Integer> findDividers(int x){
        List<Integer> divisores = new ArrayList<Integer>(); //Criar lista 

        for(int i=1; i<=x; i++){ //Ciclo para percorrer todos os numeros até x inclusive
                if(x%i==0){ //Se divisor
                        divisores.add(i); //Adicionar à lista
                }
        }
        return divisores;

}

public static List<String> breakCipher(String cipherText, List<String> words) {
        
        var r=findDividers(cipherText.length()); //Lista com os divisores
        List<String> result=new ArrayList<String>(); //Lista para retornar as frases possiveis
        for(int i=0; i<r.size(); i++) { //Ciclo para percorrer todos os divisores
                result.addAll(explore(encode(cipherText,r.get(i)),words)); //Codificar a cifra consoante os divisores e enviar para a funçao que verifica se as palavraas pertencem à frase
        }

        return result;
}
public static List<String> explore(String candidate, List<String> words) { //algoritmo do professor
        List<String> result = new Vector<>();
        for (var word : words) {
        if (candidate.startsWith(word)) {
        var suffix = candidate.substring(word.length()); // candidate = word + suffix
        var children = explore(suffix, words);
        for (var child : children) {
        var solution = String.format("%s %s", word, child);
        result.add(solution);
        }
        }
        }
        if(result.isEmpty()){ // caso nao existam frases possiveis retorna o candidato
                result.add(candidate);
        }
        return result;
}
}


