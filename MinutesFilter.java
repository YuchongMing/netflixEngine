
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    int low;
    int high;
    public MinutesFilter (int low, int high) {
        this.low = low;
        this.high = high;
    }
    @Override
    public boolean satisfies (String id) {
        return (MovieDatabase.getMinutes(id) >= low && MovieDatabase.getMinutes(id) <= high);
    }
}
