public class Laptop extends Device  {
    private boolean risc;
    private double peso=getWeight();

    public Laptop(String brand,double weigth,boolean risc){
        super(brand,weigth,32768);
        risc=risc;
    }

    public boolean isRISC(){
        return risc;
    }

    public boolean isHeavy(){
        return peso>750;
    }
}
