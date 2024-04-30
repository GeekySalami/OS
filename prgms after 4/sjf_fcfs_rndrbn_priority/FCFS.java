import java.util.*;

/**
 * FCFS
 */
public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.println(
                    String.format("Enter the process id, arrival time, burst time of the process %d: ", (i + 1)));
            int at = sc.nextInt(), bt = sc.nextInt();
            processes.add(new Process((i + 1), at, bt));
        }

        for (Process process : processes) {
            System.out.println(
                    String.format("PID: %d, AT: %d, BT: %d", process.ID, process.ArrivalTime, process.BurstTime));
        }

        Collections.sort(processes, (p1, p2) -> {
            // Prioritize by arrival time first
            
                return Integer.compare(p1.ArrivalTime, p2.ArrivalTime);
            
        });

        for (Process process : processes) {
            System.out.println(
                    String.format("PID: %d, AT: %d, BT: %d", process.ID, process.ArrivalTime, process.BurstTime));

        }

        int tp = 0;
        System.out.println("++++++++++++++++++++++++");

        for (int i = 0; i < processes.size(); i++) {
            tp += processes.get(i).BurstTime;

            processes.get(i).CompletionTime = tp;
            processes.get(i).TurnAroundTime = processes.get(i).CompletionTime - processes.get(i).ArrivalTime;
            processes.get(i).waitingTime = (tp != processes.get(i).BurstTime)
                    ? processes.get(i).TurnAroundTime - processes.get(i).BurstTime
                    : 0;
        }

        for (Process process : processes) {
            System.out.println(
                    String.format("PID: %d, AT: %d, BT: %d, WT: %d, CT: %d, TAT: %d", process.ID, process.ArrivalTime,
                            process.BurstTime, process.waitingTime, process.CompletionTime, process.TurnAroundTime));
        }
    }

}

class Process {
    int ID;
    int ArrivalTime;
    int BurstTime;
    int TurnAroundTime = 0;
    int CompletionTime = 0;
    int waitingTime = 0;

    Process(int id, int at, int bt) {
        this.ID = id;
        this.ArrivalTime = at;
        this.BurstTime = bt;
    }
}