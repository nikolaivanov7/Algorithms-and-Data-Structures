package SumBeforeGivenPosition;

/**Дадена е еднострано поврзана листа и да се модифицира листата на тој начин што ќе се почне од првиот јазел,
 * вредноста на тој јазел се зема како вредност за локација после колку јазли треба да се вметне јазел со сумата на јазлит
 * до таа локација. Откако ќе се вметне јазел се прдодолжува од наредниот и се повторува се додека не се стигне на крај од листата.
 Влез:
 14
 2 3 4 5 2 4 5 7 5 9 2 3 4 13
 Излез:
 2 3 5 4 5 2 4 15 5 7 5 9 2 28 3 4 13
 Влез:
 15
 3 3 4 5 2 4 5 7 5 9 2 3 4 13 2
 Излез:
 3 3 4 10 5 2 4 5 7 23 5 9 2 3 4 23 13 2
 */

import java.security.UnrecoverableEntryException;
import java.util.Scanner;
import removeMiddleNode.SLLNode;
import removeMiddleNode.SLL;

public class SumBeforeGivenPosition {
    public static void sumBefore(SLL<Integer> list){
        SLLNode <Integer> node = list.getFirst();
        while(node != null)
        {
            int count = node.element;
            int suma = 0;
            while(node != null && count > 0)
            {
                suma += node.element;
                node = node.succ;
                count--;
            }
            list.insertBefore(suma,node);
        }
        System.out.println(list);
    }


    public static void main(String[] args) {
        SLL<Integer> list = new SLL<Integer>();

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());

        sumBefore(list);
        input.close();
    }
}