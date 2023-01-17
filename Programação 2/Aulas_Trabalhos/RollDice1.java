import java.util.Random;

public class RollDice1 {
    public static void main(String[] args) {
        
        Die1 d = new Die1();


        d.toss();
        System.out.println(d.getValue());
        d.toss();
        System.out.println(d.getValue());
        d.toss();
        System.out.println(d.getValue());

    }
}

class Die1{
   private int value = 1;
   private Random generator = new Random();

   public void toss(){
    value=generator.nextInt(6)+1;
   }

   public int getValue(){
       return value;
   }
}