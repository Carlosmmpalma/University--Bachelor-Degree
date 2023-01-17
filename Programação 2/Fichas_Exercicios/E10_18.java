import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class E10_18 {
    public static void main(String[] args) {
        ArrayList<String> citation=new ArrayList<String>();
        Random rand=new Random();
        int index;
        Scanner scanner=new Scanner(System.in);
        char s;
        System.out.println("Citar (s/N)?");
        s=scanner.next().charAt(0);

        citation.add("Ao céu faltam todas as pessoas interessantes.");
        citation.add("Somente quem faz aprende.");
        citation.add("Muitos teimam em seguir o caminho que escolheram, poucos o objetivo.");
        citation.add("Quem luta com monstros deve acautelar-se para não se tornar um.");
        citation.add("Enquanto olhas para o abismo, o abismo olha para ti.");

        while( s=='s'|| s=='S'){
            index=rand.nextInt(citation.size());
            System.out.println(citation.get(index));
            System.out.println("Citar (s/N)?");
            s=scanner.next().charAt(0);
        }
        scanner.close();
    }
}
