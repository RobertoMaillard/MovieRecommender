# MovieRecommender
Rate 10 random movies and get a list of 20 recommeded movies based on other raters with similar ratings.
# Provided
The file directory includes two csv files. This are parsed in the program using Apache Commons CSV parser.
ratedmoviesfull.csv contains 3144 movies with informations such as MovieID, Title, Year, Country, Genre, Director, Minutes and Poster link.
ratings.csv contains 10001 movie ratings rated by 1043 raters with informations such as RaterID, MovieID, Rating and Time.
# Instructions
Please start by specifying the correct filepath to the csv files in the MovieDatabase class and in the Ratings class.
Rate 10 random movies from a list of movie with at least 10 ratings.
Rating is done by giving each movie a rate between 1-10 or 0 if you have not seen the movie.
Movie Recommender will after recommend a maximum of 20 movies based on raters with most similar ratings.  
# Behaviour
The program reads in all the movies from the ratedmoviesfull.csv file and stores each of them as a Movie object with its information in a MovieDatabase object.
Then it reads in all the ratings from the ratings.csv a creates a RaterDatabase object that contains a Rater object for every rater.
Every Rater object contains all the ratings the rater has done.
This produces the information on which the movie recommendations are based on. 
# School project
Contains the use of

* Apache Commons CSV librarie
* Interface
* Implements
* Scanner
* Random
* HashMap
* File
* Array
* IOException
* for loops
* for each loops
* and more..
