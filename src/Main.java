public class Main {
    public static void main(String[] args) {
//        Tree t = new Tree();
//        float[] test = {0,0,1,1};
//        System.out.println(t.entropy(test));

        Tree t = new Tree();
        float[][] test = {{0.1f, 0.2f}, {0.3f, 0.2f}, {0.1f, 0.1f}, {0.2f, 0.2f}, {0.3f, 0.2f}, {0.1f, 0.1f}};
        float[] labels = {1f, 1f, 2f, 1f, 1f, 2f};
        t.createTree(test, labels);
    }
}