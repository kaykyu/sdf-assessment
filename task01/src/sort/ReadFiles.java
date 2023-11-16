package sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadFiles {

    private static Map<String, List<App>> apps = new HashMap<>();

    public static Map<String, List<App>> getInfo(String fileName) {

        try (FileReader fr = new FileReader(fileName)) {

            BufferedReader br = new BufferedReader(fr);
            apps = br.lines()
                .skip(1)
                .map(lines -> lines.trim().split(","))
                .map(fields -> new App
                    (fields[0], fields[1].toUpperCase(), fields[2].toLowerCase()))
                .collect(Collectors.groupingBy(app -> app.getCategory()));

        } catch (IOException e) {
            System.out.println("Error, no file found");
        }
        
        return apps;
    }
    
}
