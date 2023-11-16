package server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Budget {

    private static double budget;
    private static List<Item> items = new ArrayList<>();

    public static double getBudget() {
        return budget;
    }

    public static void setBudget(double budget) {
        Budget.budget = budget;
    }

    public static List<Item> useBudget() {
        items = Main.getItems();
        Comparator<Item> comparator = Comparator.comparing(item -> item.getRating());
        comparator = comparator.thenComparing(Comparator.comparing(item -> item.getPrice()));
        comparator = comparator.reversed();

        Stream<Item> itemStream = items.stream().sorted(comparator);
        items = itemStream.collect(Collectors.toList());
        List<Item> itemsBought = new ArrayList<>();
        
        for (Item item : items) {
            if (item.getPrice() > budget) {
                continue;
            } else {
                itemsBought.add(item);
                budget -= item.getPrice();

            }
        }

        return itemsBought;
    }
}
