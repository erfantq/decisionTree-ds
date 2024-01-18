public class TreeNode {
    float[][] data;
    public TreeNode[] nodes;
    private int index; //determines with what attribute the node has been split
    private float value; //determines with what value from parent this node has been split
    private boolean pureNode = false;
    public float[] labels;
    private float maxOutput;

    public float getMaxOutput() {
        return maxOutput;
    }

    public boolean isPureNode(){
        return pureNode;
    }

    public int getIndex() {
        return index;
    }

    public float getValue() {
        return value;
    }

    public boolean split(int index) {
        this.index = index;
        MyLinkedList numbs = new MyLinkedList();
        int count = Tree.countUniques(data, index, numbs);
        if (count == 1) {
            pureNode = true;
            return false;
        }
        nodes = new TreeNode[count];
        int[] uniques = new int[nodes.length];
        float[] values = new float[uniques.length];
        for (int i = 0; i < numbs.size(); i++) {
            values[i] = (float) numbs.get(i).getData();
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (data[i][index] == values[j]) {
                    uniques[j]++;
                    break;
                }
            }
        }
        float[][][] allIndexes = new float[uniques.length][][];  //[total children nodes][number of elements in that child][number of data columns]
        float[][] allLabels = new float[uniques.length][];  //[total children nodes][number of elements in that child]
        for (int i = 0; i < allIndexes.length; i++) {
            allIndexes[i] = new float[uniques[i]][data[0].length];
            allLabels[i] = new float[uniques[i]];
            int c = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j][index] == values[i]) {
                    allIndexes[i][c] = data[j];
                    allLabels[i][c] = labels[j];
                    c++;
                }
            }
        }
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new TreeNode(allIndexes[i], allLabels[i], values[i]);
        }
        return true;
    }
    public TreeNode(float[][] data,float[] labels, float value) {
        this(data, labels);
        this.value = value;
    }
    public TreeNode(float[][] data,float[] labels) {
        this.data = data;
        this.labels = labels;
        MyLinkedList saveLabels = new MyLinkedList();
        saveLabels.add(labels[0]);
        saveLabels.get(0).incrementCount();
        for (int i = 1; i < labels.length; i++) {
            int j = 0;
            for (; j < saveLabels.size();j++){
                if ((float) saveLabels.get(j).getData() == labels[i]) {
                    saveLabels.get(j).incrementCount();
                    break;
                }
            }
            if (j == saveLabels.size()) {
                saveLabels.add(labels[i]);
                saveLabels.get(saveLabels.size()-1).incrementCount();
            }
        }
        int[] counts = new int[saveLabels.size()];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = saveLabels.get(i).getCount();
        }
        float[] percents = new float[counts.length];
        for (int i = 0; i < percents.length; i++) {
            percents[i] = (float) counts[i] / labels.length;
        }
        float maxPercent = -1f;
        int maxIndex = 0;
        for (int i = 0; i < percents.length; i++) {
            if (percents[i] > maxPercent) {
                maxPercent = percents[i];
                maxIndex = i;
            }
            if (percents[i] >= 0.9f) {
                maxOutput = (float) saveLabels.get(i).getData();
                pureNode = true;
                this.data = null;
                this.labels = null;
                break;
            }
        }
        if (!pureNode) {
            maxOutput = (float) saveLabels.get(maxIndex).getData();
        }
    }
}
