package sort;

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

    public static App getHighestRating(List<App> apps) {

        double idx = 0;
        App highest = new App();

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                if (Double.parseDouble(app.getRating()) > idx) {
                    highest = app;
                    idx = Double.parseDouble(app.getRating());
                }
            }
        }

        return highest;
    }

    public static App getLowestRating(List<App> apps) {

        double idx = 5;
        App lowest = new App();

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                if (Double.parseDouble(app.getRating()) < idx) {
                    lowest = app;
                    idx = Double.parseDouble(app.getRating());
                }
            }
        }

        return lowest;
    }

    public static double getAverageRating(List<App> apps) {

        double sum = 0;

        for (App app : apps) {
            if (isDouble(app.getRating())) {
                sum += Double.parseDouble(app.getRating());
                }
        }

        return sum/apps.size();
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
