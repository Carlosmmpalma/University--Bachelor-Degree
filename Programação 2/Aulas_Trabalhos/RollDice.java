import java.util.Random;

public class RollDice {
    public static void main(String[] args) {
        Die d1 = new Die();
        Die d2 = new Die();
        Die d3 = new Die();

        d1.toss();
        d2.toss();
        d3.toss();

        System.out.println(d1.getValue());
        System.out.println(d2.getValue());
        System.out.println(d3.getValue());
    }
}

class Die{
   private int value = 1;
   private Random generator = new Random();

   public void toss(){
    value=generator.nextInt(6)+1;
   }

   public int getValue(){
       return value;
   }
}