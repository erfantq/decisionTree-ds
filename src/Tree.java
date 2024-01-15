
public class Tree {

    private int depth;
    private float[] weight;
    public Tree(){

    }
    public int getDepth(){
        return depth;
    }
    public void createTree(float[][] data, float[] labels){
        //TODO make this recursive and stop pure nodes from splitting
        float[] iGains = new float[data[0].length];
        float[] inputLabels = new float[data.length];
        float[] entropies;
        float[][] calculateChildrenEntropiesInput = new float[data.length][2];
        float pEntropy = entropy(labels);
        for (int i = 0; i < labels.length; i++)
            calculateChildrenEntropiesInput[i][1] = labels[i];
        for (int i = 0; i < data[0].length; i++) {
            for (int j = 0; j < data.length; j++) {
                calculateChildrenEntropiesInput[j][0] = data[j][i];
            }
            entropies = calculateChildrenEntropies(calculateChildrenEntropiesInput);
            iGains[i] = iGain(pEntropy, weight, entropies);
        }
        float max = -1f;
        int j = 0;
        for (int i = 0; i < iGains.length; i++) {
            if (max < iGains[i]) {
                max = iGains[i];
                j = i;
            }
        }

    }

    public int countUniques(float[][] data, int index) {
        MyLinkedList numbs = new MyLinkedList();
        for (int i = 0; i < data.length; i++) {
            if (numbs.isEmpty()) {
                numbs.add(data[i][index]);
            } else {
                int j = 0;
                for (; j < numbs.size(); j++) {
                    if ((float) numbs.get(j).getData() == data[i][index]) {
                        break;
                    }
                }
                if (j == numbs.size()) {
                    numbs.add(data[i][index]);
                }
            }
        }
        return numbs.size();
    }

    private float[] calculateChildrenEntropies(float[][] attributes) {  //calculates children entropies and weights
        MyLinkedList nodes;
        MyLinkedList numbs = new MyLinkedList();
        for (int i = 0; i < attributes.length; i++) {
            if (numbs.isEmpty()) {
                numbs.add(attributes[i][0]);
            } else {
                int j = 0;
                for (; j < numbs.size(); j++) {
                    if ((float) numbs.get(j).getData() == attributes[i][0]) {
                        break;
                    }

                }
                if (j == numbs.size()) {
                    numbs.add(attributes[i][0]);
                }
            }
        }
        int[] uniques = new int[numbs.size()];
        weight = new float[uniques.length];
        float[] values = new float[uniques.length];
        for (int i = 0; i < numbs.size(); i++) {
            values[i] = (float) numbs.get(i).getData();
        }
        for (int i = 0; i < attributes.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (attributes[i][0] == values[j]) {
                    uniques[j]++;
                    break;
                }
            }
        }
        for (int i = 0; i < weight.length; i++) {
            weight[i] = (float) uniques[i] / attributes.length;
        }
        float[] entropies = new float[uniques.length];
        for (int i = 0; i < uniques.length; i++) {
            float[] labels = new float[uniques[i]];
            int k = 0;
            for (int j = 0; j < attributes.length; j++) {
                if (attributes[j][0] == values[i]) {
                    labels[k++] = attributes[j][1];
                }
            }
            entropies[i] = entropy(labels);
        }
        return entropies;
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
            sum += (float) (p * Math.log10(p));
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
