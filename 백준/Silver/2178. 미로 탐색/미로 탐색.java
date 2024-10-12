import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        plain = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                plain[i][j] = stoi(line.substring(j, j + 1));
            }
        } // init

        BFS();
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static void BFS() {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!q.isEmpty()) {

            int r = q.peek()[0], c = q.peek()[1], cnt = q.poll()[2];
            // 끝 지점에 도달, 항상 도달할 수 있는 경우만 주어지므로 추가 처리 X
            if(r == N - 1 && c == M - 1) {
                System.out.println(cnt);
                return;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(!visited[nr][nc] && plain[nr][nc] != 0) {
                    q.offer(new int[]{nr, nc, cnt + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
