import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class P3 {
    private static final int MIN_NUMBER_TESTS_BLOCKAGES=0;
    private static final int MAX_NUMBER_TESTS=1000;
    private static final int MAX_NUMBER_BLOCKAGES=500;
    private static final int MIN_ROADS_COORDINATES=1;
    private static final int MAX_NUMBER_ROADS=30;
    //private static final long MAX_NUMBER_WAYS=Long.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int C=Integer.parseInt(reader.readLine());
        if(C<MIN_NUMBER_TESTS_BLOCKAGES || C>MAX_NUMBER_TESTS) throw new IOException();

        for(int i=0;i<C;i++){
            //Criar cidade
            int R=Integer.parseInt(reader.readLine());
            if(R<MIN_ROADS_COORDINATES || R>MAX_NUMBER_ROADS) throw new IOException();
            City cidade=new City(R);

            //Posiçao inicial
            String[] S=reader.readLine().split(" ");
            int Sx=Integer.parseInt(S[0]);
            if(Sx<MIN_ROADS_COORDINATES || Sx>R) throw new IOException();
            int Sy=Integer.parseInt(S[1]);
            if(Sy<MIN_ROADS_COORDINATES || Sy>R) throw new IOException();

            //Posiçao final
            String[] E=reader.readLine().split(" ");
            int Ex=Integer.parseInt(E[0]);
            if(Ex<MIN_ROADS_COORDINATES || Ex>R) throw new IOException();
            int Ey=Integer.parseInt(E[1]);
            if(Ey<MIN_ROADS_COORDINATES || Ey>R) throw new IOException();

            //Bloqueios
            int B=Integer.parseInt(reader.readLine());
            if(B<MIN_NUMBER_TESTS_BLOCKAGES || B>MAX_NUMBER_BLOCKAGES) throw new IOException();

            for(int j=0;j<B;j++){
                String[] P=reader.readLine().split(" ");
                int Px=Integer.parseInt(P[0])-1;
                if(Px<MIN_ROADS_COORDINATES || Px>R) throw new IOException();
                int Py=Integer.parseInt(P[1])-1;
                if(Py<MIN_ROADS_COORDINATES || Py>R) throw new IOException();
                cidade.addBlockage(Px,Py,P[2]);
            }
            System.out.println(cidade.numberOfWays(Sx,Sy,Ex,Ey));
        }
    }
}
  
  class City {

    private int[][] cidade;
    private char[][] bloqueio;

    public City(int size){
        this.cidade=new int[size][size];
        this.bloqueio=new char[size][size];
    }
    /*
      Acrescenta o bloqueio do segmento de rua que tem início no cruzamento
      (X,Y), entre a rua Norte/Sul número X e a rua Este/Oeste número Y, na
      direcção DIRECTION (que pode ser "N", "E", "S" ou "W").
    */
    public void addBlockage(int x, int y, String direction){
        if(direction=="S"){
            bloqueio[x-1][y-2]='B';
        }else if(direction=="N"){
            bloqueio[x-1][y-1]='B';
        }else if(direction=="W"){
            bloqueio[x-2][y-1]='B';
        }else{
            bloqueio[x-1][y-1]='B';
        }

    }
  
    /*
      Calcula e devolve o número de caminhos que existem, do cruzamento
      (SX,SY) até ao cruzamento (EX,EY), seguindo sempre na direcção Norte
      ou na direcção Este.
    */
    public int numberOfWays(int sx, int sy, int ex, int ey){
        cidade[0][0]=1;

        //Preencer 1's para cima e na lateral
        for(int i = 1; i <cidade.length;i++){
            cidade[sx-1][i]=1;
            cidade[i][sy-1]=1;
        }
        
        for(int i=sx;i<cidade.length;i++){
                cidade[i][sy]=cidade[i-1][sy]+cidade[i][sy-1]; 
            for(int j=sy;j<cidade.length;j++){
                cidade[sx][j]=cidade[sx-1][j]+cidade[sx][j-1]; //Preencher na vertical
                cidade[i][j]=cidade[i-1][j]+cidade[i][j-1];
            }
        }
        return cidade[ex-1][ey-1]; //Retornar posiçao final
    }
  }