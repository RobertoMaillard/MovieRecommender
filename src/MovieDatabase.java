/*
Add your filepath in the initialize() method. File is located in the MovieRecommender directory.
MovieDatabase extracts all the movies from a csv file and adds each moive as a Movie object in its Hashmap.
 */

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieDatabase {
    private static HashMap<String,Movie> ourMovies;

    public static void initialize(){
        if (ourMovies == null){
            ourMovies = new HashMap<>();
            String filepath = ""; // Add your filepath. File is located in the MovieRecommender directory.
            loadMovies(filepath + "MovieRecommender/data/ratedmoviesfull.csv");
        }
    }

    private static void loadMovies(String filepath) {
        CSVParser csvParser = new CSVParser(filepath);
        Iterable<CSVRecord> records = csvParser.getCSVParser();
        ArrayList<Movie> list = new ArrayList<>();
        for (CSVRecord record : records){
                String id = record.get("id");
                String title = record.get("title");
                String year = record.get("year");
                String country = record.get("country");
                String genres = record.get("genre");
                String director = record.get("director");
                String minutes = record.get("minutes");
                String poster = record.get("poster");

                Movie movie = new Movie(id,title,year,genres,director,country,poster,minutes);
                list.add(movie);
        }
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() {
        initialize();
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(IFilter f) {
        initialize();
        ArrayList<String> list = new ArrayList<>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }
}
