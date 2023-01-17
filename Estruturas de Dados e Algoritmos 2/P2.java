import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2 {
    private static final int MIN=1;
    private static final int MAX_COINS_QUESTIONS=100;
    private static final int QUANTITY=200000;
    
    public static void main(String argv[]) throws IOException {
    
    BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

    int n=Integer.parseInt(reader.readLine()); //Quantidade de moedas
    if(n<MIN || n>MAX_COINS_QUESTIONS) throw new IOException();

    String[] t =reader.readLine().split(" "); //Array para valor de cada tipo de moeda

    int p=Integer.parseInt(reader.readLine()); //Quantidade de perguntas
    if(p<MIN || p>MAX_COINS_QUESTIONS) throw new IOException();
    
    String[] Q=new String[p];//Array para as quantias

    for(int i=0; i<p; i++){
        Q[i]=reader.readLine();
    }
    
    M pp=new M(n);
    pp.setValues(t);

    for(int i=0; i<Q.length; i++){
        int number=Integer.parseInt(Q[i]);
        if(number<MIN || number>QUANTITY) throw new IOException();
        System.out.println(number+": ["+pp.change(number)+"]");
    }

    }
}

class M{
    private int[] moedas;
    private static final int MIN=1;
    private static final int MAX_TYPEOFCOIN=10000;

    public M(int coins){
        this.moedas=new int[coins];
    }

    public void setValues(String[] values) throws IOException{
        for(int i=0; i<values.length; i++){
            int num=Integer.parseInt(values[i]);
            if(num<MIN || num>MAX_TYPEOFCOIN) throw new IOException();
            moedas[i]=num;
        }
    }

    public int change(int amount){
        int[] quantia=new int[amount+1];
        quantia[0]=0;
        for(int i=1;i<=amount;i++){
            int minim=Integer.MAX_VALUE;
            for(int j=0;j<moedas.length;j++){
                if(i-moedas[j]>=0){
                    minim=Math.min(minim,1+(quantia[i-moedas[j]]));
                    }
            }
            quantia[i]=minim;
        }
        
        return quantia[amount];
}
}



    


