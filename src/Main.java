/*
** Please start by specifying the correct filepath in the MovieDatabase class and in the Ratings class **

This class starts the Movie Recommender. Its a program that recommends you up to 20 movies
based on the ratings you supply on 10 movies random movies that got more then 10 ratings from 10 different raters.
The movies and ratings are obtain by loading two CSV files. One file with 3144 movies and all their information.
The other file with 1043 raters and their total of 10001 ratings between 1 and 10 of the 3144 movies.
Your ratings are compared to other raters and the 50 raters with most similar ratings are picked.
Their top rated movies are put in to a list and the top 20 movies with highest ratings are show a recommended movies.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Obtain a list of 10 random movies to rate. Each movie has at least 10 ratings by other raters.
        ArrayList<String> moviesToRateList = MovieRecommender.getItemsToRate();

//        // Or obtain a list of 10 random movies to rate by filter. Use any of the provided filters.
//        // Each movie has at least 10 ratings by other raters.
//        ArrayList<String> moviesToRateList = MovieRecommender.getItemsToRateByFilter(new TrueFilter());

        // Obtain user movie ratings. Prints the movies and obtains the rate.
        Rater rater = new Rater("1");
        System.out.println("MOVIE RECOMMENDER\nRate each movie with a number between 1 and 10\nRate with a 0 if you have not seen it");
        Scanner scanner = new Scanner(System.in);
        for (String movie : moviesToRateList) {
            System.out.println(MovieDatabase.getTitle(movie));
            int rateScore = scanner.nextInt();
            rater.addRating(movie,rateScore);
        }
        scanner.close();

        // Obtain a list of recommended movies based on those raters that have the most similar ratings as you.
        ArrayList<Rating> movieRecommenderList = MovieRecommender.getRecommendations(rater);

//        // Or obtain a list of recommended movies by filter based on those raters that have the most similar ratings as you.
//        //  Use any of the provided filters.
//        ArrayList<Rating> movieRecommenderList = MovieRecommender.getRecommendationsByFilter(rater,new TrueFilter());

        if (movieRecommenderList.size() > 0){
            int count = 20;
            if (movieRecommenderList.size() < count) {
                count = movieRecommenderList.size();
            }
            System.out.println("We have " + count + " movies to recommend");
            for (int i=0; i<count; i++){
                Rating rating = movieRecommenderList.get(i);
                System.out.println(MovieDatabase.getTitle(rating.getItem()));
            }
        }
    }
}
