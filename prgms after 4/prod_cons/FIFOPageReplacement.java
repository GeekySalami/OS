import java.util.ArrayDeque;
import java.util.Deque;

public class FIFOPageReplacement {
    public static int fifo(int[] pages, int[] frames) {
        Deque<Integer> queue = new ArrayDeque<>();
        int faults = 0;
        for (int page : pages) {
            if (!queue.contains(page)) {
                if (queue.size() < frames.length) {
                    queue.add(page);
                    faults++;
                } else {
                    queue.poll();
                    queue.add(page);
                }
            }
        }
        return faults;
    }
}