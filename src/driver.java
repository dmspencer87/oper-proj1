import java.io.IOException;


/**
 * Created by Spencer on 10/12/2017.
 */
public class driver{
    public static void main(String[] args)throws IOException{
        Scheduler part1 = new Scheduler("input.txt");
        part1.FCFS();
        part1.SFJ();
    }

}
