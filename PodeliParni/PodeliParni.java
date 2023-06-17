package PodeliParni;
/**Подели според парност
 Дадена е двострано поврзана листа чии што јазли содржат по еден природен број. Листата треба да се подели на две резултантни листи, т.ш. во првата резултанта листа ќе бидат бидат сместени јазли од влезната листа кои содржат парни броеви, а во втората – непарните.
 Јазлите во резултантните листи се додаваат наизменично почнувајќи од почетокот и крајот на влезната листа (т.е. прво се разгледува првиот елемент од листата и се додава во соодветната резултантна листа, па последниот, па вториот итн...)
 Во првиот ред од влезот е даден бројот на јазли во листата, а во вториот ред се дадени броевите од кои се составени јазлите по редослед во листата.
 Во првиот ред од излезот треба да се испечатат јазлите по редослед од првата резултантна листа (т.е. парните), во вториот ред од втората (т.е. непарните) .
 Име на класа (за Java): PodeliSporedParnost
 Забелешка: При реализација на задачите МОРА да се користат дадените структури, а не да користат помошни структури како низи или сл.
 Sample input
 5
 1 2 3 4 5
 Sample output
 2 4
 1 5 3
 Sample input
 6
 7 9 12 13 16 18
 Sample output
 18 16 12
 7 9 13
 */
import java.util.Scanner;
import DivideOddEven.DLL;
import DivideOddEven.DLLNode;

public class PodeliParni{
    public static void PodeliParni(DLL<Integer> list,int n){
        DLLNode <Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();
        DLL<Integer> parni = new DLL<Integer>();
        DLL<Integer> neparni = new DLL<Integer>();
        while (first != last)
        {
            if(first.element % 2 == 0)
            {
                parni.insertLast(first.element);
            }
            else
            {
                neparni.insertLast(first.element);
            }
            first = first.succ;
            if(last.element % 2 == 0)
            {
                parni.insertLast(last.element);
            }
            else
            {
                neparni.insertLast(last.element);
            }
            last = last.pred;
            if(first == last.succ)
            {
                break;
            }
        }
        if(n%2 == 1) {
            if (first.element % 2 == 0) {
                parni.insertLast(first.element);
            } else {
                neparni.insertLast(first.element);
            }
        }
        System.out.println(parni);
        System.out.println(neparni);
    }


    public static void main(String[] args) {
        DLL<Integer> list = new DLL<Integer>();

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());

        PodeliParni(list, n);
        input.close();
    }

}