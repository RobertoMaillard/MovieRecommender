/*
MovieRecommender class contains methods to collect movies to rate. With or without filters.
It also contains methods to get Recommendations based on your ratings.
 */

import java.util.ArrayList;
import java.util.Random;

public class MovieRecommender {

    public static ArrayList<String> getItemsToRate(){
        ArrayList<String> selectedMovies = getItemsToRateByFilter(new TrueFilter());
        return selectedMovies;
    }

    public static ArrayList<String> getItemsToRateByFilter(IFilter filter){
        Ratings ratings = new Ratings();
        ArrayList<Rating> mostRatings = ratings.getAverageRatingsByFilter(10,filter);
        int moviesToRate = 10;
        int count = moviesToRate;
        Random random = new Random();
        ArrayList<String> selectedMovies = new ArrayList<String>();
        for (int i=0; i<count; i++) {
            int n = random.nextInt(mostRatings.size());
            Rating rating = mostRatings.get(n);
            String movie = rating.getItem();
            if (!selectedMovies.contains(movie)) {
                selectedMovies.add(movie);
            }
            else {
                count++;
            }
        }
        return selectedMovies;

    }
    public static ArrayList<Rating> getRecommendations(Rater me){
        ArrayList<Rating> similarRatings = getRecommendationsByFilter(me,new TrueFilter());
        return similarRatings;
    }

    public static ArrayList<Rating> getRecommendationsByFilter(Rater me,IFilter filter){
        Ratings ratings = new Ratings();
        ArrayList<Rating> similarRatings = ratings.getSimilarRatingsByFilter(me,50,5,filter);
        return similarRatings;
    }
}
