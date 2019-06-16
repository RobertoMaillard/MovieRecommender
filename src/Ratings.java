/*
Add your filepath in the getSimilarities() method. File is located in the MovieRecommender directory.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Ratings {

    public ArrayList<Rating> getSimilarRatings(Rater me, int numSimilarRaters, int minimalRaters) {
        //Get similar ratings without filter.
        return getSimilarRatingsByFilter(me,numSimilarRaters,minimalRaters,new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(Rater me, int numSimilarRaters,
                                                       int minimalRaters, IFilter filterCriteria) {
        //Get similar ratings by filter.
        ArrayList<Rating> similarRatersList = getSimilarities(me);
        ArrayList<Rating> ratingList = new ArrayList<>();
        ArrayList <String> movieList = MovieDatabase.filterBy(filterCriteria);
        int num = numSimilarRaters;
        if (numSimilarRaters > similarRatersList.size()) {
            num = similarRatersList.size();
        }
        for (String movie : movieList) {
            int ratings = 0;
            double weightedSum = 0;
            for (int i=0; i<num; i++) {
                Rating r = similarRatersList.get(i);
                String raterID = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterID);
                if(simRater.hasRating(movie)) {
                    ratings++;
                    double ratingSim = simRater.getRating(movie);
                    double weight = r.getValue();
                    weightedSum += ratingSim*weight;
                }
            }
            if (ratings >= minimalRaters) {
                double weightedAverage = weightedSum / ratings;
                ratingList.add(new Rating(movie,weightedAverage));
            }
        }

        Collections.sort(ratingList,Collections.reverseOrder());
        return ratingList;
    }

    private ArrayList<Rating> getSimilarities(Rater me) {
        //Get a list of raters with the most similar rates as the rate id in descending order.
        String filepath = ""; // Add your filepath. File is located in the MovieRecommender directory.
        RaterDatabase.initialize(filepath + "/MovieRecommender/data/ratings.csv");
        ArrayList<Rater> ratersList = RaterDatabase.getRaters();

        ArrayList<Rating> similarRatersList = new ArrayList<>();

        for (Rater rater : ratersList) {
            if (rater != me) {
                double dotProduct = dotProduct(me,rater);
                if (dotProduct > 0) {
                    similarRatersList.add(new Rating(rater.getID(),dotProduct));
                }
            }
        }

        Collections.sort(similarRatersList,Collections.reverseOrder());
        return similarRatersList;
    }

    private double dotProduct(Rater me, Rater r) {
        //Get a similarity score between raters.
        ArrayList<String> myRatings = me.getItemsRated();
        ArrayList<String> raterRatings = r.getItemsRated();
        double dotProduct = 0;
        for (String s : myRatings) {
            if (raterRatings.contains(s)) {
                double myRating = me.getRating(s);
                double raterRating = r.getRating(s);
                double add = (myRating - 5) * (raterRating - 5);
                dotProduct += add;
            }
        }
        return dotProduct;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        //Get a list of the movies and their average ratings with at least a minimum amount of ratings.
        ArrayList<Rating> ratingList = getAverageRatingsByFilter(minimalRaters,new TrueFilter());
        return ratingList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, IFilter filterCriteria) {
        //Get the average rating for a movie with at least a minimum amount of ratings and a filter.
        ArrayList<String> moviesByFilterList = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratingList = new ArrayList<>();
        for (int i=0; i<moviesByFilterList.size(); i++) {
            double averageByID = getAverageByID(moviesByFilterList.get(i),minimalRaters);
            if (averageByID != 0.0) {
                Rating rating = new Rating(moviesByFilterList.get(i),averageByID);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }

    private double getAverageByID(String id, int minimalRaters) {
        // Get the average rating for a movie with at least a minimum amount of ratings.
        RaterDatabase.initialize("/Users/Roberto/Documents/Programming/code/Java-Projects/MovieRecommender/data/ratings.csv");
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        double ratingSum = 0;
        int ratings = 0;
        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                ratingSum += rater.getRating(id);
                ratings++;
            }
        }
        if (ratings >= minimalRaters) {
            return ratingSum/ratings;
        }
        return 0.0;
    }
}
