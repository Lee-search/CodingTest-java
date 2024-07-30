import java.util.*;

class Solution {
    
    static final int INF = 100_000_000;
    int answer = Integer.MAX_VALUE;
    int[][] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        graph = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }
        
        for(int[] info : fares) {
            int from = info[0], to = info[1], cost = info[2];
            
            graph[from][to] = cost;
            graph[to][from] = cost;
        }
        
        // 경유지
        for(int k = 1; k <= n; k++) {
            // 출발지
            for(int i = 1; i <= n; i++) {
                // 도착지
                for(int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        
        // for(int i = 1; i <= n; i++) {
        //     System.out.println(Arrays.toString(graph[i]));
        // }
        
        // 어느 하나를 경유해서 a, b로 가는 모든 경우 계산
        for(int k = 1; k <= n; k++) {
            answer = Math.min(answer, graph[s][k] + graph[k][a] + graph[k][b]);
        }
        
        return answer;
    }
}