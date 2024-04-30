import java.util.LinkedHashMap;
import java.util.Map;

public class LRUPageReplacement {
    public static int lru(int[] pages, int[] frames) {
        Map<Integer, Integer> lruMap = new LinkedHashMap<Integer, Integer>(frames.length, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > frames.length;
            }
        };
        int faults = 0;
        for (int page : pages) {
            if (!lruMap.containsKey(page)) {
                lruMap.put(page, 1);
                faults++;
            } else {
                lruMap.put(page, lruMap.get(page) + 1);
            }
        }
        return faults;
    }
}