package SumListTest;
/**Дадена е еднострано поврзана листа SLL чии што јазли содржат по еден природен број.
 * Да се трансформира листата така што на непарните позиции (сметајќи од лево на десно, со почеток 0) ќе се постави збирот од сите елементи кои се наоѓаат помеѓу позициите M и N.
 Влез: Во првиот ред од влезот е даден бројот на елементи во листата, а потоа самите елементи на листата секој во нов ред. По внесувањето на листата
 во посебен ред е даден бројот М, а во наредниот бројот N.
 Пример:
 Влез:
 8 //број на елементи на листата
 1 //елементите на листата ...
 2
 3
 4
 5
 6
 7
 8
 3 //бројот M
 6 //бројот N
 Излез:
 1 22 3 22 5 22 7 22
 Објаснување: M=3, N=6
 Елементите кои се наoѓаат во интервалот од 3-от до 6-от елемент се : 4, 5, 6, 7, нивниот збир е: 4 + 5+6+7 =22
 Со замена на јазлите во листата се добива 1 22 3 22 5 22 7 22
 Забелешка: При реализација на задачата не е дозволено менување на вредностите на јазлите во листата. Манипулациите се вршат врз цели јазли.
 На располагање од структурите имате само ЕДНА еднострано поврзана листа, SLL.
 Име на класа (Јава): SumList
 Sample input
 8
 1
 2
 3
 4
 5
 6
 7
 8
 3
 6
 Sample output
 1 22 3 22 5 22 7 22
 */



import java.util.Scanner;
import removeMiddleNode.SLL;
import removeMiddleNode.SLLNode;
public class SumListTest {

    public static void SumList(SLL<Integer> list, int m , int n) {
        SLLNode <Integer> curr = list.getFirst();
        int index = 0 , sum = 0;
        while(curr != null)
        {
            if(index >= m && index <= n && curr != null)
            {
                sum += curr.element;
            }
            curr = curr.succ;
            index++;
        }
        curr = list.getFirst();
        index = 0;
        while(curr != null)
        {
            if(index%2 == 1 && curr != null)
            {
                list.insertBefore(sum,curr);
                list.delete(curr);
            }
            curr = curr.succ;
            index++;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SLL<Integer> list = new SLL<Integer>();
        int size = input.nextInt();

        for (int i = 0; i < size; i++)
            list.insertLast(input.nextInt());

        int m = input.nextInt();
        int n = input.nextInt();
        SumList(list, m, n);
        input.close();
    }
}
