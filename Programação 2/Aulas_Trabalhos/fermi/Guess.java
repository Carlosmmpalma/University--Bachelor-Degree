package fermi;

public class Guess {
    int a,b,c;
    public Guess(String abc){//a,b,c="1 2 3"
        String[] d = abc.split(" ");
        a=Integer.parseInt(d[0]);
        b=Integer.parseInt(d[1]);
        c=Integer.parseInt(d[2]);
    }

    public int getA(){return a;}

    public int getB(){return b;}

    public int getC(){return c;}
}
