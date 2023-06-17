package juniTermin2;

/**(2 термин): Дадена е еднострано поврзана листа од цели броеви.
 Треба да се изврши бришење на тој начин што прво се остава еден па се брише следниот, па се оставаат два, па се брише следниот, па три итн.
 се додека е возможно бришење.
 Ако листата е празна на излез да се испечати Prazna lista.
 Влез: број на елементи во листата, самата листа
 Излез: листа со избришани јазли
 Не смее да се користат помошни структури како низи и сл. и не смее да се менуваат вредностите на јазлите.
 Пример:
 Влез: 								Излез:
 7									1 9 5 3 0
 7
 */
import java.util.Scanner;
import removeMiddleNode.SLL;
import removeMiddleNode.SLLNode;

public class JuniTermin2{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SLL<Integer> list = new SLL<Integer>();
        int n = input.nextInt();

        for (int i = 0; i < n; ++i) {
            list.insertLast(input.nextInt());
        }

        if (n > 0) {
            removeNode(list);
        } else {
            System.out.println("Prazna lista");
        }
        input.close();
    }

    private static void removeNode(SLL<Integer> list) {
        SLLNode <Integer> curr = list.getFirst();
        int counter = 0, pom = 1;
        while(curr != null)
        {
            if(counter == pom)
            {
                list.delete(curr);
                counter = 0;
                pom++;
                curr = curr.succ;
            }
            else
            {
                counter++;
                curr = curr.succ;
            }
        }
        System.out.println(list);
    }
}
