/*
YearAfterFilter accepts all movies that are made after a specified year.
 */

public class YearAfterFilter implements IFilter {

    private int myYear;

    public YearAfterFilter(int year) {
        myYear = year;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }

}
