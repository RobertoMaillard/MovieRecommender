/*
TrueFilter accepts all movies.
 */

public class TrueFilter implements IFilter {
    @Override
    public boolean satisfies(String id){
        return true;
    }
}