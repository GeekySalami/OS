import java.util.Scanner;

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        int[] burstTime = new int[numProcesses];
        int[] arrivalTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        int[] turnAroundTime = new int[numProcesses];
        int timeQuantum, totalTime = 0;

        System.out.print("Enter the burst time of each process: ");
        for (int i = 0; i < numProcesses; i++) {
            burstTime[i] = scanner.nextInt();
            totalTime += burstTime[i];
        }

        System.out.print("Enter the arrival time of each process: ");
        for (int i = 0; i < numProcesses; i++) {
            arrivalTime[i] = scanner.nextInt();
        }

        System.out.print("Enter the time quantum: ");
        timeQuantum = scanner.nextInt();

        int[] remainingTime = burstTime.clone();
        int currentTime = 0;
        boolean finished[] = new boolean[numProcesses];

        while (true) {
            boolean allFinished = true;
            for (int i = 0; i < numProcesses; i++) {
                if (!finished[i] && arrivalTime[i] <= currentTime) {
                    allFinished = false;
                    if (remainingTime[i] <= timeQuantum) {
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        finished[i] = true;
                    } else {
                        currentTime += timeQuantum;
                        remainingTime[i] -= timeQuantum;
                    }
                }
            }

            if (allFinished || currentTime >= totalTime) {
                break;
            }
        }

        for (int i = 0; i < numProcesses; i++) {
            waitingTime[i] = currentTime - burstTime[i] - arrivalTime[i];
            turnAroundTime[i] = waitingTime[i] + burstTime[i];
        }

        System.out.println("Process\tWaiting Time\tTurn Around Time");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(i + "\t" + waitingTime[i] + "\t" + turnAroundTime[i]);
        }

        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        for (int i = 0; i < numProcesses; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
        }

        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / numProcesses);
        System.out.println("Average Turn Around Time: " + (float) totalTurnAroundTime / numProcesses);
    }
}