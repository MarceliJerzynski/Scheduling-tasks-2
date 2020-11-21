package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class Generator {

    Random random = new Random();

    public void generate(int n, File directory) throws IOException {
        Double[] b = new Double[5]; //speed of machines ( 1+ must be equals 1)
        int[] p = new int[n]; //duration
        int[] r = new int[n]; //when ready

        String fileName = directory.toPath() + "/in_jerzynski_" + n + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write(n + "\n");

        int sumP = 0;
        b[0] = 1.0d;
        writer.write(new DecimalFormat("##.00").format(b[0]).replace(",","."));
        for(int i = 1; i < 5; i++) {
            b[i] = random.nextDouble()*2 + 1; //random from 1 (inclusive), 3 (exclusive)
            writer.write(" " + new DecimalFormat("##.00").format(b[i]).replace(",","."));
        }

        writer.write("\n");

        for(int i = 0; i < n; i++){
            p[i] = random.nextInt(9) + 1;
            sumP += p[i];
        }
        for(int i = 0; i < n; i++) {
            r[i] = random.nextInt((int) (sumP/3)); //3 because of statistics ( average of machine speed is 2, so average amount of tasks per second is 3 )
            writer.write(p[i] + " " + r[i]);
            writer.write("\n");
        }

        writer.close();
    }
}