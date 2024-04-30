import java.util.HashMap;
import java.util.Map;

public class OptimalPageReplacement {
    static int pageFaults(int[] pages, int n, int capacity) {
        Map<Integer, Integer> indexes = new HashMap<>();
        int pageFaults = 0; 

        for (int i = 0; i < n; i++) {
            if (indexes.size() < i) {
                indexes.put(pages[i], i);
                pageFaults++;
            } else {
                int maxIndex = -1;
                for (int j = i + 1; j < n; j++) {
                    if (indexes.containsKey(pages[j])) {
                        maxIndex = Math.max(maxIndex, indexes.get(pages[j]));
                    }
                }
                if (maxIndex == -1) {
                    indexes.put(pages[i], i);
                    pageFaults++;
                } else {
                    pageFaults++;
                    indexes.remove(pages[maxIndex]);
                }
            }
        }
        return pageFaults;
    }
}