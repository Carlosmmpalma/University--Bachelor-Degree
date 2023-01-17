public class SmartPhone extends Device {
    private long imei=0;
    private double peso=getWeight();

    public SmartPhone(double weight, long memory,long imei) {
        super("Bravo de Esmolfe",weight, memory);
        this.imei= imei;
    }

    public long getIMEI(){
        return imei;
    }

    public boolean isHeavy(){
        return peso>100;
    }


}