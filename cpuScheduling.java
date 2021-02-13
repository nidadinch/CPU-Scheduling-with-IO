

import java.io.*;
import java.util.*;

/**
 * Java program for Operating Systems
 * FCFS Scheduling with IO Burst
 *
 * @author Nida DİNÇ
 */

public class cpuScheduling {

    static String textfile;
    static double deadProcessesListSize;
    static int totalTAT;
    static double averageTAT;
    static double averageWT;


    public static void main(String[] args) throws FileNotFoundException {


        try {
            if (args.length != 1) {
                System.out.println("Lütfen dosya yolunu doğru veriniz");
                if (args.length == 0) {
                    System.out.println("Dosya yolu girilmedi");
                } else
                    System.out.println("Birden fazla parametre girildi");
                return;
            }
            textfile = args[0];


            FCFS();



        } catch (IOException e) {
            e.printStackTrace();

        }



    }

    static void FCFS() throws FileNotFoundException {

        // process list for holding processes after reading from file
        List<Process> processList = new ArrayList<>();
        // process list for holding executed processes after IO is -1
        ArrayList<Process> deadProcessesList = new ArrayList<>();

        int currentTime = 0;
        int totalWT = 0;
        int i = 0;



        File file = new File(textfile);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String num = input.nextLine();
            // Deleting "(" & ")" characters from scanned string
            String inputReplace = num.replaceAll("[[()]*]", "");
            inputReplace = inputReplace.replaceAll("\\r\\n","");

            // First, split with : and leftmost of this operation equals process id
            String splitString[] = inputReplace.split(":");


            // Rightmost of this splitting operation equals bursts
            String burstsAll = splitString[1];

            // Bursts splitted with ";" for getting every couple of <cpuBurst, IOBurst>
            String burstsSplit[] = burstsAll.split(";");

            // Every couple of <cpuBurst, IOBurst> stored in a arrayList for calculating
            ArrayList<String> processBurstList = new ArrayList<>();
            processBurstList.addAll(Arrays.asList(burstsSplit));

            // New process created with splitting and every couple of <cpuBurst, IOBurst>
            processList.add(new Process(Integer.parseInt(splitString[0]), processBurstList));

        }

        while (!processList.isEmpty()) {

            for (i = 0; i < processList.size(); ) {

                /*  If process' turnaround time lower than or equals current time
                    current time - process' last cpu burst + last arrival time is added to this process' waiting time
                    and update process' last arrival time to current time & cpu to current CPU
                */
                if (processList.get(i).turnAroundTime <= currentTime) {

                    // Process' every couple of <cpuBurst, IOBurst> splitted with ","
                    String cpuIOBursts = processList.get(i).cpuIObursts.get(0);
                    String cpuIOBurstsArray[] = cpuIOBursts.split(",");

                    // and current cpu & io burst initialized
                    int CPU = Integer.parseInt(cpuIOBurstsArray[0]);
                    int IO = Integer.parseInt(cpuIOBurstsArray[1]);


                    processList.get(i).waitingTime += currentTime - (processList.get(i).lastCpuBurst + processList.get(i).lastArrivalTime);
                    processList.get(i).lastArrivalTime = currentTime;
                    processList.get(i).lastCpuBurst = CPU;

                    // If current process' IOburst equals -1, process' turn around time is current time + current CPU burst.

                    if (IO == -1) {
                        processList.get(i).turnAroundTime = currentTime + CPU;
                        totalTAT += processList.get(i).turnAroundTime;

                        // process added to dead processes list to calculate averages
                        deadProcessesList.add(processList.get(i));

                        // current cpu added to current time
                        currentTime += CPU;

                        // process deleted from processes list
                        processList.remove(i);
                    } else {

                        // If IO is not equals -1 current process' turn around time is current time + current cpu + current io burst
                        processList.get(i).turnAroundTime = currentTime + CPU + IO;

                        // current cpu added to current time because process is executed
                        currentTime += CPU;

                        // and delete this cpu&io bursts from process' cpuIObursts list for not to use it again
                        processList.get(i).cpuIObursts.remove(0);
                    }

                    i = 0;

                } else {
                    i++;
                }

            }
            deadProcessesListSize = deadProcessesList.size();
            currentTime++;
        }
        // calculate total waiting time
        while(i < deadProcessesListSize){
            totalWT += deadProcessesList.get(i++).waitingTime;

        }

        averageTAT = totalTAT / deadProcessesListSize;
        averageWT = totalWT / deadProcessesListSize;


        System.out.println("Average Turnaround Time: " + averageTAT);
        System.out.println( "Average Waiting Time: " + averageWT);


    }


}

class Process {
    public int pid;
    public int cpuBurst;
    public int IOBurst;
    public int lastCpuBurst = 0;
    public int lastArrivalTime = 0;
    public int turnAroundTime = 0;
    public int waitingTime = 0;
    public ArrayList<String> cpuIObursts;


    // splitting scanned text with only process object
    public Process(String input) {

        String inputReplace = input.replaceAll("[[()]*]", "");
        String splitString[] = inputReplace.split(":");
        String burstAll = splitString[1];
        String burstsSplit[] = burstAll.split(";");
        String cpuIOBursts = burstsSplit[0];
        String cpuBursts[] = cpuIOBursts.split(",");


        this.pid = Integer.parseInt(splitString[0]);
        //  System.out.println("PID: " + pid);
        this.cpuBurst = Integer.parseInt(cpuBursts[0]);
        //  System.out.println("CPU BURST: " + cpuBurst);
        this.IOBurst = Integer.parseInt(cpuBursts[1]);
        //  System.out.println("IO BURST: " + IOBurst);

        this.cpuIObursts.addAll(Arrays.asList(burstsSplit));
    }


    public Process(int pid, ArrayList<String> cpuIObursts) {
        this.pid = pid;
        this.cpuIObursts = cpuIObursts;
    }


}




