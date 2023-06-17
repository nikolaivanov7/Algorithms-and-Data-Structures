package PrevrtiLista;

/** Дадена е двострано поврзана листа чии што јазли содржат по еден природен број.
 Листата треба да се преврти т.ш. прво се превртуваат јазлите кои содржат парни броеви,
 а потоа јазлите со непарни броеви. Листата се разгледува од назад.
 Право на користење имате само една дополнителна помошна двострано поврзана листа.

 Во првиот ред од влезот е даден бројот на јазли во листа,
 потоа во вториот ред се дадени броевите од кои се составени јазлите по редослед во листата.
 На излез треба да се испечатат јазлите по редослед во превртената листа.

 Забелешка: При реализација на задачите МОРА да се користат дадените структури,
 а не да се користат помошни структури како низи или сл.

 Делумно решение: Задачата се смета за делумно решена доколку се поминати 7 тест примери.

 Sample input														Sample output
 20																	0 8 6 4 2 6 4 2 2 9 7 5 3 1 1 3 1 1 1 1
 2 2 4 6 2 4 6 8 0 1 1 1 1 3 1 1 3 5 7 9

 9
 2 2 4 6 2 4 6 8 0 													2 2 4 6 2 4 6 8 0
 */

import java.util.Collections;
import java.util.Scanner;
import DivideOddEven.DLLNode;
import DivideOddEven.DLL;
public class PrevrtiLista {
    public static void reverseList(DLL<Integer> list){
        DLLNode<Integer> curr1 = list.getLast();
        DLLNode<Integer> curr2 = list.getLast();
        while(curr1 != null)
        {
            if(curr1.element % 2 == 0)
            {
                list.insertLast(curr1.element);
                list.delete(curr1);
            }
            curr1 = curr1.pred;
        }
        while (curr2 != null)
        {
            if(curr2.element % 2 != 0)
            {
                list.insertLast(curr2.element);
                list.delete(curr2);
            }
            curr2 = curr2.pred;
        }
        System.out.println(list);
    }



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        DLL<Integer> list = new DLL<Integer>();
        int n = input.nextInt();

        for (int i = 0; i < n; ++i) {
            list.insertLast(input.nextInt());
        }
        reverseList(list);
    } // END OF Main
}
