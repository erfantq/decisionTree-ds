public class DTreeClassifire {
    private float[][] data;
    private float[] labels;
    public DTreeClassifire(float[][] data, float[] labels){
        this.data = data;
        this.labels = labels;
    }
    float predict(float[][] data, int depth){
        //todo implementation
        return 0.0f;
    }
    float accuracy(int[] labels, int[] labelsPredicted){
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
