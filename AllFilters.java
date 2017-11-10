import java.util.ArrayList;

public class AllFilters implements Filter {
    private ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }
    
    public ArrayList<Filter> getList() {
        return filters;
    }
    
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }

}
