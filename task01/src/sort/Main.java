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

            List<App> highs = Evaluate.getHighestRating(apps);
            String highest = "";
            for (App high : highs) {
                highest += String.format("\t%s\n", high.getName());
            }

            List<App> lows = Evaluate.getLowestRating(apps);
            String lowest = "";
            for (App low : lows) {
                lowest += String.format("\t%s\n", low.getName());
            }

            int count = apps.size();
            int discarded = Evaluate.getDiscarded(apps);
            double average = Evaluate.getTotalRating(apps)/(count - discarded);

            System.out.printf("""
                Category: %s
                    Count: %d
                    Discarded: %d
                    Average rating: %.2f

                    Highest rating: %s
                %s
                    Lowest rating: %s
                %s\n
                """, str, count, discarded, average, 
                highs.get(0).getRating(), highest,
                lows.get(0).getRating(), lowest
                ); 
        }

    }
}