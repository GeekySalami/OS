import java.util.*;

public class PriorityJobScheduling {
    static class Job implements Comparable<Job> {
        int id;
        int priority;
        int burstTime;
        int completionTime;
        int turnAroundTime;
        int waitingTime;

        public Job(int id, int priority, int burstTime) {
            this.id = id;
            this.priority = priority;
            this.burstTime = burstTime;
            this.completionTime = 0;
            this.turnAroundTime = 0;
            this.waitingTime = 0;
        }

        @Override
        public int compareTo(Job other) {
            return this.priority - other.priority;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of jobs: ");
        int numJobs = scanner.nextInt();
        PriorityQueue<Job> minHeap = new PriorityQueue<>();
        int[] jobIds = new int[numJobs];

        System.out.print("Enter the priority of each job: ");
        for (int i = 0; i < numJobs; i++) {
            int priority = scanner.nextInt();
            int burstTime = scanner.nextInt();
            minHeap.add(new Job(i, priority, burstTime));
        }

        System.out.print("Enter the job ids: ");
        for (int i = 0; i < numJobs; i++) {
            jobIds[i] = scanner.nextInt();
        }

        int currentTime = 0;
        while (!minHeap.isEmpty()) {
            Job job = minHeap.poll();
            currentTime += job.burstTime;
            job.completionTime = currentTime;
            job.turnAroundTime = currentTime - job.burstTime;
            job.waitingTime = job.turnAroundTime - job.burstTime;
        }

        System.out.println("Job Schedule:");
        for (int i = 0; i < numJobs; i++) {
            System.out.println("Job " + jobIds[i] + ": Completion Time = " + minHeap.poll().completionTime + ", Turn Around Time = " + minHeap.poll().turnAroundTime + ", Burst Time = " + minHeap.poll().burstTime + ", Waiting Time = " + minHeap.poll().waitingTime);
        }
    }
}