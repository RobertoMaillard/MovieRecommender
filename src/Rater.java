/*
Rater class is used to contain the rater with a rater ID, and all their movie ratings.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Rater {
    private String myID;
    private HashMap<String,Rating> ratings;

    public Rater(String id) {
        this.myID = id;
        this.ratings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        this.ratings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (this.ratings.containsKey(item)) {
            return true;
        }
        return false;
    }

    public String getID() {
        return this.myID;
    }

    public double getRating(String item) {
        if (hasRating(item)) {
            return this.ratings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return this.ratings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : this.ratings.keySet()) {
            list.add(s);
        }
        return list;
    }
}
