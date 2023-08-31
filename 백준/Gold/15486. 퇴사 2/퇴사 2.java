import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        dp = new int[N + 2];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            // 해당 일에 드는 시간과 얻을 수 있는 비용
            int t = stoi(st.nextToken());
            int p = stoi(st.nextToken());

            // 입력과 동시에 dp 테이블에 삽입
            dp[i + 1] = Math.max(dp[i + 1], dp[i]); // 선택 X
            if(i + t <= N + 1) {    // 선택
                dp[i + t] = Math.max(dp[i + t], dp[i] + p);
            }
        } // end of init

        System.out.println(dp[N + 1]);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
