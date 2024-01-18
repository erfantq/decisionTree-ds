public class QueueNode {
    QueueNode next;
    float[] values;

    public QueueNode(float[] in) {
        values = new float[in.length];
        for (int i = 0; i < in.length; i++) {
            values[i] = in[i];
        }
    }
}
