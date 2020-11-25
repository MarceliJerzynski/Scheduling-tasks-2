package algorithm;

import java.util.ArrayList;

public class Machine implements Comparable<Machine>{
    int index;
    double speed;
    ArrayList<Task> tasks;
    double time;
    double actualPunishment;
    double punishment;

    public Machine(int index, double speed) {
        this.index = index;
        this.speed = speed;
        tasks = new ArrayList<>();
        time = 0;
        actualPunishment = 0;
        punishment = 0;
    }


    @Override
    public int compareTo(Machine o) {
        return Double.compare(speed, o.speed);
    }
}
