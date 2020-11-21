package verifier;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Verifier {

    private static final int numberOfMachines = 5;

    public void verify(File[] files) throws FileNotFoundException {

        File input = files[0];
        File output = files[1];
        Scanner myReader = new Scanner(input);
        int n = parseInt(myReader.nextLine().trim());

        double[] b = new double[numberOfMachines];
        int[] p = new int[n]; //duration
        int[] r = new int[n]; //when ready
        int i = 0;

        var bTexts = myReader.nextLine().split(" ");
        for (int j = 0; j < numberOfMachines; j++) {
            b[j] = Double.parseDouble(bTexts[j].replace(",","."));
        }

        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(" ");
            if (data.length == 2) {
                p[i] = parseInt(data[0]);
                r[i] = parseInt(data[1]);
                i++;
            }
        }
        myReader.close();
        myReader = new Scanner(output);
        Integer[][] order = new Integer[5][n];
        int result = parseInt(myReader.nextLine().trim());
        double realResult = 0;
        int j =0;
        while (myReader.hasNextLine()) {
            i = 0;
            for (String number : myReader.nextLine().split(" ")) {
                order[j][i] = parseInt(number) - 1;
                i++;
            }
            j++;
        }

        for(int k = 0; k < numberOfMachines; k++) {
            double time = 0;
            for (i = 0; i < n; i++) { //for each task
                if (order[k][i] == null) break;
                if (time < r[order[k][i]]) {
                    time = r[order[k][i]];
                }
                time += p[order[k][i]]*b[k]; //end task
                realResult += time - r[order[k][i]];
            }
        }
        realResult = Math.round(realResult / n * 100) / 100.0;

        System.out.println("-----------------");
        System.out.println("Analysing files: ");
        System.out.println(ConsoleColors.BLUE + "    Input   ---> " + input.getPath());
        System.out.println("    Output  ---> " + output.getPath());
        System.out.println("    N       ---> " + n + ConsoleColors.RESET);
        System.out.println("\n");
        System.out.println("    given punishment ---> " + result);
        System.out.println("    real punishment  --->" + realResult);
        if (realResult == result) {
            System.out.println(ConsoleColors.GREEN + "===VALID===" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "===INVALID===" + ConsoleColors.RESET);
        }

        myReader.close();
    }
}