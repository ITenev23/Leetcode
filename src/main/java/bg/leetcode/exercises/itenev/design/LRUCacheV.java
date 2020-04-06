package bg.leetcode.exercises.itenev.design;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheV extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheV(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer val = super.get(key);
        return val == null ? -1 : val;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}
