package airport;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CSV_Reader {
    private final HashMap<String, ArrayList<String>> column = new HashMap<>();
    private final HashMap<ArrayList<String>, String> search_result = new HashMap<>();
    ComparatorForAirports comparatorForAirports = new ComparatorForAirports();
    private final String filename;
    private final Integer num_of_column;
    private boolean delete = false;

    public CSV_Reader(String filename, Integer num_of_column){
        this.filename = filename;
        this.num_of_column = num_of_column - 1;
    }

    public void load() throws IOException, OutOfMemoryError {
        BufferedReader fp = new BufferedReader(new FileReader(filename));
        String string;
        LinkedList<String> linkedList = new LinkedList<>();
        while (fp.ready()){
            string = fp.readLine();
            linkedList.add(string);
        }
        for(String str : linkedList){
            String[] temp = str.split(",");
            String name = temp[0];
            ArrayList<String> other_info = new ArrayList<>();
            Collections.addAll(other_info, temp);
            column.put(name, other_info);
        }
        fp.close();
    }

    public void find(String search) throws IOException {
        if(column.isEmpty()){
            load();
        }
        long startTime = System.currentTimeMillis();
        for(ArrayList<String> arr_of_str : column.values()){
            String str = arr_of_str.get(num_of_column);
            if(str.startsWith("\"")){
                if (str.toLowerCase().indexOf(search.toLowerCase()) == 1){
                    search_result.put(arr_of_str, str);
                }
            }
            else if (str.toLowerCase().indexOf(search.toLowerCase()) == 0){
                search_result.put(arr_of_str, str);
            }
        }
        long endTime = System.currentTimeMillis();
        long total_time = endTime-startTime;
        search_result.entrySet().stream().sorted(Map.Entry.comparingByValue(comparatorForAirports)).forEach((entry) -> {
            System.out.println(entry.getValue() + " " + entry.getKey());});
        System.out.println("Строк: " + search_result.size() + " Время поиска: " + total_time + " мс");
        search_result.clear();
        if(delete){
            column.clear();
        }
        switcher();
    }

    private void switcher(){
        this.delete = !this.delete;
    }

}
