package DivideOddEven;

/** Дадена е двојно поврзана листа и со N јазли кои во себе содржат по еден природен број. Треба да се подели листата на две резултантни листи. Во првата резултантна листа треба да се преместат јазлите со непарни броеви, а во втората со парните.
 Во првиот ред од влезот е даден бројот на јазли во листата, а потоа во вториот ред се дадени јазлите од кои е составена. На излез треба да се испечатат во еден ред јазлите на првата листа со непарните броеви, а во втор ред јазлите на втората листа со парните броеви.
 Име на класата (Java): DivideOddEven
 Sample input			Sample output
 3						4 8 6
 4 8 6
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DivideOddEven {

    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }
        divideOddEven(lista);
    }

    private static void divideOddEven(DLL<Integer> list) {
        DLLNode <Integer> first = list.getFirst();
        DLL<Integer> parni = new DLL<Integer>();
        while(first != null)
        {
            if(first.element % 2 == 0)
            {
                parni.insertLast(first.element);
                list.delete(first);
            }
            first = first.succ;
        }
        System.out.println(parni);
        System.out.println(list);
    }
}