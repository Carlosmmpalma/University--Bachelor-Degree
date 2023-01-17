
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mosaics {
    //Constraints
    private static final int MIN_R_C=1;
    private static final int MAX_R_C=1000;
    private static final int MIN_W=1;
    private static final long MAX_W=Long.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        
        //INPUT
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] t =reader.readLine().split(" ");
        int R=Integer.parseInt(t[0]); //Linhas
        if(R<MIN_R_C || R>MAX_R_C) throw new IOException();
        int C=Integer.parseInt(t[1]); //Caracteres por linha
        if(C<MIN_R_C || C>MAX_R_C) throw new IOException();

        //INPUT DE STRINGS
        ArrayList<Integer> n=new ArrayList<Integer>();
        for(int i = 0; i<R;i++){
        String str=reader.readLine();
        if(str.length()!=C) throw new IOException();
        
        ArrayList<Integer> p=numberOfSequences(str); 
        for(int j=0; j<p.size(); j++){
            
            n.add(p.get(j)); //Lista com numero de letras iguais seguidas na string
        }

    }
        long W=1;
        for(int j=0; j<n.size(); j++){
        W=W*sequenceCombinations(n.get(j)); // Numero total de combinações possiveis
    }
        if(W<MIN_W || W>MAX_W) throw new IOException();
        System.out.println(W);
    }
 
    public static long sequenceCombinations(int n){ //Retorna as combinações
        int mosaicos[] = {16,12,10,8,6,4,3,2,1}; //Peças disponiveis
        long[] array = new long[n+1];
        array[0] =1;
        array[1] =1;

        for(int i = 2; i<=n; i++){ //Percorrer o array onde serão guardadas as combinações possveis em letras iguais seguidas
            long soma=0;
            for(int j = 0; j<mosaicos.length;j++){ //Percorre array com tipo de peças possiveis
                if(i-mosaicos[j]>=0){ //Se a sobra for superior a 0
                    soma=soma+array[i-mosaicos[j]]; //Soma o numero de combinações
                }
            }
            array[i]=soma;
        }

        return array[n];    //Retorna as combinações possiveis para n  letras seguidas
    }

     public static ArrayList<Integer> numberOfSequences(String s) throws IOException{

            ArrayList<Integer> n = new ArrayList<Integer>();
            for(int i = 0;i<s.length();i++){ //Percorre a string
                if((s.charAt(i) < 'A' && s.charAt(i) > 'Z') && s.charAt(i)!='.') throw new IOException();
                int count = 1;
                while((i+1) < s.length() && s.charAt(i)==s.charAt(i+1)){ //Verifica se a letra seguinte é igual à anterior e termina quando atinge '.'
                    i++;
                    count++;
                }
                n.add(count++);
            }

            return n; //Retorna a lista com as combinações possiveis por linha
        }

}
