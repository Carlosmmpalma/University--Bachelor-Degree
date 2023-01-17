package fermi;

public class Progress {
    int guessCount=0;
    Secret secret;
    
    public String evalGuess(Guess guess){
        guessCount++;
        return "ABC";
    }

    public int getGuessCount(){
        return guessCount;
    }

    public boolean secretFound(){
        return guessCount ==2;
    }

}
