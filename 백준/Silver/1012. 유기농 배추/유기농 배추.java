import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M, K;
    static int[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            M = stoi(st.nextToken());
            N = stoi(st.nextToken());
            K = stoi(st.nextToken());
            plain = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = stoi(st.nextToken());
                int y = stoi(st.nextToken());
                plain[y][x] = -1;   // 배추 위치
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(plain[i][j] == -1) {
                        BFS(i, j, ++answer);
                    }
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static void BFS(int sr, int sc, int n) {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        plain[sr][sc] = n;  // 배추블럭 단위로 방문처리

        while (!q.isEmpty()) {
            
            int r = q.peek()[0];
            int c = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (plain[nr][nc] == -1) {  // 이동가능한 배추가 있는 경우

                    q.offer(new int[]{nr, nc});
                    plain[nr][nc] = n;
                }
            }
        }
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
