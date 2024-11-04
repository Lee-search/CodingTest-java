import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        // dp[i][0]: i를 1로 만드는데 걸린 가장 작은 횟수
        // dp[i][1]: 연산하기 직전의 수
        dp = new int[N + 1][2];
        for(int i = 2; i <= N; i++) dp[i][0] = Integer.MAX_VALUE;
//        dp[1][0] = 0;

        for(int i = 2; i <= N; i++) {

            if(i % 3 == 0) {
                // 나누어지면, i/3 을 만드는데 필요한 횟수 + 1로 i를 만들 수 있음
                int tmp = dp[i / 3][0] + 1;
                if(dp[i][0] > tmp) {    // 기존보다 더 작은 횟수로 i를 만들 수 있는 경우
                    dp[i][0] = tmp;
                    dp[i][1] = i / 3;
                }
            }
            if(i % 2 == 0) {
                int tmp = dp[i / 2][0] + 1;
                if(dp[i][0] > tmp) {
                    dp[i][0] = tmp;
                    dp[i][1] = i / 2;
                }
            }
            int tmp = dp[i - 1][0] + 1;
            if(dp[i][0] > tmp) {
                dp[i][0] = tmp;
                dp[i][1] = i - 1;
            }
        }

//        for(int i = 1; i <= N; i++)
//            System.out.println(i + "= " + dp[i][0] + "," + dp[i][1]);

        sb.append(dp[N][0]).append("\n");

        int i = N;
        while (i >= 1) {

            sb.append(i).append(" ");
            i = dp[i][1];
        }

        System.out.println(sb.toString());
    }
}
