import java.io.*;
import java.util.Scanner;

public class Main {
    private static void readCSV(Queue queue, Scanner inputStream) {
        String line;
        String[] values;
        while (inputStream.hasNext()) {
            line = inputStream.next();
            values = line.split(",");
            float[] floats = new float[values.length];
            for (int i = 0; i < floats.length; i++) {
                floats[i] = Float.parseFloat(values[i]);
            }
            queue.insert(floats);
        }
    }
    public static void main(String[] args) {
        String fileName = "feature_train.csv";
        File file = new File(fileName);
        float[][] data;
        float[] labels;
        String[] dataNames;
        Queue queue;
        DTreeClassifier dTreeClassifier;
        try {
            //reading train data
            Scanner inputStream = new Scanner(file);
            inputStream.next(); //ignoring first line which has the data columns names.
            //dataNames = inputStream.next().split(","); // if we needed columns names, this line gets them but last line must be deleted/commented.
            queue = new Queue();
            readCSV(queue, inputStream);
            data = queue.returnAll();
            //reading train labels
            inputStream = new Scanner(new File("label_train.csv"));
            inputStream.next();
            queue = new Queue();
            readCSV(queue, inputStream);
            labels = queue.returnAll1D();
            //creating tree
            dTreeClassifier = new DTreeClassifier(data, labels);
            //reading test data
            inputStream = new Scanner(new File("feature_test.csv"));
            inputStream.next();
            queue = new Queue();
            readCSV(queue, inputStream);
            data = queue.returnAll();
            //reading test labels
            inputStream = new Scanner(new File("label_test.csv"));
            inputStream.next();
            queue = new Queue();
            readCSV(queue, inputStream);
            labels = queue.returnAll1D();
            //predicting and printing result
            float[] predicted = dTreeClassifier.predictAll(data, 50);
            System.out.println(dTreeClassifier.accuracy(labels, predicted));
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}