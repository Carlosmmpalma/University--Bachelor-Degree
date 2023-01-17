import java.util.List;
import java.util.stream.Collectors;

public class QSort {
    public static List<Integer> quicksort(List<Integer> xs){
        if(xs.size()<=1) return xs;

        var x0=xs.get(0);
        var menores=xs.stream().filter(xi->xi<x0).collect(Collectors.toList());
        var maiores=xs.stream().filter(xi->xi>x0).collect(Collectors.toList());
        var iguais=xs.stream().filter(xi->xi==x0).collect(Collectors.toList());

        var a=quicksort(menores);
        var b=quicksort(maiores);

        a.addAll(iguais);
        a.addAll(b);

        return a;
    }   

    public static void main(String[] args){
        List<Integer> a=List.of(55,3,3);
        System.out.println(quicksort(a));
    }
}
