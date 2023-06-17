package PodListi;
/** Подлисти
 Да се избришат сите подлисти на една листа во друга листа
 input:
 20
 7 6 7 6 5 1 3 7 6 7 6 7 6 5 4 7 6 7 6 5
 5
 7 6 7 6 5
 output:
 1 3 7 6 4
 */
import removeMiddleNode.SLL;
import removeMiddleNode.SLLNode;
import java.util.Scanner;
public class Podlista {

    private static void brisiPodlisata(SLL<Integer> list, SLL<Integer> podlista) {
        SLLNode<Integer> inList = list.getFirst();
        SLLNode<Integer> podList = podlista.getFirst();
        SLLNode<Integer> tmp = null;
        int first = 0 , last = 0, count = 0, broi=0;
        boolean brisi = false;
        while (inList!=null){
            if (inList.element == podList.element){
                first = count;
                last = count;
                tmp = inList;
                while (tmp!=null && podList!=null) {
                    if (podList.element != tmp.element) {
                        first = 0;
                        last = 0;
                        brisi = false;
                        break;
                    }else
                        brisi = true;
                    ++last;
                    podList = podList.succ;
                    tmp = tmp.succ;
                    broi = first;
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SLL<Integer> list = new SLL<Integer>();
        SLL<Integer> podlista = new SLL<Integer>();

        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());

        int m = input.nextInt();
        for (int i = 0; i < m; i++)
            podlista.insertLast(input.nextInt());

        brisiPodlisata(list, podlista);

        input.close();
    }
}
