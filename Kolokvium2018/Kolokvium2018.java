package Kolokvium2018;
import removeMiddleNode.SLLNode;
import removeMiddleNode.SLL;
import java.util.Scanner;
/**
 * Парни разменливи елементи на дадена поврзана листа
 * Со оглед на листата со поединечно поврзана вредност, напишете функција за да ги замените елементите.
 * На пример, ако поврзаната листа е 1-> 2-> 3-> 4-> 5 тогаш функцијата треба да ја смени на 2-> 1-> 4-> 3-> 5,
 * и ако поврзаната листа е  1-> 2-> 3-> 4-> 5-> 6 тогаш функцијата треба да ја промени на 2-> 1-> 4-> 3-> 6-> 5.
 */
public class Kolokvium2018 {
    private static void swap(SLL<Integer> list, int n) {
        SLLNode<Integer> node1 = list.getFirst();
        SLLNode<Integer> node2 = node1.succ;
        if(n%2 == 0) {
            while (n > 1) {
                list.insertLast(node2.element);
                list.delete(node2);
                list.insertLast(node1.element);
                list.delete(node1);

                node1 = node2.succ;
                node2 = node2.succ.succ;
                n -= 2;
            }
        }
        else
        {
            while(n>1)
            {
                list.insertLast(node2.element);
                list.delete(node2);
                list.insertLast(node1.element);
                list.delete(node1);
                node1 = node2.succ;
                --n;
                if(n<1)
                    break;
                node2 = node2.succ.succ;
                --n;
            }
            list.insertLast(node1.element);
            list.delete(node1);
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SLL<Integer> list = new SLL<Integer>();
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }
        swap(list, n);
        input.close();
    }
}
