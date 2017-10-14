/**
 * Created by Spencer on 10/12/2017.
 */
public class process {
    private int pid;
    private int burset_time;
    private int priority;


    process(int p, int b, int pr){
        pid =p;
        burset_time=b;
        priority=pr;
    }
    public void timeReturn(int timeQuanta){
        burset_time -= timeQuanta;
    }
    public int getPid(){return this.pid;}
    public int getBurst(){return  this.burset_time;}
    public int getPriority(){return this.priority;}


}
