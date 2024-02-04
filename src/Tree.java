
public class Tree {
    TreeNode root;

    private int columns;

    private int depth;
    public int getDepth(){
        return depth;
    }

    private void createTreeRecursive(TreeNode node, int currentDepth) {
        currentDepth++;
        if (currentDepth > columns * 0.8) {
            node.setPureNode(true);
            return;
        }
        if (currentDepth > depth){
            depth = currentDepth;
        }
        int splitIndex = 0;
        {
            float[] iGains = new float[node.data[0].length];
            float[] entropies;
            Weights weights = new Weights();
            float[][] calculateChildrenEntropiesInput = new float[node.data.length][2];
            float pEntropy = entropy(node.labels);
            for (int i = 0; i < node.labels.length; i++)
                calculateChildrenEntropiesInput[i][1] = node.labels[i];
            for (int i = 0; i < node.data[0].length; i++) {
                for (int j = 0; j < node.data.length; j++) {
                    calculateChildrenEntropiesInput[j][0] = node.data[j][i];
                }
                entropies = calculateChildrenEntropies(calculateChildrenEntropiesInput, weights);
                iGains[i] = iGain(pEntropy, weights.w, entropies);
            }
            int[] indexes = new int[iGains.length];
            for (int i = 0; i < iGains.length; i++) {
                indexes[i] = i;
            }
            int n = iGains.length;
            boolean sw = true;
            for (int i = n-1; i > 0 && sw; i--) {
                sw = false;
                for (int j = 0; j < i; j++) {
                    if (iGains[j] > iGains[j+1]) {
                        int temp1 = indexes[j];
                        float temp2 = iGains[j];
                        indexes[j] = indexes[j+1];
                        indexes[j+1] = temp1;
                        iGains[j] = iGains[j+1];
                        iGains[j+1] = temp2;
                        sw = true;
                    }
                }
            }
            for (int i = 0; i < iGains.length; i++) {
                if(node.split(indexes[i])) {
                    node.data = null;
                    node.labels = null;
                    for (int j = 0; j < node.nodes.length; j++) {
                        if (!node.nodes[j].isPureNode()) {
                            createTreeRecursive(node.nodes[j], currentDepth);
                        }
                    }
                    break;
                }
            }
            /*float max = -1f;
            
            for (int i = 0; i < iGains.length; i++) {
                if (max < iGains[i]) {
                    max = iGains[i];
                    splitIndex = i;
                }
            }*/
        }
        //iGains calculated
        /*if (node.split(splitIndex)) {
            if (node.nodes.length == 1) {
                System.out.println("bad");
            }
            node.data = null;
            node.labels = null;
            for (int i = 0; i < node.nodes.length; i++) {
                if (!node.nodes[i].isPureNode()) {
                    createTreeRecursive(node.nodes[i], currentDepth);
                }
            }
        }*/
    }
    public void createTree(float[][] data, float[] labels){
        columns = data[0].length;
        root = new TreeNode(data, labels);
        if (!root.isPureNode()){
            createTreeRecursive(root, 0);
        }
    }

    public static int countUniques(float[][] data, int index, MyLinkedList numbs) {
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

    private float[] calculateChildrenEntropies(float[][] attributes, Weights weights) {  //calculates children entropies and weights
        MyLinkedList numbs = new MyLinkedList();
        int[] uniques = new int[countUniques(attributes, 0, numbs)];
        weights.w = new float[uniques.length];
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
        for (int i = 0; i < weights.w.length; i++) {
            weights.w[i] = (float) uniques[i] / attributes.length;
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
        float num = 0f;
        int count = 0;
        float p = 0.0f;
        boolean isFirst = true;
        float sum = 0.0f;
        MyLinkedList tempNumbers = new MyLinkedList();
        for (int i = 0; i < labels.length; i++) {
            if (isFirst){
                tempNumbers.add(labels[i]);
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
