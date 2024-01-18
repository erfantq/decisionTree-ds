public class Queue {
    private int size = 0;
    public QueueNode head = null;
    public QueueNode tail = null;
    public int getSize() {
        return size;
    }
    public void insert(float[] in) {
        QueueNode q = new QueueNode(in);
        if (head != null) {
            tail.next = q;
        }
        else {
            head = q;
        }
        tail = q;
        size++;
    }

    public float[][] returnAll() {
        float[][] out = new float[size][head.values.length];
        QueueNode current = head;
        for (int i = 0; i < out.length; i++) {
            for (int j = 0; j < out[i].length; j++) {
                out[i][j] = current.values[j];
            }
            current = current.next;
        }
        return out;
    }

    public float[] returnAll1D() {
        float[] out = new float[size];
        QueueNode current = head;
        for (int i = 0; i < out.length; i++) {
            out[i] = current.values[0];
            current = current.next;
        }
        return out;
    }
}
