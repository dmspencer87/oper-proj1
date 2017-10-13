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
    void display(){
        System.out.println(this.pid);
        System.out.println(this.burset_time);
        System.out.println(this.priority);
    }
    int getPid(){return this.pid;}
    int getBurst(){return  this.burset_time;}
    int getPriority(){return this.priority;}
}
