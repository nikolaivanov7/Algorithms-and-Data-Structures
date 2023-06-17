package removeMiddleNode;
/**(1 Термин):
 Дадена еднострано поврзана листа, се бара N пати да се избрише средината. Ако листата е со парен број елементи од 2та средишни елементи се брише помалиот, а ако се исти се брише првиот.
 Влез: првата линија број на елементи на листата,вториот ред елементите на листата и во третиот ред број колку пати да се избрише средината
 Sample input:		Sample output:
 5					1 4 5
 1 2 3 4 5
 2
 */
import java.util.Scanner;

public class BrisiSredina {

    public static void removeMiddleNode(SLL<Integer> list , int timesDeleted){
        while(timesDeleted != 0)
        {
            SLLNode<Integer> curr = list.getFirst();
            int n = list.size();
            int counter = 1;
            while(curr != null)
            {
                if(n % 2 != 0)
                {
                    if(counter == (n/2) + 1)
                    {
                        list.delete(curr);
                    }
                }
                else {
                    if(counter == (n/2))
                    {
                        if(curr.element < curr.succ.element)
                        {
                            list.delete(curr);
                        }
                        else
                        {
                            list.delete(curr.succ);
                        }
                    }
                }
                counter++;
                curr = curr.succ;
            }
            --timesDeleted;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SLL<Integer> list = new SLL<Integer>();
        int n = input.nextInt();

        for (int i = 0; i < n; ++i)
            list.insertLast(input.nextInt());
        removeMiddleNode(list, input.nextInt());
        input.close();
    }
}
