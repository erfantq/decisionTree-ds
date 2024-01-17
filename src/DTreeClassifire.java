public class DTreeClassifire {
    private float[][] data;
    private float[] labels;
    private Tree tree;
    public DTreeClassifire(float[][] data, float[] labels){
        this.data = data;
        this.labels = labels;
        tree = new Tree();
        tree.createTree(data, labels);
    }

    public float[] predictAll(float[][] data, int depth) {
        float[] output = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            output[i] = predict(data[i], depth);
        }
        return output;
    }

    public float predict(float[] data, int depth){
        TreeNode current = tree.root;
        int count = 0;
        while (!current.isPureNode() && count < depth) {
            for (int i = 0; i < current.nodes.length; i++) {
                if (data[current.getIndex()] == current.nodes[i].getValue()) {
                    current = current.nodes[i];
                    count++;
                    break;
                }
            }
        }
        return current.getMaxOutput();
    }

    public float accuracy(int[] labels, int[] labelsPredicted){
        int countCorrectLabels = 0;
        float result = 0.0f;
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] == labelsPredicted[i]){
                countCorrectLabels++;
            }
        }
        result = (countCorrectLabels / labelsPredicted.length) * 100;
        return result;
    }
}
