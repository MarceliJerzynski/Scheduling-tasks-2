package algorithm;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toCollection;

public class Algorithm {

    public int machinesNumber;
    public void run(File input, File outputDirectory) throws IOException {

        var model = getModelAlgorithm(input);
        var result = runAlgorithm(model);


        var punishment = result.parallelStream().map(m -> m.punishment).reduce(Double::sum).get() / model.tasks.size();

        String outputNameFile = getOutputNameFile(input);

        saveToFile(result, punishment, outputDirectory, outputNameFile);
    }

    private Model getModelAlgorithm(File input) throws FileNotFoundException {
        var tasks = new ArrayList<Task>();
        Scanner myReader = new Scanner(input);
        int n = parseInt(myReader.nextLine());
        var machines = new ArrayList<Machine>();
        var machinesData = myReader.nextLine().split(" ");
        machinesNumber = machinesData.length;
        for (int j = 0; j < machinesData.length; j++) {
            int index = j+1;
            double speed = Double.parseDouble(machinesData[j]);
            machines.add(new Machine(index, speed));
        }

        int i = 0;
        while (myReader.hasNextLine()) {
            var task = new Task();
            task.index = i+1;
            String[] data = myReader.nextLine().split(" ");
            if (data.length == 2) {
                task.p = parseInt(data[0]);
                task.r = parseInt(data[1]);
                tasks.add(task);
                i++;
            }
        }
        return new Model(tasks, machines);
    }

    private ArrayList<Machine> runAlgorithm(Model model) {

        var tasks = model.tasks.parallelStream().sorted().collect(toCollection(ArrayList::new));
        var machines = model.machines.parallelStream().sorted().collect(toCollection(ArrayList::new));

        while(!tasks.isEmpty()) {
            var task = tasks.get(0);
            for(var machine: machines) {
                machine.actualPunishment = task.p * machine.speed;
                if (machine.time > task.r) {
                    machine.actualPunishment += machine.time - task.r;
                }
            }

            var theBestMachine =  Collections.min(machines, Comparator.comparing(m -> m.actualPunishment));

            theBestMachine.tasks.add(task);
            theBestMachine.punishment += theBestMachine.actualPunishment;
            theBestMachine.time += task.p * theBestMachine.speed;
            tasks.remove(task);

        }
        return machines;
    }

    private String getOutputNameFile(File input) {
        String inputFileName = input.getName();
        return inputFileName.replaceFirst("in", "out");
    }

//    private int getPunishment(Task[] tasks) {
//        int time = 0;
//        int result = 0;
//        for(int i = 0;i < tasks.length; i++) { //for each task
//            while(time < tasks[i].r) { //wait
//                time++;
//            }
//            time += tasks[i].p; //end task
//            if (time > tasks[i].d) {
//                result += tasks[i].w;
//            }
//        }
//        return result;
//    }

    private void saveToFile(ArrayList<Machine> machines, double punishment, File directory, String outputFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "/" + outputFileName));
        writer.write(punishment + "\n");
        for (Machine machine : machines) {
            for(var task : machine.tasks){
                writer.write(task.index + " ");
            }
            writer.write("\n");
        }
        writer.close();

    }
}
