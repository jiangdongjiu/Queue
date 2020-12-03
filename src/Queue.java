public class Queue <T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public Queue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return  this.size == 0;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(T element){
        Node<T> newNode = new Node<>(element);
        if (isEmpty()){
            this.head = newNode;
        }
        else{
            this.tail.setNext(newNode);
        }

        this.tail = newNode;
        this.size ++;
    }

    public T front(){
        if (this.isEmpty()){
            throw new IllegalArgumentException();
        }
        return this.head.getElement();
    }

    public T dequeue(){
        if (this.isEmpty()){
            throw new IllegalArgumentException();
        }
        T removed_element = this.head.getElement();

        this.head = this.head.getNext();
        this.size--;
        if (getSize() == 0){
            clear();
        }

        return removed_element;
    }

    public int getSize(){
        return this.size;
    }

    public Node<T> getHead(){
        return this.head;
    }

    public Node<T> getTail(){
        return this.tail;
    }
}
