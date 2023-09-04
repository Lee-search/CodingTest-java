import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] fibo = new int[41];
        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i < 41; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }

        int N = Integer.parseInt(br.readLine());
        int t;
        for (int i = 0; i < N; i++) {
            t = Integer.parseInt(br.readLine());
            if (t == 0 || t == 1)
                sb.append(1 - t).append(' ').append(t).append('\n');
            else
                sb.append(fibo[t - 2]).append(' ').append(fibo[t - 1]).append('\n');
        }
        System.out.println(sb);
    }

}
