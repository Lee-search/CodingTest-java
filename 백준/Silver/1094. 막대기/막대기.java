import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int[] dp = new int[X + 1]; // 막대를 접합해서 i cm를 만드는 막대수의 최소값

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 길이 0을 만들기 위해 필요한 막대 수는 0개

        int[] sticks = {1, 2, 4, 8, 16, 32, 64};

        for (int i = 1; i <= X; i++) {
            for (int stick : sticks) {
                if (i - stick >= 0 && dp[i - stick] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - stick] + 1);
                }
            }
        }

        System.out.println(dp[X]);
    }
}
