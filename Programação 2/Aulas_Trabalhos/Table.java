public class Table{
    public static void tabulate(int start, int end, int lineLength){
        int j=start;
        for(int i=start;i<=end;i++){
            if(i==j+lineLength){
                System.out.println();
                j=j+lineLength;
            }
            if(i<=9){
                System.out.print(" "+i+" ");
            }else{
                System.out.print(i+" ");
            }
        }
    }
    public static void main(String[] args){
        tabulate(1,49,10);
    }
}
