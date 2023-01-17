import java.util.List;
import java.util.Vector;

public class Try {
    static List<String> explore(String candidate, List<String> words) {
        List<String> result = new Vector<>();
        for (var word : words) {
        if (candidate.startsWith(word)) {
        var suffix = candidate.substring(word.length()); // candidate = word + suffix
        var children = explore(suffix, words);
        for (var child : children) {
        var solution = String.format("%s %s", word, child);
        result.add(solution);
        }
        }
        }
        return result;
        }

        public static void main(String[] args){
            var words = List.of(
"hoje", "vamos", "ao", "cinema",
"ou", "passear", "no", "jardim",
"va", "mosao", "he");

            System.out.println(explore("hojenaocomi",words));
        }
}
