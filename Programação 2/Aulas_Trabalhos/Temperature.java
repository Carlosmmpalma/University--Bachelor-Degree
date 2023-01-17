public class Temperature {

    private double celsius=0.0;

    public void setCelsius(double t){
        this.celsius=t;
    }

    public double toCelsius(){
        return celsius;
    }

    public void setFahrenheit(double fahr){
        setCelsius((fahr-32)/1.8);
    }

    public double toFahrenheit(){
        return 1.8*celsius+32;
    }
    public static void main(String[] args) {
        Temperature t = new Temperature();
        t.setCelsius(10);
        System.out.println("10 C= "+t.toFahrenheit()+" F");
        t.setFahrenheit(32);
        System.out.println("32 F= "+t.toCelsius()+" C");
    }
