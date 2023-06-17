package ChangeList;
/**
 За дадена листа од А0 до Аn да се испечати како резултат А0->Аn-1->А1->Аn-2->А2->...
 Влез:
 5
 1 2 3 4 5
 Излез:
 1->5->2->4->3
 Забелешка:
 Не смеат да се креираат нови листи
 Работа со јазлите
 */
import java.util.Scanner;
import removeMiddleNode.SLLNode;
import removeMiddleNode.SLL;

public class ChangeList {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SLL<Integer> list = new SLL<Integer>();

        int n = input.nextInt();

        for (int i = 0; i < n; i++)
            list.insertLast(input.nextInt());

        changeList(list);
        input.close();
    }
    public static void changeList(SLL<Integer> list){
        SLLNode<Integer> curr = list.getFirst();
        SLLNode<Integer> next;
        while(curr.succ != null)
        {
            next = curr.succ;
            while (next.succ != null)
            {
                next = next.succ;
            }
            curr = curr.succ;
            list.insertBefore(next.element,curr);
            list.delete(next);
        }
        System.out.println(list);
    }
}
