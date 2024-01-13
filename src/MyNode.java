
public class MyNode{
    private Object data;
    private MyNode next;
    public MyNode() {
        this(0);
    }
    public MyNode(Object data){
        setData(data);
        setNext(null);
    }
    public void setData(Object data){
        this.data = data;
    }
    public Object getData() {
        return data;
    }
    public void setNext(MyNode next){
        this.next = next;
    }
    public MyNode getNext() {
        return next;
    }
    public String toString() {
        return "[" + data + "]";
    }
}

