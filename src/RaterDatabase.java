/*
RaterDatabase extracts all the ratings from a csv file and adds each one as a Rating object in
a the specific Rater objects ratings. This are identified with a Rater ID.
 */

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;

    private static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap<>();
        }
    }

    public static void initialize(String filepath) {
        if (ourRaters == null) {
            ourRaters= new HashMap<>();
            addRatings(filepath);
        }
    }

    public static void addRatings(String filepath) {
        initialize();
        CSVParser csvParser = new CSVParser(filepath);
        Iterable<CSVRecord> records = csvParser.getCSVParser();
        ArrayList<Rater> list = new ArrayList<>();
        for (CSVRecord record : records){
            String id = record.get("rater_id");
            String item = record.get("movie_id");
            String rating = record.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new Rater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static Rater getRater(String id) {
        initialize();
        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<>(ourRaters.values());

        return list;
    }

    public static int size() {
        return ourRaters.size();
    }
}
