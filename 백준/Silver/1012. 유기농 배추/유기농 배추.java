import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static boolean[][] maps;
    static boolean[][] visited;
    static ArrayList<int[]> baechu;
    static int cnt;

    /** initialize */
    public static void initial() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maps = new boolean[N][M];
        visited = new boolean[N][M];
        baechu = new ArrayList<>();
        int r, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            maps[r][c] = true;
            baechu.add(new int[] { r, c });
        }
        cnt = 0;
    }

    // 상하좌우
    static int[][] d = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };

    /** bfs */
    public static void bfs() {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int[] now;
        now = baechu.remove(0);
        if (visited[now[0]][now[1]])
            return;
        que.add(now);
        visited[now[0]][now[1]] = true;

        int R, C, newR, newC;
        while (!que.isEmpty()) {
            now = que.poll();
            R = now[0];
            C = now[1];
            for (int[] drc : d) {
                newR = R + drc[0];
                newC = C + drc[1];
                if (newR >= 0 && newC >= 0 && newR < N && newC < M
                        && maps[newR][newC] && !visited[newR][newC]) {
                    que.add(new int[] { newR, newC });
                    visited[newR][newC] = true;
                }
            }
        }
        cnt++;
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            initial();
            while (baechu.size() != 0)
                bfs();
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
