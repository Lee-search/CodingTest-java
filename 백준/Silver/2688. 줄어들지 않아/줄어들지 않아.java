import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static long[][] comb = new long[74][74];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 10 H N
        for (int i = 0; i < 74; i++) {
            comb[i][i] = 1;
            comb[i][0] = 1;
        }

        for (int i = 1; i < 74; i++) {
            for (int j = 1; j < i; j++) {
                comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
            }
        }
        int T = Integer.parseInt(br.readLine());
        int n;
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            sb.append(comb[10 + n - 1][n]).append('\n');
        }
        System.out.println(sb);
    }
}
