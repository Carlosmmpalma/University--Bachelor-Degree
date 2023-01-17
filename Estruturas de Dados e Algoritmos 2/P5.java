import java.io.IOException;

public class P5 {
    public static void main(String[] args) throws IOException{
      
    }
}
  
  class Project {
    /*
      Cria um projecto com TASKS tarefas.
    */
    public Project(int tasks){

    }
  
    /*
      Acrescenta a tarefa PRECEDENT às tarefas de que a tarefa TASK depende.
    */
    public void addDependency(int task, int precedent){

    }
  
    /*
      Acrescenta as dependências indicadas na regra RULE.
  
      [Em alternativa ou como complemento do método anterior.]
    */
    public void addDependencies(int[] rule){

    }
  
    /*
      Calcula uma ordem possível de execução das tarefas do projecto e
      devolve um array que contém as tarefas por essa ordem.
  
      Na ordem calculada, quando há duas tarefas que é possível executar, a
      com menor número aparece antes da com maior número.
    */
    public int[] computeOrder(){

    }
  }