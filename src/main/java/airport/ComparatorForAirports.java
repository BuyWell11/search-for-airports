package airport;
import java.util.Comparator;

public class ComparatorForAirports implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        try {
            double Value1 = Double.parseDouble(o1);
            double Value2 = Double.parseDouble(o2);
            return Double.compare(Value1, Value2);
        } catch (NumberFormatException e) {
            return o1.compareTo(o2);
        }
    }
}
