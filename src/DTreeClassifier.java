public class DTreeClassifier {
    public Tree tree;
    public DTreeClassifier(float[][] data, float[] labels){
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
            boolean broke = false;
            for (int i = 0; i < current.nodes.length; i++) {
                if (data[current.getIndex()] == current.nodes[i].getValue()) {
                    current = current.nodes[i];
                    count++;
                    broke = true;
                    break;
                }
            }
            if (!broke) break;
        }
        return current.getMaxOutput();
    }

    public float accuracy(float[] labels, float[] labelsPredicted){
        int countCorrectLabels = 0;
        float result = 0.0f;
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] == labelsPredicted[i]){
                countCorrectLabels++;
            }
        }
        result = ((float) countCorrectLabels / labelsPredicted.length) * 100;
        return result;
    }
}
