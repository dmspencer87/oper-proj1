import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Spencer on 10/12/2017.
 */
public class Scheduler  {
    private ArrayList<process> array = new ArrayList();
    private int completionTime = 0;
    private int waiting = 0;



    public Scheduler(String f){
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

    public void FCFS()throws IOException{
        Queue<process> q = new LinkedList<>();
        //PrintWriter writer = new PrintWriter(new FileWriter("FCFS-1.txt"));
        for(int i=0;i < array.size();++i){
            q.add(array.get(i));
        }
        System.out.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");
        //writer.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");
        for(int i=0;i < array.size();++i){
            process p = q.remove();
            this.completionTime += p.getBurst();
            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            //writer.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            this.waiting += (p.getBurst() + 3);
            this.completionTime += 3;
        }
        System.out.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
        //writer.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
    }
    public void SFJ() throws IOException{
        //PrintWriter writer = new PrintWriter(new FileWriter("SJF-1.txt"));
        Comparator<process> comp = new comparator();
        PriorityQueue<process> q = new PriorityQueue(array.size(),comp);
        for(int i=0; i < array.size();++i){
            q.add(array.get(i));
        }
        this.completionTime = 0;
        this.waiting = 0;
        System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime", "TurnAround", "Priority");
        //writer.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");
        for (int i = 0; i < array.size(); ++i) {
            process p = q.remove();
            this.completionTime += p.getBurst();
            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            //writer.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            this.waiting += (p.getBurst() + 3);
            this.completionTime += 3;
        }
        System.out.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
        //writer.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
    }

    public void RR(int time_quant)throws IOException{
        //PrintWriter writer = new PrintWriter(new FileWriter("RR-1.txt"));
        ArrayList<process> rrArray;
        rrArray = (ArrayList<process>)array.clone();
        System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime", "TurnAround", "Priority");
        //writer.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");
        int index = 0;
        this.waiting = 0;
        this.completionTime = 50;
        int avgComp = 0;
        while(!rrArray.isEmpty()){
            process p = rrArray.get(index);

            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            //writer.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            p.timeReturn(time_quant);
            int t = p.getBurst();
            if(t <= 0){
                t = time_quant + t;
                //System.out.println(t);
                completionTime += t;
                avgComp += (t+completionTime);
                rrArray.remove(index);
                System.out.println("process:" + p.getPid() + " COMPLETED");
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
            ++index;
            if(index >= rrArray.size()){
                index = 0;
            }

        }

        System.out.println("\nAverage Turnaround Time: "+ (float)(avgComp/ array.size()));
        //writer.println("\nAverage Turnaround Time: "+ (float)(avgComp/ array.size()));
    }
    public void lotery()throws IOException{
        //PrintWriter writer = new PrintWriter(new FileWriter("LOtery-1.txt"));
        ArrayList<process> rrArray;
        rrArray = (ArrayList<process>)array.clone();
        Random rand = new Random();
        this.completionTime = 0;
        this.waiting=0;
        System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime", "TurnAround", "Priority");
        //writer.printf("%-5s %-12s %-12s %-12s \n", "pid", "BurstTime", "WaitingTime","TurnAround");

        while(!rrArray.isEmpty()){
            int index = (rand.nextInt(2000)%rrArray.size());
            process p = rrArray.get(index);
            this.completionTime += p.getBurst();
            System.out.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            //writer.printf("%-5s %-12s %-12s %-12s %-12s \n", p.getPid(), p.getBurst(), this.waiting, this.completionTime, p.getPriority());
            this.waiting += (p.getBurst() + 3);
            this.completionTime += 3;
            rrArray.remove(index);

        }
        System.out.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
        //writer.println("\nAverage Turnaround Time: " + (float)(this.completionTime / array.size()));
    }


}
