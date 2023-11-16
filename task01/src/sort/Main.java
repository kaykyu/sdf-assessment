package sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<String, List<App>> map = new HashMap<>();

    public static void main(String[] args) {
        
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        } else {
            System.out.println("Error, please input file");
            System.exit(0);
        }

        map = ReadFiles.getInfo(fileName);

        for (String str : map.keySet()) {
            List<App> apps = map.get(str);
            App highest = Evaluate.getHighestRating(apps);
            App lowest = Evaluate.getLowestRating(apps);
            double average = Evaluate.getAverageRating(apps);
            int count = apps.size();
            int discarded = Evaluate.getDiscarded(apps);
            System.out.printf("""
                Category: %s
                    Highest: %s, %s
                    Lowest: %s, %s
                    Average: %.2f
                    Count: %d
                    Discarded: %d\n
                """, str.toUpperCase(), 
                highest.getName(), highest.getRating(), 
                lowest.getName(), lowest.getRating(),
                average, count, discarded); 
        }

    }
}