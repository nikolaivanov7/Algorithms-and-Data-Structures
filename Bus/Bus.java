package Bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        int minSuma = 0;
        int maxSuma = 0;
        for(i = 1; i <= N; i++)
        {
            if(M == 0 || M == 1)
            {
                minSuma = N * 100;
                maxSuma = N * 100;
                break;
            }
            if(i == 1)
            {
                minSuma = 1 * 100 + (M-1)*100;
            }
            if(N > M)
            {
                minSuma = N * 100;
            }
            if(i == N)
            {
                maxSuma = N*100 + (M-1)*100;
            }
        }
        System.out.println(minSuma);
        System.out.println(maxSuma);
    }

}
