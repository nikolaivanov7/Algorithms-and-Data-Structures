package CombineNumbers;
/**Дадена е еднострана/двострана листа која содржи едноцифрени броеви и меѓу нив е и -1.
 * Да се модифицира листата на тој начин што ќе се форматираат броеви од тие цифри се додека не се стигне до бројот -1.
 * Кога ќе се стигне до тој број се прeкинува и се брише -1 и се продолжува во форматирање на броеви од наредните цифри (постапката се повторува).
 Влез:
 14
 1 2 3 -1 5 -1 6 7 4 8 9 -1 9 8
 123 5 67489 98
 19
 1 2 3 -1 5 6 -1 3 4 -1 7 4 8 9 -1 9 -1 8 -1
 Излез:
 123 56 34 7489 9 8
 */
import java.util.Scanner;
import removeMiddleNode.SLLNode;
import removeMiddleNode.SLL;

public class CombineNumbers {
    public static void changeList(SLL<Integer> list, int n){
        SLLNode<Integer> node1 = list.getFirst();
        String pom = "";
        int counter = 0;
        while(node1 != null)
        {
            if(counter == n-1)
            {
                if(node1.element == -1)
                {
                    list.insertFirst(Integer.valueOf(pom));
                    list.delete(node1);
                    break;
                }
                else
                {
                    pom += node1.element;
                    list.delete(node1);
                    list.insertFirst(Integer.valueOf(pom));
                    break;
                }
            }
            if(node1.element != -1)
            {
                pom += node1.element;
                list.delete(node1);
            }
            else {
                list.delete(node1);
                list.insertFirst(Integer.valueOf(pom));
                pom = "";
            }
            node1 = node1.succ;
            counter++;
        }
        list.mirror();
        System.out.println(list);
    }




    public static void main(String[] args) {

        SLL<Integer> list = new SLL<Integer>();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        changeList(list, n);
        input.close();
    }

}