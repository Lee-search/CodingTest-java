import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        
        plain = new int[N][M];
        for (int i = 0; i < K; i++) {

            st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());

            for (int r = x1; r < x2; r++) {
                for (int c = y1; c < y2; c++) {
                    if(plain[r][c] != 1) plain[r][c] = 1;   // 직사각형에 포함되는 영역 표시
                }
            }
        }

        int answer = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(plain[i][j] == 0) {
                    answer += 1;
                    list.add(BFS(i, j));
                }
            }
        }

        Collections.sort(list);
        sb.append(answer).append("\n");
        for (int n : list) {
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int BFS(int sr, int sc) {

        Queue<int[]> q = new ArrayDeque<>();
        int count = 1;
        q.offer(new int[]{sr, sc});
        plain[sr][sc] = 1;

        while (!q.isEmpty()) {

            int r = q.peek()[0];
            int c = q.poll()[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(plain[nr][nc] == 0) {
                    plain[nr][nc] = 1;
                    count += 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return count;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
