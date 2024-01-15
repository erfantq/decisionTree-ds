import sjh.T;

public class TreeNode {
    float[][] data;
    TreeNode[] nodes;

    boolean pureNode = false;

    public void split(int index) {
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
        nodes = new TreeNode[numbs.size()];
        int[] uniques = new int[numbs.size()];
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
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new TreeNode();
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

}
