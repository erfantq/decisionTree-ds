
public class MyStack {
    private MyNode top;
    private int size;

    public MyStack(){
        top = null;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void push(Object data){
        MyNode t = new MyNode(data);
        if (isEmpty()){
            top = t;
        } else {
            t.setNext(top);
            top = t;
        }
        size++;
    }
    public Object pop(){
        if (isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        Object poppedData = top.getData();
        top = top.getNext();
        size--;
        return poppedData;
    }
    public Object peek(){
        if (isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return top.getData();
    }
    public Object get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }


}
