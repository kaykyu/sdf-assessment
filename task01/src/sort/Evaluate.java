package sort;

import java.util.ArrayList;
import java.util.List;

public class Evaluate {

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<App> getHighestRating(List<App> apps) {

        double idx = Double.MIN_VALUE;
        App highest = new App();

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                if (Double.parseDouble(app.getRating()) > idx) {
                    highest = app;
                    idx = Double.parseDouble(app.getRating());
                }
            }
        }

        List<App> highs = new ArrayList<>();
        for (App app : apps) {
            if (app.getRating().equals(highest.getRating())) {
                highs.add(app);
            }
        }

        return highs;
    }

    public static List<App> getLowestRating(List<App> apps) {

        double idx = Double.MAX_VALUE;
        App lowest = new App();

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                if (Double.parseDouble(app.getRating()) < idx) {
                    lowest = app;
                    idx = Double.parseDouble(app.getRating());
                }
            }
        }

        List<App> lows = new ArrayList<>();
        for (App app : apps) {
            if (app.getRating().equals(lowest.getRating())) {
                lows.add(app);
            }
        }

        return lows;
    }

    public static double getTotalRating(List<App> apps) {

        double sum = 0;

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                sum += Double.parseDouble(app.getRating());
                }
        }

        return sum;
    }

    public static int getDiscarded(List<App> apps) {

        int count = 0;

        for (App app : apps) {
            if(!isDouble(app.getRating())) {
                count++;
            }
        }

        return count;
    }
    
}
