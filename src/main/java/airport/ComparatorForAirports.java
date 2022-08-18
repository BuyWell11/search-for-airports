package airport;
import java.util.Comparator;

public class ComparatorForAirports implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        try {
            double Value1 = Double.parseDouble(o1);
            double Value2 = Double.parseDouble(o2);
            double result = Value1 - Value2;
            return (int) result;
        } catch (NumberFormatException e) {
            return o1.compareTo(o2);
        }
    }
}
