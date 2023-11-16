package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static String SERVER;
    private static int PORT;
    private static String requestID;
    private static double budget;
    private static List<Item> items = new ArrayList<>();

    public static List<Item> getItems() {
        return items;
    }

    public static void main(String[] args) throws IOException {
        
        if (args.length == 0) {
            SERVER = "localhost";
            PORT = 3000;
        } else if (args.length == 1) {
            SERVER = "localhost";
            PORT = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            SERVER = args[0];
            PORT = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid command, please try again");
            System.exit(0);
        }

        System.out.printf("Attempting to connect to %s, port %d\n", SERVER, PORT);

        Socket socket = new Socket(SERVER, PORT);
        System.out.printf("Connected to %s, port %d\n", SERVER, PORT);
        
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line = "";
        int count = 1;

        while (count > 0) {
            line = br.readLine();
            if (line == null) {
                continue;

            } else if (line.trim().startsWith("request_id")) {
                String[] words = line.split(":");
                requestID = words[1].trim();

            } else if (line.trim().startsWith("item_count")) {
                String[] words = line.split(":");
                count = Integer.parseInt(words[1].trim());           
            
            } else if (line.trim().startsWith("budget")) {
                String[] words = line.split(":");
                budget = Double.parseDouble(words[1].trim());
                Budget.setBudget(budget);

            } else if (line.trim().equals("prod_start")) {
                int id = 0;
                String title = "";
                double price = 0;
                double rating = 0;

                while (!line.trim().startsWith("prod_end")) {
                    line = br.readLine();
                    String[] words = line.split(":");

                    if (words[0].trim().equals("prod_id")) {
                        id = Integer.parseInt(words[1].trim());

                    } else if (words[0].trim().equals("title")) {
                        title = words[1].trim();

                    } else if (words[0].trim().equals("price")) {
                        price = Double.parseDouble(words[1].trim());

                    } else if (words[0].trim().equals("rating")) {
                        rating = Double.parseDouble(words[1].trim());

                    } 
                }
                
                count--;
                Item item = new Item(id, title, price, rating);
                items.add(item); 
            }

        }

        items = Budget.useBudget();
        String str = String.valueOf(items.get(0).getId());
        for (int i = 1; i < items.size(); i++) {
            str += "," + String.valueOf(items.get(i).getId());
        }

        String send1 = String.format("request_id: %s\n", requestID);
        String send2 = "name: Ng Kai Qing\n";
        String send3 = "email: kaiqing.97@outlook.com\n";
        String send4 = String.format("items: %s\n", str);
        String send5 = String.format("spent: %.2f\n", budget - Budget.getBudget());
        String send6 = String.format("remaining: %.2f\n", Budget.getBudget());

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(send1);
        bw.flush();

        bw.write(send2);
        bw.flush();

        bw.write(send3);
        bw.flush();

        bw.write(send4);
        bw.flush();

        bw.write(send5);
        bw.flush();

        bw.write(send6);
        bw.flush();

        bw.write("client_end\n");
        bw.flush();

        line = br.readLine();
        System.out.println(line);
              
        if (line != null) {
            bw.close();
            br.close();
            socket.close();
        }
    }
    
}
