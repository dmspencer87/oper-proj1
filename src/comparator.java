import java.util.Comparator;

/**
 * Created by Spencer on 10/13/2017.
 */
public class comparator implements Comparator<process> {
    public int compare(process x, process y){
        if (x.getPriority()>y.getPriority()){
            return -1;
        }
        else if(x.getPriority()<y.getPriority()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
