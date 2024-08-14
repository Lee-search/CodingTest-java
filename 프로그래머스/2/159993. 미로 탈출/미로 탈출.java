import java.util.*;

class Solution {
    
    int answer = 0;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int sr, sc, er, ec, lr, lc;
    
    public int solution(String[] maps) {
        
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                
                char ch = maps[i].charAt(j);
                if(ch == 'S') {
                    sr = i;
                    sc = j;
                }
                else if(ch == 'L') {
                    lr = i;
                    lc = j;
                }
                else if(ch == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }
        
        BFS(sr, sc, lr, lc, maps);    // S에서 L까지 이동
        if(answer == -1) return -1;
        BFS(lr, lc, er, ec, maps);    // L에서 E까지 이동
        if(answer == -1) return -1;
        
        return answer;
    }
    
    void BFS(int sr, int sc, int er, int ec, String[] maps) {
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!q.isEmpty()) {
            
            int r = q.peek()[0];
            int c = q.peek()[1];
            int count = q.poll()[2];
            
            // 도달했는지 확인
            if(r == er && c == ec) {
                answer += count;
                return;
            }
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 범위 바깥인지 확인
                if(nr < 0 || nr >= maps.length || nc < 0 
                   || nc >= maps[0].length()) continue;
                // 방문했는지 확인
                if(visited[nr][nc]) continue;
                // 벽인지 확인
                if(maps[nr].charAt(nc) == 'X') continue;
                
                q.offer(new int[] {nr, nc, count + 1});
                visited[nr][nc] = true;
            } // end of for
        } // end of while
        
        // 방문하지 못하고 BFS종료되면 예외처리
        answer = -1;
    } // end of BFS
}