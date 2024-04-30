import java.util.*;

class Process {
    int pid;
    int burstTime;
    int arrivalTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;

    public Process(int pid, int burstTime, int arrivalTime){
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public void calculateCompletionTime(int time) {
        completionTime = time;
        turnAroundTime = completionTime - arrivalTime;
        waitingTime = turnAroundTime - burstTime;
    }
}

public class SJF {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int numProcesses = scanner.nextInt();
        PriorityQueue<Process> processQueue = new PriorityQueue<Process>(numProcesses, new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                if (p1.arrivalTime!= p2.arrivalTime) {
                    return Integer.compare(p1.arrivalTime, p2.arrivalTime);
                } else {
                    return Integer.compare(p1.burstTime, p2.burstTime);
                }
            }
        });

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter the arrival time and burst time for process " + (i + 1));
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processQueue.add(new Process(i + 1, burstTime, arrivalTime));
        }

        int time = 0;
        while (!processQueue.isEmpty()) {
            Process currentProcess = processQueue.poll();
            if (currentProcess.arrivalTime > time) {
                time = currentProcess.arrivalTime;
            }
            time += currentProcess.burstTime;
            currentProcess.calculateCompletionTime(time);
            System.out.println(String.format("PID: %d, AT: %d, BT: %d, WT: %d, CT: %d, TAT: %d", currentProcess.pid, currentProcess.arrivalTime, currentProcess.burstTime, currentProcess.waitingTime, currentProcess.completionTime, currentProcess.turnAroundTime));
        }

        scanner.close();
    }
}