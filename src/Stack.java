import java.util.NoSuchElementException;

public class Stack <T> implements Cloneable {
    private Node<T> head;
    private int size;

    public Stack(){
        this.head = null;
        this.size = 0;
    }
    public Stack(Node<T> head, int size){
        this.head = head;
        this.size = size;
    }

    public boolean isEmpty() {
        return  this.size == 0;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }
    public void push(T element){
        Node<T> newNode = new Node<>(element,head);
//        if (!this.isEmpty()) {
//            newNode.setPrevious(this.head);
//        }
        this.head = newNode;
        this.size ++;
    }

    public T top(){
        if (this.isEmpty()){
            throw new NoSuchElementException("top() not allowed on Empty Stack!");
        }
        return this.head.getElement();
    }

    public T pop(){
        if (this.isEmpty()){
            throw new NoSuchElementException("pop() not allowed on Empty Stack!");

        }

        T removed_element = this.head.getElement();

//        if (this.size == 1) clear();
//        else{
        this.head = this.head.getPrevious();
        this.size--;
//        }

        return removed_element;
    }

    public int getSize(){
        return this.size;
    }

    public Node<T> getHead(){
        return this.head;
    }

    // Overriding clone() method of Object class
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}



















