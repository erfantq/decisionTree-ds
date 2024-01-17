import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Tree t = new Tree();
//        float[] test = {0,0,1,1};
//        System.out.println(t.entropy(test));
//
//        Tree t = new Tree();
//        float[][] test = {{0.1f, 0.2f}, {0.3f, 0.2f}, {0.1f, 0.1f}, {0.2f, 0.2f}, {0.3f, 0.2f}, {0.1f, 0.1f}};
//        float[] labels = {1f, 1f, 2f, 1f, 1f, 2f};
//        t.createTree(test, labels);


        String fileName = "feature_train.csv";
        File file = new File(fileName);
        String[] values;
        String data;

        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                data = inputStream.next();
                values = data.split(",");
                System.out.println(values[1]);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("check");
    }
}