import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    
    private double dotProduct (Rater me, Rater r) {
        double result = 0.0;
        ArrayList<String> myRatings = me.getItemsRated();
        for (String s : myRatings) {
            if (!r.hasRating(s)) {
                continue;
            }
            result += (me.getRating(s) - 5.0) * (r.getRating(s) - 5.0);
        }
        return result;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rater> list = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : list) {
            if (r == me) {
                continue;
            }
            double dp = dotProduct(me, r);
            if (dp <= 0) {
                continue;
            }
            result.add(new Rating(r.getID(), dp));
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatings (String raterId, 
                                                int numSimilarRaters, 
                                                int minimalRaters) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieID) {
            double avg = getAvgBySimilar (id, similarRaters, numSimilarRaters, minimalRaters);
            if (avg == 0.0) {
                continue;
            }
            results.add(new Rating(id, avg));
        }
        Collections.sort(results);
        return results;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (String raterId, 
                                                        int numSimilarRaters, 
                                                        int minimalRaters, 
                                                        Filter filterCriteria) {
        ArrayList<Rating> results = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<String> movieID = MovieDatabase.filterBy(filterCriteria);
        for (String id : movieID) {
            double avg = getAvgBySimilar(id, similarRaters, numSimilarRaters, minimalRaters);
            if (avg == 0.0) {
                continue;
            }
            results.add(new Rating(id, avg));
        }
        Collections.sort(results);
        return results;
    }
    
    public double getAvgBySimilar (String id,
                                   ArrayList<Rating> similarRaters,
                                   int numSimilarRaters,
                                   int minimalRaters) {
        int count = 0;
        double sum = 0.0, weight = 0.0;
        for (int i = 0; i < numSimilarRaters && i < similarRaters.size(); i++) {
            Rater r = RaterDatabase.getRater(similarRaters.get(i).getItem());
            if (!r.hasRating(id)) {
                continue;
            }
            double pd = similarRaters.get(i).getValue();
            sum += r.getRating(id) * pd;
            weight += pd;
            count++;
        }
        if (count >= minimalRaters) {
            return sum / count;
        }
        return 0.0;
    }
}
