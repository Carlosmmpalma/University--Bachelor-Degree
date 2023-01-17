import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.*;

public class P7 {
    public static void main(String[] args) throws IOException{
      //leitura dos dados e controlo da execução do programa
    }
}
  
  /*
    Pessoas numa rede social e a idade das suas relações (em dias).
  */
  class SocialNetwork {
    /*
      Inicializa uma rede social com NPERSONS elementos.
    */
    public SocialNetwork(int nPersons){

    }
  
    /*
      Estabelece a existência de uma relação entre PERSON1 e PERSON2, cuja
      idade é DAYS dias.
    */
    public void addRelation(int person1, int person2, int days){

    }
  
    /*
      Calcula e devolve o peso de um backbone máximo da rede.
    */
    public int maximumBackboneWeight(){
        return 1;
    }
  }

  class Graph{
      int nodes;
      List<Edge>[] adjacentes;


      public void addEdge(Edge edge1,Edge edge2){
          adjacentes[edge1].add(edge2);
      }

    class Edge implements Comparable{
        private int dest;
        private int weight;

      public Edge(int destino,int peso){
        dest=destino;
        weight=peso;
      }
        
        @Override
        public int compareTo(Object o) {
            //<0-> menor; >0 ->maior; =0 iguais
            return 0;
        }
          
      }

  }
