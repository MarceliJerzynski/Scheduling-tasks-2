package algorithm;

public class Task implements Comparable<Task> {
    int index;
    int p;
    int r;

    @Override
    public int compareTo(Task other) {
        return Double.compare(r, other.r);
    }

}
