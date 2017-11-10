import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (Yuchong Ming) 
 * @version (Jun 16, 2017)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName) {
        FileResource movieFile = new FileResource(fileName);
        ArrayList<Movie> result = new ArrayList<Movie>();
        if (movieFile.lines() == null) {
            return result;
        }      
        CSVParser cp = movieFile.getCSVParser();
        Iterator<CSVRecord> iter = cp.iterator();
            while (iter.hasNext()) {
            CSVRecord cr = iter.next();
            
            String id = cr.get("id");
            String title = cr.get("title");
            String year = cr.get("year");
            String genres = cr.get("genre");
            String director = cr.get("director");
            String country = cr.get("country");
            String poster = cr.get("poster");
            int minutes = Integer.parseInt(cr.get("minutes"));
            
            result.add(new Movie(id, title, year, genres, director, country, poster, minutes));
        }
        return result;
    }
    
    public ArrayList<Rater> loadRaters(String fileName) {
        FileResource raterFile = new FileResource(fileName);
        ArrayList<Rater> results = new ArrayList<Rater>();
        Map<Integer, Rater> map = new HashMap<Integer, Rater>();
        if (raterFile.lines() == null) {
            return results;
        }
        CSVParser cp = raterFile.getCSVParser();
        Iterator<CSVRecord> iter = cp.iterator();
        while (iter.hasNext()) {
            CSVRecord cr = iter.next();
            int raterId = Integer.parseInt(cr.get(0));
            if (!map.containsKey(raterId)) {
                Rater rater = new EfficientRater(cr.get(0));
                map.put(raterId, rater);
            }
            map.get(raterId).addRating(cr.get(1), (double)Integer.parseInt(cr.get(2)));
        }
        Iterator mapIter = map.entrySet().iterator();
        while (mapIter.hasNext()) {
            Map.Entry entry = (Map.Entry)mapIter.next();
            results.add((Rater)entry.getValue());
        }
        return results;
    }
}
