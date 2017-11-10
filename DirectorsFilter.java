import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    Set<String> director;
    public DirectorsFilter (String input) {
        director = new HashSet<String>();
        String[] directors = input.split(",");
        for (String s : directors) {
            director.add(s);
        }
    }
    @Override
    public boolean satisfies (String id) {
        String[] directors = MovieDatabase.getDirector(id).split(",");
        for (String s : directors) {
            if (!director.contains(s.trim())) {
                continue;
            }
            return true;
        }
        return false;
    }
}
