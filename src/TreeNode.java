public class TreeNode {
    float[][] data;
    TreeNode[] nodes;
    private int index; //determines with what attribute the node has been split
    private float value; //determines with what value from parent this node has been split
    private boolean pureNode = true;
    public float[] labels;
    public boolean isPureNode(){
        return pureNode;
    }

    public void split(int index) {
        this.index = index;
        MyLinkedList numbs = new MyLinkedList();
        nodes = new TreeNode[Tree.countUniques(data, index, numbs)];
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
        data = null;
        labels = null;
    }
    public TreeNode(float[][] data,float[] labels, float value) {
        this.data = data;
        this.value = value;
        this.labels = labels;
        float save = labels[0];
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] != save) {
                pureNode = false;
                break;
            }
        }
    }
    public TreeNode(float[][] data,float[] labels) {
        this.data = data;
        this.labels = labels;
        float save = labels[0];
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] != save) {
                pureNode = false;
                break;
            }
        }
    }


}
