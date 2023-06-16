import java.util.ArrayList;
import java.util.List;

public class GreibachNormalForm {
    public static void main(String[] args) {

        List<String> productions = new ArrayList<>();
        productions.add("S->AB|CSB");
        productions.add("A->aB|C");
        productions.add("B->bbB|b");

        List<String> greibachProductions = transformToGreibachNormalForm(productions);

        System.out.println("Produções na Forma Normal de Greibach:");
        for (String production : greibachProductions) {
            System.out.println(production);
        }
    }

    public static List<String> transformToGreibachNormalForm(List<String> productions) {
        List<String> greibachProductions = new ArrayList<>();

        for (String production : productions) {
            if (production.contains("|")) {
                String[] parts = production.split("->");
                String nonTerminal = parts[0];
                String[] symbols = parts[1].split("\\|");

                for (String symbol : symbols) {
                    if (symbol.length() > 1 && !symbol.contains("|")) {
                        String newNonTerminal = generateNewNonTerminal();
                        String newProduction = nonTerminal + "->" + symbol.charAt(0) + newNonTerminal;
                        greibachProductions.add(newProduction);
                        production = newNonTerminal + "->" + symbol.substring(1);
                    }
                    greibachProductions.add(production);
                }
            } else {
                greibachProductions.add(production);
            }
        }
        return greibachProductions;
    }

    public static String generateNewNonTerminal() {
        return "X" + System.currentTimeMillis();
    }

}
