public class MyLinkedList {
    private MyNode first;
    public MyLinkedList(){
        first = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void traverse(){
        MyNode p = new MyNode();
        if (isEmpty())
            System.out.println("list is empty!");
        else {
            p = first;
            while (p != null){
                System.out.println(p.getData());
                p.setNext(p);
            }
        }
    }
    public int size(){
        int count = 1;
        if (isEmpty())
            return 0;
        else {
            MyNode p = first;
            while (p.getNext() != null){
                p = p.getNext();
                count++;
            }
        }
        return count;
    }
    public void add(Object x){
        if (first == null){
            first = new MyNode(x);
            first.setNext(null);
        } else {
            MyNode p = first;
            MyNode q = new MyNode(x);
            while (p.getNext() != null)
                p = p.getNext();
            p.setNext(q);
        }
    }
    public void remove(int i){
        if (i >= size() || i < 0){
            throw new IndexOutOfBoundsException("out of index");
        } else {
            MyNode p = first;
            for (int j = 0; j < i - 1; j++) {
                p = p.getNext();
            }
            p.setNext(p.getNext().getNext());
        }
    }
    public MyNode get(int i){
        MyNode p = first;
        for (int j = 0; j < i; j++) {
            if (p == null){
                throw new IndexOutOfBoundsException("out of index");
            } else {
                p = p.getNext();
            }
        }
        return p;
    }
}
