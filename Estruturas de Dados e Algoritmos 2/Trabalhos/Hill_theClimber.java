import java.io.*;
import java.util.*;

public class Hill_theClimber {

    //Constraints
    private static final int MIN_HOLD_POINTS_TESTS_REACH=1;
    private static final int MAX_HOLD_POINTS=30000;
    private static final int MIN_HEIGHT=2;
    private static final int MAX_HEIGHT=40000;
    private static final int MIN_COORD_X=0;
    private static final int MAX_COORD_X=10000;
    private static final int MAX_TESTS=20;
    private static final int MAX_REACH=200;

    public static void main(String[] args) throws IOException{
    //INPUT
    Graph graph = new Graph();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] s=reader.readLine().split(" ");
    
    //Number of hold points
    int N=Integer.parseInt(s[0]);
    if(N<MIN_HOLD_POINTS_TESTS_REACH || N>MAX_HOLD_POINTS) throw new IOException();

    //Array de Vértices para guardar as coordenadas de cada vértice
    Vertex[] coordinates=new Vertex[N];

    //Height of the climb
    int H=Integer.parseInt(s[1]);
    if(H<MIN_HEIGHT || H>MAX_HEIGHT) throw new IOException();
    
    //Number of test cases
    int C=Integer.parseInt(s[2]);
    if(C<MIN_HOLD_POINTS_TESTS_REACH || C>MAX_TESTS) throw new IOException();
    
    //CRIAR VERTICES
    int number=0; //Indice que representa cada vértice
    for(int i=0; i<N;i++){

        String[] t=reader.readLine().split(" ");

        int x=Integer.parseInt(t[0]);
            if(x<MIN_COORD_X || x>MAX_COORD_X) throw new IOException();

        int y=Integer.parseInt(t[1]);
            if(y<MIN_HOLD_POINTS_TESTS_REACH || y>H-1) throw new IOException();

        graph.addVertex(number);    //Cria o vértice/node no grafo
        coordinates[number]=new Vertex(x,y); //Cria um novo objecto associado ao vértice com coordenadas c e y
        number++;
    }

    Arrays.sort(coordinates);

    //Testes (alcance)
    int[] R=new int[C];
    for(int i=0; i<C;i++){
    R[i]=Integer.parseInt(reader.readLine());
    if(R[i]<MIN_HOLD_POINTS_TESTS_REACH || R[i]>MAX_REACH) throw new IOException();
    }

    //Verificar qual o maior alcance
    int max_range = R[0]; 
    for(int i = 1; i < R.length; i++){
        if(max_range < R[i]){
            max_range = R[i];
        }
    }
    
    //ADICIONAR ARESTAS
    for(int i=0; i<N;i++){
        for(int j=i+1; j<N;j++){
         int d_y=coordinates[i].getV_y()-coordinates[j].getV_y(); //Distancia de vértice i a j, tendo em conta a altura
            if(d_y>=-max_range && d_y<=max_range){ //Apenas calculamos a distância caso o ponto esteja à distancia do alcance máximo em relaçao às coordenadas y
                double d=Math.sqrt(Math.pow((coordinates[j].getV_x()-coordinates[i].getV_x()),2)+Math.pow((coordinates[j].getV_y()-coordinates[i].getV_y()), 2));
                    if(d<=max_range) graph.addEdge(i,j,d); //se a distancia entre pontos <alcance maximo, adiconar aresta
            }else{
                break;
            }
        }
    }

    //Procura em largura para cada teste 
    for(int i=0; i<R.length;i++){
        int result=graph.bfs(N,R[i],H,coordinates);
        if(R[i]>=H){
            System.out.println("0");
        }else if(result==Integer.MAX_VALUE){
            System.out.println("unreachable");
        }else{
            System.out.println(result);
        }
    }
}
}

class Graph{
 
//USA HASHMAP PARA GUARDAR OS VÉRTICES DO GRAFO, COM UMA LISTA DE ADJACÊNCIAS IMBUTIDA
private Map<Integer, List<Edge>> map= new HashMap<>();

public static final int INFINITY = Integer.MAX_VALUE;
public static final int NONE = -1;
private static enum Colour{WHITE, GREY, BLACK};

//ADICIONA VÉRTICE AO GRAFO
public void addVertex(int s){
    map.put(s, new LinkedList<Edge>());
}

//ADICIONA ARESTA
public void addEdge(int source, int destination,double value){
        map.get(source).add(new Edge(destination,value));
        map.get(destination).add(new Edge(source,value));
}

//PROCURA EM LARGURA DO VÉRTICE INICIAL AO FINAL
public int bfs(int nodes,int r,int h,Vertex[] coordinates){
    Colour[] colour = new Colour[nodes];
    int[] d = new int[nodes]; // distância para S
    int[] p = new int[nodes]; // predecessor no caminho de S

    for (int u = 0; u < nodes; ++u){
        colour[u] = Colour.WHITE;
        d[u] = INFINITY;
        p[u] = NONE;
    }
    
    //Inicializar todos os vértices que podem ser pontos de partida
    Queue<Integer> Q = new LinkedList<>();
    int j=0;
    
    while(j<nodes && coordinates[j].getV_y()<=r){
    int index = j;
    colour[index] = Colour.GREY;
    d[index] = 1;
    Q.add(index);
    j++;
    }
    
    //Realiza a procura em largura e retorna o caminho mais curto até um dos possiveis vértices finais
    int min=Integer.MAX_VALUE;
    while (!Q.isEmpty()){
        int u = Q.remove(); // visita n ́o U
            for (Edge w : map.get(u)) {
                int vertice=w.getE_v();
                double valor=w.getE_Value();
                if (colour[vertice] == Colour.WHITE && valor<=r ){
                    colour[vertice] = Colour.GREY; // V  ́e um novo n ́o
                    d[vertice] = d[u] + 1;
                    p[vertice] = u;
                    Q.add(vertice);
                    
                }
            }
            
    colour[u] = Colour.BLACK; // n ́o U est ́a tratado
        if(coordinates[u].getV_y()>=h-r){
            if(d[u]<min) min=d[u];
        }
    }
    return min;
}

}

//OBJETO PARA COORDENADAS DE CADA VÉRTICE
class Vertex implements Comparable<Vertex>{
    int x;
    int y;

public Vertex(int x1,int y1){
    this.x=x1;
    this.y=y1;
}

public int getV_x(){return x;}

public int getV_y(){return y;}


@Override
public int compareTo(Vertex anVertex)
  {
    if (y < anVertex.y)
      return -1;

    if (y == anVertex.y)
      return 0;

    return 1;			
  }
}

//OBJETO PARA VALOR DAS ARESTAS
class Edge{
    int vertex;
    double value;

public Edge(int vertex1,double value1){
    this.vertex=vertex1;
    this.value=value1;
}

public int getE_v(){return vertex;}

public double getE_Value(){return value;}

}