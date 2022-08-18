package airport;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int max_num_of_columns = 14;
        Integer num_of_column = null;
        try{
            num_of_column = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.err.println("Wrong format of input for number of column");
            System.exit(1);
        }
        if(num_of_column > max_num_of_columns || num_of_column < 1){
            System.out.println("Number of column out of range");
            return;
        }
        CSV_Reader csv = new CSV_Reader("airports.csv", num_of_column);
        csv.load();
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Input something for search");
            String search = in.nextLine();
            if (search.equals("!quit")){
                return;
            }
            csv.find(search);
        }

    }
}