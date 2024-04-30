import java.util.*;

public class Bankers {
    public static void main(String[] args) {
        int[] Available = { 3, 3, 2 };
        List<Processes> processes = new ArrayList<>();
        processes.add(new Processes(01, new int[] { 7, 5, 3 }, new int[] { 0, 1, 0 }));
        processes.add(new Processes(02, new int[] { 3, 2, 2 }, new int[] { 2, 0, 0 }));
        processes.add(new Processes(03, new int[] { 9, 0, 2 }, new int[] { 3, 0, 2 }));
        processes.add(new Processes(04, new int[] { 2, 2, 2 }, new int[] { 2, 1, 1 }));
        processes.add(new Processes(05, new int[] { 4, 3, 3 }, new int[] { 0, 0, 2 }));

        for (Processes p : processes) {
            int i = 0;
            System.out.println(String.format("pid: %d, A:{%d,%d,%d}, M:{%d,%d,%d}, N:{%d,%d,%d}",p.Id,p.Allocated[i],p.Allocated[i+1],p.Allocated[i+2],p.Max[i],p.Max[i+1],p.Max[i+2],p.Needed[i],p.Needed[i+1],p.Needed[i+2]));
            
        }

        int[] safeSequence = new int[processes.size()];

         int count = 0;
        while (count < processes.size()) {
            for (Processes p : processes) {
                if (!p.finished) {
                    boolean f = true;

                    if (Arrays.compare(p.Needed, Available) > 0) {
                        f = false;
                    }
                    if (f) {
                        for (int j = 0; j < Available.length; j++) {
                            Available[j] += p.Allocated[j];
                        }
                        safeSequence[count] = p.Id;
                        p.finished = true;
                        count++;
                    }

                }
            }
        }

        System.out.println("The safeSequence is: \n");
        for (int i : safeSequence) {
            System.out.print("P"+i+"=>");
        }
    }
}

class Processes {
    int Id = 0;
    int[] Max;
    int[] Allocated;
    int[] Needed;
    boolean finished = false;

    public Processes(int id, int[] max, int[] allocated) {
        Id = id;
        Max = max;
        Allocated = allocated;
        Needed = new int[Allocated.length];
        for (int i = 0; i < allocated.length; i++) {
            Needed[i] = Max[i] - Allocated[i];
        }
    }
}
