package algorithm;

import java.util.ArrayList;

public class Model {
    ArrayList<Task> tasks;
    ArrayList<Machine> machines;

    public Model(ArrayList<Task> tasks, ArrayList<Machine> machines) {
        this.tasks = tasks;
        this.machines = machines;
    }
}
