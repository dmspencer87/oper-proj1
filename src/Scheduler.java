import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Spencer on 10/12/2017.
 */
public class Scheduler  {
    private ArrayList<process> array = new ArrayList();
    private int completionTime = 0;
    private int waiting = 0;


    Scheduler(String f){
        try {
            Scanner inFile = new Scanner(new File(f));
            while(inFile.hasNext()){
                process proc = new process(inFile.nextInt(), inFile.nextInt(), inFile.nextInt());
                array.add(proc);
            }
        }
        catch(IOException exc){
            System.out.print(exc);
        }
    }

    void FCFS(){
        Queue<process> q = new LinkedList<>();
        for(int i=0;i < array.size();++i){
            q.add(array.get(i));
        }
        System.out.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");
        for(int i=0;i < array.size();++i){
            process p = q.remove();
            this.completionTime += p.getBurst();
            System.out.printf("%-5s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime);
            this.waiting += (p.getBurst() + 3);
            this.completionTime += 3;
        }
        System.out.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
    }
    void SFJ() {
        Comparator<process> comp = new comparator();
        PriorityQueue<process> q = new PriorityQueue(array.size(),comp);
        for(int i=0; i < array.size();++i){
            q.add(array.get(i));
        }
        this.completionTime = 0;
        this.waiting = 0;
        System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime", "TurnAround", "Priority");
        for (int i = 0; i < array.size(); ++i) {
            process p = q.remove();
            this.completionTime += p.getBurst();
            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            this.waiting += (p.getBurst() + 3);
            this.completionTime += 3;
        }
        System.out.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
    }

    void RR(int time_quant){
        ArrayList<process> rrArray;
        rrArray = (ArrayList<process>)array.clone();
        System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime", "TurnAround", "Priority");
        int index = 0;
        this.waiting = 0;
        this.completionTime = 50;
        int avgComp = 0;
        while(!rrArray.isEmpty()){
            process p = rrArray.get(index);
            //int burst = p.getBurst();
            //this.completionTime += time_quant;
            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            p.timeReturn(time_quant);
            int t = p.getBurst();
            if(t <= 0){
                t = time_quant + t;
                //System.out.println(t);
                completionTime += t;
                avgComp += (t+completionTime);
                rrArray.remove(index);
                //System.out.println(avgComp);
            }
            else{
                completionTime += time_quant;
                rrArray.set(index,p);
            }


            this.waiting += (time_quant + 3);


            if(index == rrArray.size()){
                index = 0;
            }
            this.completionTime += 3;
//            if(rrArray.isEmpty()==true){
//                return;
//            }
            ++index;
            if(index >= rrArray.size()){
                index = 0;
            }

        }

        System.out.println("\nAverage Turnaround Time: "+ (float)(avgComp/ array.size()));
    }


}
