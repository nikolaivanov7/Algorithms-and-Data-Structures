package ListaVtorTermin;

/*Дадена е двојно поврзана листа од цели броеви. Да се трансформира листата така што ќе се меша N пати (N > 0 и се чита од стандарден влез) на следниот начин:
        Во секое мешање, последниот елемент се поставува во внатрешноста на листата после јазлите чија кумулативна вредност е максимална и помала или еднаква од вредноста
        на елементот кој се вметнува.

        Во првиот ред од влезот е даден бројот на елементи на листата M, во вториот ред бројот N и во третиот ред се дадени елементите на листата.

        Влез:

        M = 14
        N = 3
        оригинална листа: 1 <-> 5 <-> 2 <-> 3 <-> 0 <-> 6 <-> 4 <-> 3 <-> 7 <-> 9 <-> 1 <-> 4 <-> 6 <-> 8
        Мешање 1: 1 <-> 5 <-> 2 <-> 8 <-> 3 <-> 0 <-> 6 <-> 4 <-> 3 <-> 7 <-> 9 <-> 1 <-> 4 <-> 6
        Бројот 8 како последен елемент се вади од крајот на листата и се вметнува после елементот 2,
        бидејќи збирот 1+5+2 = 8 е најголем кумулативен збир од почетокот на листата, а помал и еднаков на 8

        Мешање 2: 1 <-> 5 <-> 6 <-> 2 <-> 8 <-> 3 <-> 0 <-> 6 <-> 4 <-> 3 <-> 7 <-> 9 <-> 1 <-> 4
        Бројот 6 како последен елемент се вади од крајот на листата и се вметнува после елементот 5,

        бидејќи збирот 1+5 = 6 е најголем кумулативен збир од почетокот на листата, а помал и еднаков на 6

        Мешање 3: 1  <-> 4 <-> 5 <-> 6 <-> 2 <-> 8 <-> 3 <-> 0 <-> 6 <-> 4 <-> 3 <-> 7 <-> 9 <-> 1
        Бројот 4 како последен елемент се вади од крајот на листата и се вметнува после елементот 1,
        бидејќи збирот 1 = 1 е најголем кумулативен збир од почетокот на листата, а помал и еднаков на 4


        Излез:
        1  <-> 4 <-> 5 <-> 6 <-> 2 <-> 8 <-> 3 <-> 0 <-> 6 <-> 4 <-> 3 <-> 7 <-> 9 <-> 1*/
import java.util.Scanner;

class Element {
    private int id;

    public Element(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }

}

public class Transformation {

    private static DLLNode<Element> cumulativeSpot(DLL<Element> list, int value){
        DLLNode<Element> tmp = list.getFirst();
        int sum = 0;
        while(tmp != list.getLast())
        {
            sum += tmp.element.getId();
            if(sum > value) break;
            tmp = tmp.succ;
        }
        return tmp;
    }

    private static void listTransform(DLL<Element> original, int N) {
        DLLNode<Element> last;
        while(N != 0)
        {
            last = original.getLast();
            original.insertBefore(last.element,cumulativeSpot(original,last.element.getId()));
            N--;
            original.deleteLast();
        }
        System.out.println(original.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int N = Integer.parseInt(scanner.nextLine());

        DLL<Element> list = new DLL<Element>();

        for (int i = 0; i < num; i++) {
            int n = scanner.nextInt();
            list.insertLast(new Element(n));
        }

        listTransform(list, N);
    }
}







