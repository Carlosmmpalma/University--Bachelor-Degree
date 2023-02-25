package sd;
public class PedidoDeConsulta extends Pedido implements java.io.Serializable {
 
    private String matricula;
 
    public PedidoDeConsulta(String matricula) {
        this.matricula= matricula;
    }
 
    public String getMatricula() {
        return matricula;
    }
}