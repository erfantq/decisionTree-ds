import java.util.LinkedList;

public class Tree {
    private int depth;
    public int getDepth(){
        return depth;
    }
    public void createTree(float[][] data, float[] labels){
        //todo implementation
        float[] iGains = new float[data.length];
        float[] inputLabels = new float[data.length];
        float[] entropies = new float[data[0].length];
        float weight = 0f;
        for (int i = 0; i < data[0].length; i++) {
            for (int j = 0; j < data.length; j++) {
                inputLabels[j] = data[i][j];
            }
            entropies[i] = entropy(inputLabels);
//            iGain(entropies[i], weight, );
        }
    }
    public float entropy(float[] labels){
        //todo change log
        float num = 0;
        int count = 0;
        float p = 0.0f;
        boolean isFirst = true;
        float sum = 0.0f;
        MyLinkedList tempNumbers = new MyLinkedList();
        for (int i = 0; i < labels.length; i++) {
            count = 0;
            if (isFirst){
                tempNumbers.add(labels[i]);
                num = labels[i];
                isFirst = false;
            } else {
                boolean doesExist = false;
                for (int j = 0; j < tempNumbers.size(); j++) {
                    if (labels[i] == (float) tempNumbers.get(j).getData()){
                        doesExist = true;
                        break;
                    }
                }
                if (!doesExist){
                    tempNumbers.add(labels[i]);
                    num = labels[i];
                } else continue;
            }
            for (int j = 0; j < labels.length; j++) {
                if (num == labels[j]){
                    count++;
                }
            }
            p = (float) count / labels.length;
            sum += p * Math.log10(p);
        }
        return -sum;
    }
    public float iGain(float pEntropy, float[] weight, float[] entropies){
        float sum = 0.0f;
        for (int i = 0; i < weight.length; i++) {
            sum += weight[i] * entropies[i];
        }
        return pEntropy - sum;
    }



}
