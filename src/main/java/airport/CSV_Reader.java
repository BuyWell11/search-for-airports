package airport;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CSV_Reader {
    private final HashMap<String, ArrayList<String>> column = new HashMap<>();
    private final HashMap<String, ArrayList<String>> search_result = new HashMap<>();
    ComparatorForAirports comparatorForAirports = new ComparatorForAirports();

    public void load(String filename, Integer num_of_column) throws IOException, OutOfMemoryError {
        Integer max_num_of_columns = 14;
        if(num_of_column > max_num_of_columns || num_of_column < 1){
            System.out.println("out of range");
            return;
        }
        BufferedReader fp = new BufferedReader(new FileReader(filename));
        String string;
        LinkedList<String> linkedList = new LinkedList<>();
        while (fp.ready()){
            string = fp.readLine();
            linkedList.add(string);
        }
        for(String str : linkedList){
            String[] temp = str.split(",");
            String name = temp[num_of_column-1];
            ArrayList<String> other_info = new ArrayList<>();
            Collections.addAll(other_info, temp);
            column.put(name, other_info);
        }
        fp.close();
    }

    public void find(String search){
        long startTime = System.currentTimeMillis();
        for(String key : column.keySet()){
            int check = key.toLowerCase().indexOf(search.toLowerCase());
            int length = search.length();
            if(key.startsWith("\"")){
                if (key.toLowerCase().indexOf(search.toLowerCase()) == 1){
                    search_result.put(key, column.get(key));
                }
            }
            else if (key.toLowerCase().indexOf(search.toLowerCase()) == 0){
                search_result.put(key, column.get(key));
            }
        }
        long endTime = System.currentTimeMillis();
        long total_time = endTime-startTime;
        search_result.entrySet().stream().sorted(Map.Entry.comparingByKey(comparatorForAirports)).forEach(System.out::println);
        System.out.println("строк: " + search_result.size() + " время поиска: " + total_time + " мс");
        search_result.clear();
    }

}
