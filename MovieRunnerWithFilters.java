import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public static void printAverageRatings () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 35;
        ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByYears () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 20;
        int year = 2000;
        Filter filter = new YearAfterFilter(year);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByGenre () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 20;
        String genre = new String("Comedy");
        Filter filter = new GenreFilter(genre);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByMinutes () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 5;
        int low = 105, high = 135;
        Filter filter = new MinutesFilter(low, high);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Movie Run Time: " + MovieDatabase.getMinutes(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByDirector () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 4;
        String director = new String("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        Filter filter = new DirectorsFilter(director);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Director: " + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByYearAfterAndGenre () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 8;
        int year = 1990;
        String genre = new String("Drama");
        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        Filter all = new AllFilters();
        ((AllFilters)all).getList().add(yearFilter);
        ((AllFilters)all).getList().add(genreFilter);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, all);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genres: " + MovieDatabase.getGenres(rating.getItem()));
            System.out.println("Year: " + MovieDatabase.getYear(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
    
    public static void printAverageRatingsByDirectorsAndMinutes () { 
        String ratingsFile = new String("data/ratings.csv");
        String movieFile = new String("ratedmoviesfull.csv");
        MovieDatabase.initialize(movieFile);
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        int minimalRaters = 3;
        int low = 90, high = 180;
        String director = new String("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter minuteFilter = new MinutesFilter(low, high);
        Filter directorFilter = new DirectorsFilter(director);
        Filter all = new AllFilters();
        ((AllFilters)all).getList().add(minuteFilter);
        ((AllFilters)all).getList().add(directorFilter);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, all);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Movie Run Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Director: " + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("found " + ratings.size() + " movies");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
    }
}
