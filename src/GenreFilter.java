/*
GenreFilter accepts all movies that contains a specified genre.
 */

public class GenreFilter implements IFilter {

    private String genre;

    public GenreFilter(String genre){
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(this.genre);
    }
}
