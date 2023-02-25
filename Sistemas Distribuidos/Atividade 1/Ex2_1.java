import java.util.LinkedList;

public class Ex2_1 {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<String>();
        for (String n: args) {
            sort(n,names);
        }
        System.out.println(names);
    }

    public static void sort(String name,LinkedList<String> names) {

        int i=0;
        //procurar posição certa para inserir
        for(;i<names.size() && names.get(i).compareTo(name)<0;i++){

        }
        names.add(i,name);
    }
}

