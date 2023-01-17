import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P4{
    private static final int MIN_EMPLOYEES_TESTS=1;
    private static final int MAX_EMPLOYEES=2500;
    private static final int MIN_FRIENDS=0;
    private static final int MAX_FRIENDS=15;
    private static final int MAX_TESTS=60;

    public static void main(String[] args) throws IOException{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   
    //Input para o  grafo
    int E=Integer.parseInt(reader.readLine());
    if(E<MIN_EMPLOYEES_TESTS || E>MAX_EMPLOYEES) throw new IOException();
    Graph g = new Graph(E);

    for(int i=0; i<E; i++){
        String Friends[]=reader.readLine().split(" ");
        int N=Integer.parseInt(Friends[0]);
        if(N<MIN_FRIENDS || N>MAX_FRIENDS) throw new IOException();
        if(Friends.length!=N+1) throw new IOException();
            for(int j=1;j<Friends.length;j++){
                int f=Integer.parseInt(Friends[j]);
                g.addEdge(i,f);
            }
    }

    //Resoluçao
    int T=Integer.parseInt(reader.readLine());
    if(T<MIN_EMPLOYEES_TESTS || T>=MAX_TESTS) throw new IOException();
    ArrayList<Integer> n=new ArrayList<Integer>();
    for(int i=0; i<T; i++){
        int t=Integer.parseInt(reader.readLine());
        int[] arr=g.bfs(t);
        Arrays.sort(arr);
        int maior=0;
        int dia=0;
        for (int j=0; j<arr.length; j++){
            int count=1;
            if(arr[j]==Integer.MAX_VALUE || arr[j]==0){ 
                count=0;
            }else{
            while((j+1<arr.length && arr[j]==arr[j+1])){ 
                j++;
                count++;
            }
        }
        if(count>maior) {
            maior=count;
            dia=arr[j];
        }
        }
        n.add(maior);
        n.add(dia);
    }

    for (int i=0;i<n.size();i++){
        if(n.get(i)==0){
            System.out.println(0);
            i++;
        }else{
            System.out.println(n.get(i)+" "+n.get(i+1));
            i++;
        }
    }
}
}

class Graph {
public static final int INFINITY = Integer.MAX_VALUE;
public static final int NONE = -1;
private static enum Colour{WHITE, GREY, BLACK};

int nodes; // number of nodes
List<Integer>[] adjacents; // adjacency lists
@SuppressWarnings("unchecked")

public Graph(int nodes){
    this.nodes = nodes;
    adjacents = new List[nodes];
        for (int i = 0; i < nodes; ++i)
            adjacents[i] = new LinkedList<>();
}

/* Adds the (directed) edge (U,V) to the graph. */
public void addEdge(int u, int v){
    adjacents[u].add(v);
}

public int[] bfs(int s){
    Colour[] colour = new Colour[nodes];
    int[] d = new int[nodes]; // dist^ancia para S
    int[] p = new int[nodes]; // predecessor no caminho de S
    for (int u = 0; u < nodes; ++u){
        colour[u] = Colour.WHITE;
        d[u] = INFINITY;
        p[u] = NONE;
    }
    colour[s] = Colour.GREY;
    d[s] = 0;
    Queue<Integer> Q = new LinkedList<>();
    Q.add(s);

    while (!Q.isEmpty()){
        int u = Q.remove(); // visita n ́o U
        for (Integer v : adjacents[u])
            if (colour[v] == Colour.WHITE){
                colour[v] = Colour.GREY; // V  ́e um novo n ́o
                d[v] = d[u] + 1;
                p[v] = u;
                Q.add(v);
            }
    colour[u] = Colour.BLACK; // n ́o U est ́a tratado
    }
    return d;
}

}