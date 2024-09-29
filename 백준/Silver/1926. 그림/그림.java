import java.io.*;
import java.util.*;

public class Main {

    static int N, M, count, biggist;
    static int[][] plain;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = stoi(st.nextToken());   // 세로, R
        M = stoi(st.nextToken());   // 가로, C
        plain = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = stoi(st.nextToken());
                if(plain[i][j] != n) plain[i][j] = n;
            }
        } // end of init

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(plain[i][j] == 1 && !visited[i][j]) {
                    count += 1;     // 새로운 그림 발견
                    biggist = Math.max(biggist, BFS(i, j));      // 그림의 크기 책정
                }
            }
        }

        sb.append(count).append("\n").append(biggist);
        System.out.println(sb);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int BFS(int sr, int sc) {

        int size = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            
            size += 1;  // 그림 블럭 하나 탐색
            int r = q.peek()[0];
            int c = q.poll()[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || plain[nr][nc] == 0) continue;

                q.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        return size;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}