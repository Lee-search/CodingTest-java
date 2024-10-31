import java.util.*;

class Solution {
    
    int INF = Integer.MAX_VALUE;
    int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] dp = new int[m + 1][n + 1];
        
        dp[1][1] = 1;
        for(int[] p : puddles) {
            dp[p[0]][p[1]] = INF;
        }
        
        // 상,좌 가장자리 이동 경우의 수는 반드시 하나
        // 단, 가는길에 물웅덩이 없어야함
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                
                if(dp[i][j] == INF) continue;
                if(dp[i - 1][j] != INF) dp[i][j] += dp[i - 1][j] % MOD;
                if(dp[i][j - 1] != INF) dp[i][j] += dp[i][j - 1] % MOD;
            }
        }
        
        // for(int i = 0; i <= m; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        
        return dp[m][n] % MOD;
    }
}