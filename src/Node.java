public class Node<T> {
    private T element;
    private Node<T> previousNode;
    private Node<T> nextNode;

    public Node(){
        this.element = null;
        this.previousNode = null;
        this.nextNode = null;
    }

    public Node(T element){
        this.element = element;
        this.previousNode = null;
        this.nextNode = null;
    }

    public Node(T element, Node<T> previousNode){
        this.element = element;
        this.previousNode = previousNode;
    }


    public T getElement(){
        return element;
    }

    public Node<T> getPrevious(){
        return  previousNode;
    }

    public Node<T> getNext() { return nextNode; }

    public void setElement(T element){
        this.element = element;
    }

    public void setPrevious(Node<T> previousNode){
        this.previousNode = previousNode;
    }

    public void setNext(Node<T> nextNode) { this.nextNode = nextNode; }
}
