import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings () {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings (String ratingsFile) {
        FirstRatings fr = new FirstRatings();

        this.myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getRaterSize () {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings (int minimalRater) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieID) {
            double average = getAverageByID(id, minimalRater);
            if (average == 0.0) {
                continue;
            }
            Rating rating = new Rating(id, average);
            result.add(rating);
        }
        Collections.sort(result);
        return result;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRater, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movieID = MovieDatabase.filterBy(filterCriteria);
        for (String id : movieID) {
            double average = getAverageByID(id, minimalRater);
            if (average == 0.0) {
                continue;
            }
            Rating rating = new Rating(id, average);
            result.add(rating);
        }
        Collections.sort(result);
        return result;
    }
    
    public double getAverageByID (String id, int minimalRater) {
        double sum = 0.0, count = 0;
        for (Rater rater : myRaters) {
            if (!rater.hasRating(id)) {
                continue;
            }
            sum += rater.getRating(id);
            count++;
        }
        if (count >= minimalRater) {
            return sum / (count * 1.0);
        }
        return 0.0;
    }
}
