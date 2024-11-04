import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, I;
    static int[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {

            I = stoi(br.readLine());
            plain = new int[I][I];

            st = new StringTokenizer(br.readLine());
            int sr = stoi(st.nextToken());  // 시작점
            int sc = stoi(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int er = stoi(st.nextToken());  // 끝점
            int ec = stoi(st.nextToken());

            sb.append(BFS(sr, sc, er, ec)).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int[][] dirs = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };
    static int BFS(int sr, int sc, int er, int ec) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[I][I];

        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {

            int r = q.peek()[0];
            int c = q.peek()[1];
            int count = q.poll()[2];    // 이동 횟수

            if(r == er && c == ec) return count;

            for (int d = 0; d < 8; d++) {

                int nr = r + dirs[d][0];
                int nc = c + dirs[d][1];
                if(nr < 0 || nr >= I || nc < 0 || nc >= I) continue;
                if(!visited[nr][nc]) {

                    q.offer(new int[]{nr, nc, count + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;  // 이런 경우는 주어지지 않긴함
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
