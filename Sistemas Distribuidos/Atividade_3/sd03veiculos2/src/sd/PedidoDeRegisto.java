package sd;
public class PedidoDeRegisto extends Pedido implements java.io.Serializable {
 
    public Registo reg;
 
    public PedidoDeRegisto(Registo r) {
        this.reg= r;
    }
 
    public Registo getRegisto() {
        return reg;
    }
}