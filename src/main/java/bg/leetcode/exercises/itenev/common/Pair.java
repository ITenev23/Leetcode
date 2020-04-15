package bg.leetcode.exercises.itenev.common;

public class Pair<S, I> {
    public S key;
    public I value;

    public Pair(S key, I value) {
        this.key = key;
        this.value = value;
    }

    public S getKey() {
        return key;
    }

    public I getValue() {
        return value;
    }
}
