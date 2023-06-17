package LDS;
import java.util.Scanner;
public class LDS {
    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        int n = a.length;
        int lds[] = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
            lds[i] = 1;
        }
        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(a[i] < a[j] && lds[i] < lds[j] + 1)
                {
                    lds[i] = lds[j]+1;
                }
            }
        }
        for(int i = 0; i < n; i++)
        {
            if(max < lds[i])
            {
                max = lds[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}