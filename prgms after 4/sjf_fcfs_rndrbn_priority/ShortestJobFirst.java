import java.util.*;

/**
 * ShortestJobFirst
 */
public class ShortestJobFirst {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        Process p []= new Process [n];
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("Enter the process id, arrival time, burst time of the process %d: ", (i+1)));
            int at = sc.nextInt(), bt = sc.nextInt();
            processes.add(new Process((i+1), at, bt));
        }

        for (Process process : processes) {
            System.out.println(String.format("PID: %d, AT: %d, BT: %d", process.ID,process.ArrivalTime,process.BurstTime));
        }

        Collections.sort(processes,(p1, p2) -> {
            // Prioritize by arrival time first
            if (p1.ArrivalTime != p2.ArrivalTime) {
                return Integer.compare(p1.ArrivalTime, p2.ArrivalTime);
            } else {
                // If arrival times are the same, prioritize by burst time (shorter first)
                return Integer.compare(p1.BurstTime, p2.BurstTime);
            }
        });
        /*
        Queue<Process> acctoburst = new PriorityQueue<>((p1, p2) -> {
            // Prioritize by arrival time first
            if (p1.ArrivalTime != p2.ArrivalTime) {
                return Integer.compare(p1.ArrivalTime, p2.ArrivalTime);
            } else {
                // If arrival times are the same, prioritize by burst time (shorter first)
                return Integer.compare(p1.BurstTime, p2.BurstTime);
            }
        });
        

        for (Process process : processes) {
            acctoburst.add(process);
        }
        */

        for (Process process : processes) {
            System.out.println(String.format("PID: %d, AT: %d, BT: %d", process.ID,process.ArrivalTime,process.BurstTime));
            
        }
        
        int tp = 0;
        System.out.println("++++++++++++++++++++++++");
        
        int i = 0;
        /*
        List<Process> execorder = new ArrayList<>();
        while (processes.size() != execorder.size()) {
            tp += processes.get(i).BurstTime;  

            processes.get(i).CompletionTime = tp;
            processes.get(i).TurnAroundTime = processes.get(i).CompletionTime-processes.get(i).ArrivalTime;
            processes.get(i).waitingTime = (tp!=processes.get(i).BurstTime)?processes.get(i).TurnAroundTime-processes.get(i).BurstTime:0;
            processes.get(i).exec = true;
            execorder.add(processes.get(i));
            
        }
        */

        PriorityQueue <Process> pq = new PriorityQueue<>(n, (p1,p2)-> Integer.compare(p1.BurstTime, p2.BurstTime));
        while (true) {
            
        }













        for (Process process : execorder) {
            System.out.println(String.format("PID: %d, AT: %d, BT: %d, WT: %d, CT: %d, TAT: %d", process.ID,process.ArrivalTime,process.BurstTime, process.waitingTime,process.CompletionTime,process.TurnAroundTime));
        }
        
        sc.close();
    }
}

class Process{
    int ID;
    int ArrivalTime;
    int BurstTime;
    int TurnAroundTime = 0;
    int CompletionTime = 0;
    int waitingTime = 0;
    boolean exec = false;

    Process(int id, int at, int bt){
        this.ID = id;
        this.ArrivalTime = at;
        this.BurstTime = bt;
    }
}