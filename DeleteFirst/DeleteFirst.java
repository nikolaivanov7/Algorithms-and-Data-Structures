package DeleteFirst;

/**Дадена е еднострано поврзана листа, ако во листата има два непарни елементи еден до друг или два парни еден до друг да се избрише првиот елемент
 Sample input:
 10
 1 2 3 5 7 4 6 12 9 11
 Sample output:
 1->2->5->7->6->12->11->
 Sample input:
 10
 1 2 4 3 5 6 8 7 9 10
 Sample output:
 1->4->5->8->9->10->
 */

//CODE

import java.util.Scanner;
import removeMiddleNode.SLLNode;
import removeMiddleNode.SLL;

public class DeleteFirst {
    public static void DeleteFirstElement(SLL<Integer> list){
        SLLNode <Integer> curr = list.getFirst();
        while(curr.succ != null)
        {
            if(curr.element % 2 == 0 && curr.succ.element % 2 == 0)
            {
                list.delete(curr);
            }
            else if(curr.element % 2 != 0 && curr.succ.element % 2 != 0)
            {
                list.delete(curr);
            }
            curr = curr.succ;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        SLL<Integer> list = new SLL<Integer>();
        int n = input.nextInt();

        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());

        DeleteFirstElement(list);

        input.close();
    }
}
