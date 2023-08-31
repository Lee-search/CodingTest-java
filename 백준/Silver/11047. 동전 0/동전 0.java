import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] coins;

    /** initialize */
    public static void initial() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }

    public static int greedy() {
        int cnt = 0;
        int q, coin;
        for (int i = N - 1; i >= 0; i--) {
            coin = coins[i];
            q = K / coin;
            cnt += q;
            K -= coin * q;
            if (K == 0)
                return cnt;
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        initial();
        System.out.println(greedy());
    }
}
