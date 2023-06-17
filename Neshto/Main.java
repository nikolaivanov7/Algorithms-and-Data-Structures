package Neshto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n,m;
        Scanner scanner= new Scanner(System.in);
        DLL<Integer> lista1=new DLL<>(), lista2=new DLL<>();

         n = scanner.nextInt();
         m= scanner.nextInt();

         for(int i=0;i<n;i++){
             lista1.insertLast(scanner.nextInt());
         }
        for(int i=0;i<m;i++){
            lista2.insertLast(scanner.nextInt());
        }

        sort(lista1, lista2);
    }

    private static void sort(DLL<Integer> lista1, DLL<Integer> lista2) {
        DLLNode<Integer> jazol1=lista1.getLast();
        DLLNode<Integer> jazol2= lista2.getFirst();

        while(jazol1!=null) {
            if (jazol1.element > jazol2.element) {
                lista2.insertBefore(jazol1.element, jazol2);
                jazol1 = jazol1.pred;
            } else {
                while (jazol2!=null && jazol1.element < jazol2.element) {
                    jazol2 = jazol2.succ;
                }
                lista2.insertBefore(jazol1.element, jazol2);
                jazol1 = jazol1.pred;
            }
            jazol2=lista2.getFirst();
        }
        lista1.deleteList();
        while(jazol2!=null){
            lista1.insertFirst(jazol2.element);
            jazol2=jazol2.succ;
        }
        System.out.println(lista2);
        System.out.println(lista1);
    }


}
