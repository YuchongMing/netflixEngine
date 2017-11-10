
/**
 * Write a description of GenreFilte here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    String genre;
    public GenreFilter (String genre) {
        this.genre = genre;
    }
    @Override
    public boolean satisfies (String id) {
        String[] genres = MovieDatabase.getGenres(id).split(",");
        for (String s : genres) {
            if (!s.trim().equals(genre)) {
                continue;
            }
            return true;
        }
        return false;
    }
}
