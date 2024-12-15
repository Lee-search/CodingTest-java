import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int C, R, answer;
    static char[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        plain = new char[R][C];

        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                plain[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(plain[i][j] == 'L') {
                    answer = Math.max(answer, BFS(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int BFS(int sr, int sc) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        int maxCnt = 0;

        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {

            int r = q.peek()[0];
            int c = q.peek()[1];
            int cnt = q.poll()[2];
            maxCnt = Math.max(cnt, maxCnt);

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C
                        || visited[nr][nc] || plain[nr][nc] != 'L') continue;

                q.offer(new int[]{nr, nc, cnt + 1});
                visited[nr][nc] = true;
            }
        }

        return maxCnt;
    }
}
