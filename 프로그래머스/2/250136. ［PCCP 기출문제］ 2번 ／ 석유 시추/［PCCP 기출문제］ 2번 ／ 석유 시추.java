import java.util.*;

class Solution {
    
    private int N, M, answer, marker;
    private boolean[][] visited;
    private Map<Integer, Integer> cntMap;
    
    public int solution(int[][] land) {
        
        N = land.length;    // 세로길이
        M = land[0].length; // 가로길이
        answer = 0;         // 초기정답
        marker = 1;         // 블럭번호 마킹
        cntMap = new HashMap<>();
        visited = new boolean[N][M];
        
        // 유전을 블럭 단위로 번호 매핑
        for(int c = 0; c < M; c++) {
            for(int r = 0; r < N; r++) {
                // (r, c)에 석유가 있고, 탐색이 안된 지역인 경우
                if(land[r][c] > 0 && !visited[r][c]) {
                    BFS(r, c, land);
                    marker += 1;
                }
            }
        } // end of for
        
        // 각각의 위치에서 내려갔을 때 캘 수 있는 석유 탐색
        for(int c = 0; c < M; c++) {
            
            Set<Integer> set = new HashSet<>();
            int total = 0;  // 시추한 석유 양
            
            for(int r = 0; r < N; r++) {
                set.add(land[r][c]);
            }
            
            for(int block : set) {
                if(block == 0) continue;
                total += cntMap.get(block);
            }
            
            answer = Math.max(answer, total);
        }
        
        return answer;
    } 
    
    private int[] dr = {0, 1, 0, -1};
    private int[] dc = {1, 0, -1, 0};
    private void BFS(int sr, int sc, int[][] land) {
        
        int count = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;
        land[sr][sc] = marker;  // 지도에 번호 마킹
        
        while(!q.isEmpty()) {
            
            int r = q.peek()[0], c = q.poll()[1];
            if(land[r][c] > 0) count += 1;
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(0 > nr || nr >= N || 0 > nc || nc >= M) continue;
                if(land[nr][nc] == 0 || visited[nr][nc]) continue;
                
                q.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
                land[nr][nc] = marker;
            }
        } // end of while
        
        cntMap.put(marker, count);
    }
}