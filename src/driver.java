/************************************************************************************
 *	file: ChatClient.java
 *	author: Daniel Spencer
 *	class: CS 431 - Operating Systems
 *
 *	assignment: Project 1
 *	date last modified: 10/13/2017
 *
 *	purpose: Create FCFS, FJO, RR, and LOTTERY Schedulers
 *
 *
 ************************************************************************************/
import java.io.IOException;

public class driver{
    public static void main(String[] args)throws IOException{
        Scheduler part1 = new Scheduler("input4.txt");
        Scheduler part1rr = new Scheduler("input4.txt");
        Scheduler part1rr2 = new Scheduler("input4.txt");
        System.out.println("\nFirstComeFirstServe");
        part1.FCFS();
        System.out.println("\nShortestJobFirst");
        part1.SFJ();
        System.out.println("\nRoundRobin-TQ 25");
        part1rr.RR(25);
        System.out.println("\nRoundRobin-TQ 50");
        part1rr2.RR(50);
        System.out.println("\nLottery");
        part1.lotery();


    }

}
