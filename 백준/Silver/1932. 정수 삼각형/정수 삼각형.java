import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];
        for(int i = 1; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                }
                else if(j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + map[i][j];
                }
            }
        }

        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
