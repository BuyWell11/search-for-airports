package airport;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CSV_Reader csv = new CSV_Reader();
        Integer num_of_column = null;
        try{
            num_of_column = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.err.println("Wrong format of input for number of column");
            System.exit(1);
        }
        csv.load("airports.csv", num_of_column);
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("input something for search");
            String search = in.nextLine();
            if (search.equals("!quit")){
                return;
            }
            csv.find(search);
        }

    }
}