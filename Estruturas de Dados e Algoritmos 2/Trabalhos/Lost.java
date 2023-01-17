import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    //CONSTRAINTS
    private static final int MIN_TEST_CASES=1;
    private static final int MAX_TEST_CASES=18;
    private static final int MIN_ROWS_COLS=3;
    private static final int MAX_ROWS_COLS=60;
    private static final int MIN_WHEELS_ROW_COL=0;
    private static final int MAX_WHEELS=9;
    private static final int MIN_TIME=-1000000;
    private static final int MAX_TIME=1000000;

    public static void main(String[] args) throws IOException{

    BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

    //NUMBER OS TESTS
    int N=Integer.parseInt(reader.readLine());
    if(N<MIN_TEST_CASES || N>MAX_TEST_CASES) throw new IOException();
    
    //Print final
    ArrayList<String> print=new ArrayList<String>();

    for (int i=0; i<N;i++){
        String[] t=reader.readLine().split(" ");

        //ROWS
        int R=Integer.parseInt(t[0]);
        if(R<MIN_ROWS_COLS || R>MAX_ROWS_COLS) throw new IOException();

        //COLS
        int C=Integer.parseInt(t[1]);
        if(C<MIN_ROWS_COLS || C>MAX_ROWS_COLS) throw new IOException();

        //NUMBER MAGICAL WHEELS
        int M=Integer.parseInt(t[2]);
        if(M<MIN_WHEELS_ROW_COL || M>MAX_WHEELS) throw new IOException();

        //MAP OF THE ISLAND
        String[][] map=new String[R][C];

        for(int j=0;j<R;j++){
            map[j]=reader.readLine().split("");
        }

        Graph g=new Graph(C*R);
        int[] whells=new int[M+1];
        int end=0;
        
        //EDGES
        for(int j=0; j<R; j++){
            for(int k=0; k<C; k++){
                //GRASS
                if((map[j][k].compareTo("G"))==0){
                    if(k-1>=0 && (map[j][k-1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k-1),1,'A'); //'A' podem passar todos
                    if(k+1<=C-1 && (map[j][k+1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k+1),1, 'A');
                    if(j-1>=0 && (map[j-1][k].compareTo("O"))!=0) g.addEdge(j*C+k, C*(j-1)+k,1,'A');
                    if(j+1<=R-1 && (map[j+1][k].compareTo("O"))!=0) g.addEdge(j*C+k,C*(j+1)+k,1,'A');
                }
                //WATER
                else if((map[j][k].compareTo("W"))==0){
                    if(k-1>=0 && (map[j][k-1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k-1),2, 'K'); //Apenas a Kate consegue passar
                    if(k+1<=C-1 && (map[j][k+1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k+1),2, 'K');
                    if(j-1>=0 && (map[j-1][k].compareTo("O"))!=0) g.addEdge(j*C+k, (j-1)*C+k,2,'K');
                    if(j+1<=R-1 && (map[j+1][k].compareTo("O"))!=0) g.addEdge(j*C+k,(j+1)*C+k,2,'K');
                }
                //END
                else if((map[j][k].compareTo("X"))==0){
                        end=j*C+k;
                }
                //OBSTACLES
                else if((map[j][k].compareTo("O"))==0){
                }
                //WHELLS
                else{
                    int indice=Integer.parseInt(map[j][k]);
                    whells[indice]=j*C+k;
                    if(k-1>=0 && (map[j][k-1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k-1),1,'A'); //Whells podem ser usadas como grass cells
                    if(k+1<=C-1 && (map[j][k+1].compareTo("O"))!=0) g.addEdge(j*C+k, j*C+(k+1),1, 'A');
                    if(j-1>=0 && (map[j-1][k].compareTo("O"))!=0) g.addEdge(j*C+k, C*(j-1)+k,1,'A');
                    if(j+1<=R-1 && (map[j+1][k].compareTo("O"))!=0) g.addEdge(j*C+k,C*(j+1)+k,1,'A');
                }

            }
        }

        //MAGICAL WHEELS
        for(int j=1; j<M+1;j++){
            String[] mw=reader.readLine().split(" ");
            int r_mw=Integer.parseInt(mw[0]);
            if(r_mw<=0 || r_mw>=R-1) throw new IOException();

            int c_mw=Integer.parseInt(mw[1]);
            if(c_mw<=0 || c_mw>=C-1) throw new IOException();

            int time=Integer.parseInt(mw[2]);
            if(time<MIN_TIME || time>MAX_TIME) throw new IOException();

            int source=whells[j];
            int destination=r_mw*C+c_mw;
            g.addEdge(source, destination, time,'J'); //Apenas o John consegue passar
        }

        String[] pos=reader.readLine().split(" ");
        int r_j=Integer.parseInt(pos[0]); //ROWS JOHN
        int c_j=Integer.parseInt(pos[1]); //COLS JOHN
        int r_k=Integer.parseInt(pos[2]); //ROWS KATE
        int c_k=Integer.parseInt(pos[3]); //ROWS JONH
        int number_vertex=C*R;


        print.add("Case #"+(i+1));
        print.add("John "+g.Bellman_Ford(number_vertex, r_j*C+c_j, end));
        print.add("Kate "+g.Dijkstra(number_vertex, r_k*C+c_k, end));

    }

    for (int i=0;i<print.size(); i++){
        System.out.println(print.get(i));
    }

    }
    
}

class Graph{
public static final int INFINITY = Integer.MAX_VALUE;
public static final int NONE = -1;

int nodes; // number of nodes
List<Edge>[] adjacents; // adjacency lists
@SuppressWarnings("unchecked")

public Graph(int nodes){
    this.nodes = nodes;
    adjacents = new List[nodes];
        for (int i = 0; i < nodes; ++i)
            adjacents[i] = new LinkedList<>();
}

// Adiciona uma aresta orientada ao grafo
public void addEdge(int u, int v, int p, char person){
    adjacents[u].add(new Edge(v, p, person));
}

//Objeto para arestas com valor da aresta, vértice de destino e quem pode percorrer a mesma
class Edge{
    private int value;
    private int destination;
    private char person;

    public Edge(int destination,int value,char person){
        this.value = value;
        this.destination = destination;
        this.person=person;
    }

    public int getValue(){return value;}

    public int getDestination(){return destination;}

    public char getPerson(){return person;}

}

//Algoritmo de Bellman_Ford adaptado à nossa forma de ver o problema
public String Bellman_Ford(int nodes,int source,int destination){

    int[] d = new int[nodes]; // distância para S

    for (int u = 0; u < nodes; u++){ //Inicializa todos os vértices
        d[u] = INFINITY;
    }

    d[source]=0;//Nó inicial

int count=0; //Para verificar quando não há caminhos mais curtos para nenhum vértice

for(int o=1;o<nodes-1; o++){ 
    for(int i=0;i<nodes;i++){
    for (Graph.Edge u : adjacents[i]){
            int v=u.getDestination();
            int w=u.getValue();
            char pe=u.getPerson();
            if ((pe=='A' || pe=='J') && d[i] != INFINITY && d[i] + w < d[v]){
                d[v]=d[i]+w;
                count++;
            }
    }
    }
    if(count==0) break;
    count=0;
}
    //Verificar se existem ciclos 
    for(int i=0;i<nodes;i++){
    for (Graph.Edge u : adjacents[i]){  
            int v=u.getDestination();
            int w=u.getValue();
            char pe=u.getPerson();
            if ((pe=='A' || pe=='J') && d[i] != INFINITY && d[i] + w < d[v]){
                return "Lost in Time";
            }
    }
}

    if(d[destination]==INFINITY) return "Unreachable";
    else return String.valueOf(d[destination]);
}

//Algoritmo de Dijkstra adaptado à nossa forma de ver o problema
public String Dijkstra(int nodes, int source,int destination){

    int[] d = new int[nodes]; // distância para S
    Queue<Integer> Q = new LinkedList<>(); //Priority Queue

    for (int u = 0; u < nodes; u++){ //Inicializa todos os vértices
        d[u] = INFINITY;
    }

    d[source]=0;//Nó inicial
    Q.add(source); //Adiciona à queue o nó inicial

    //Verifica as distâncias para todos os nós
    while (!Q.isEmpty()){
        int e = Q.remove(); // visita n ́o U
        for (Graph.Edge u : adjacents[e]){
            int v=u.getDestination();
            int w=u.getValue();
            char pe=u.getPerson();
            if ((pe=='A' || pe=='K') && d[e] != INFINITY && d[e] + w < d[v]){
                d[v]=d[e]+w;
                Q.add(v);
            }
        }
    }

    if(destination==-1 || d[destination]==INFINITY) return "Unreachable";
    else return String.valueOf(d[destination]);

}
}


