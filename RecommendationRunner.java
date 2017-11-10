import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> result = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            result.add(new String(r.nextInt() % 3143 + ""));
        }
        return result;
    }

    @Override
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fr = new FourthRatings();
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        if (ratings.size() == 0) {
            System.out.println("We have no suitable movie recommend to you");
        }
        for (int i = 0; i < 15 && i < ratings.size(); i++) {
            Rating tempRating = ratings.get(i);
            System.out.println(tempRating.getValue() + " " + MovieDatabase.getTitle(tempRating.getItem()));
        }
    }
}
