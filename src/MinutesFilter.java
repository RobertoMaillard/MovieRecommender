/*
MinutesFilter accepts all movies that are between a specified minimum and maximum of minutes.
 */

public class MinutesFilter implements IFilter {

    private int min;
    private int max;

    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id) >= min) && (MovieDatabase.getMinutes(id) <= max);
    }
}
