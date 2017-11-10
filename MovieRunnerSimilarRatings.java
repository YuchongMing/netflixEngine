import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public static void printSimilarRatings () { 
        String ratingsFile = new String("ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        FourthRatings fr = new FourthRatings();
        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
    }
    
    public static void printSimilarRatingsByGenre () { 
        String ratingsFile = new String("ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        FourthRatings fr = new FourthRatings();
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = new String("Mystery");
        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, 
                                                                 numSimilarRaters, 
                                                                 minimalRaters, 
                                                                 genreFilter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
    }
    
    public static void printSimilarRatingsByDirector () { 
        String ratingsFile = new String("ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        FourthRatings fr = new FourthRatings();
        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String director = new String("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        Filter directorFilter = new DirectorsFilter(director);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, 
                                                                 numSimilarRaters, 
                                                                 minimalRaters, 
                                                                 directorFilter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
    }
    
    public static void printAverageRatingsByGenreAndMinutes () { 
        String ratingsFile = new String("ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        FourthRatings fr = new FourthRatings();
        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        int low = 80, high = 160;
        String genre = new String("Drama");
        Filter minFilter = new MinutesFilter(low, high);
        Filter genreFilter = new GenreFilter(genre);
        Filter all = new AllFilters();
        ((AllFilters)all).getList().add(minFilter);
        ((AllFilters)all).getList().add(genreFilter);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, 
                                                                 numSimilarRaters, 
                                                                 minimalRaters, 
                                                                 all);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genres: " + MovieDatabase.getGenres(rating.getItem()));
            System.out.println("Movie Run Time: " + MovieDatabase.getMinutes(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
    }
    
    public static void printAverageRatingsByYearAfterAndMinutes () { 
        String ratingsFile = new String("ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        RaterDatabase.initialize(ratingsFile);
        FourthRatings fr = new FourthRatings();
        String raterID = new String("314");
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int low = 70, high = 200;
        int year = 1975;
        Filter minFilter = new MinutesFilter(low, high);
        Filter yearFilter = new YearAfterFilter(year);
        Filter all = new AllFilters();
        ((AllFilters)all).getList().add(yearFilter);
        ((AllFilters)all).getList().add(minFilter);
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, 
                                                                 numSimilarRaters, 
                                                                 minimalRaters, 
                                                                 all);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Year: " + MovieDatabase.getYear(rating.getItem()));
            System.out.println("Movie Run Time: " + MovieDatabase.getMinutes(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
    }
}
