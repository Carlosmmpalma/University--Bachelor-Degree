package fermi;
import java.util.Scanner;

public class GameUI {
    static boolean continuePlaying(String response){
        char c0=response.charAt(0);
        return c0== 's' || c0== 'S';
    }
    static String prompt(String message){
        System.out.print(message);
        Scanner s = new Scanner(System.in);
        String response = s.nextLine();
        s.close();
        return response;
    }

    void play(){
        System.out.println("START");
        do{ 
           Progress progress=new Progress();
            do{
            String guess = prompt("Palpite: ");
            Guess guess=new Guess(guessText);
            String result=progress.evalGuess(guess);
            System.out.printf("%2d %s |> %s½n",
            progress.getGuessCount(),guessText,result);
            }while(!progress.secretFound());
            System.out.format("Parabéns, resolveu em %2d, palpites%n",progress.getGuessCount());
        }while (continuePlaying(prompt("Jogo Novo? "))); 
    }

    public static void main(String[] args){
       GameUI game = new GameUI();
       game.play(); 
    }
    
}
