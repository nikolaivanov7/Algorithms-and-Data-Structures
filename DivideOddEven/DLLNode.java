package DivideOddEven;

public class DLLNode<E extends Comparable <E>>{
    public E element;
    protected int numAppearances;
    public DLLNode<E> pred;
    public DLLNode<E> succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
        this.numAppearances = 1;
    }

    @Override
    public String toString() {
        return element.toString() + "[" + numAppearances + "]";
    }
}



