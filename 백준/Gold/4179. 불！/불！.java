import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] plain;
    static int[][] fireMap;
    static Queue<int[]> jq, fq;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        plain = new char[R][C];
        fireMap = new int[R][C];    // 불이 언제 도달하는지 저장
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireMap[i], -1);    // 아직 도달하지 않은 곳은 -1
        }

        jq = new ArrayDeque<>();    // 지훈이 초기 위치 저장
        fq = new ArrayDeque<>();    // 불의 초기 위치 저장
        int sr = -1, sc = -1;

        for (int i = 0; i < R; i++) {

            String line = br.readLine();
            for (int j = 0; j < C; j++) {

                char ch = plain[i][j] = line.charAt(j);
                if(ch == 'J') {
                    jq.offer(new int[]{i, j, 0});
                    sr = i; sc = j;
                }
                else if(ch == 'F') {
                    fq.offer(new int[]{i, j});
                    fireMap[i][j] = 0;  // 초기 불 위치
                }
            }
        } // init

        // 불 먼저 BFS
        fire();
        
        // 지훈이 BFS
        move(sr, sc);

    } // end of main

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static void fire() {

        while (!fq.isEmpty()) {

            int r = fq.peek()[0], c = fq.poll()[1];

            // 불 이동
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if(fireMap[nr][nc] == -1 && plain[nr][nc] != '#') {

                    fireMap[nr][nc] = fireMap[r][c] + 1;    // 한 턴 뒤에 도달
                    fq.offer(new int[]{nr, nc});
                }
            }
        }
    } // end of fire

    static void move(int sr, int sc) {

        boolean[][] visited = new boolean[R][C];
        visited[sr][sc] = true;

        while (!jq.isEmpty()) {

            int r = jq.peek()[0], c = jq.peek()[1], count = jq.poll()[2];
            
            // 가장자리 도달했는지 확인
            if(r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                System.out.println(count + 1);
                return;
            }
            
            // 지훈이 이동
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if(visited[nr][nc] || plain[nr][nc] == '#') continue;
                // 불이 도달하기 전일때만 해당 칸으로 이동 가능
                if(count + 1 < fireMap[nr][nc] || fireMap[nr][nc] == -1) {

                    jq.offer(new int[]{nr, nc, count + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        // 여기까지 왔다는건 지훈이 불에 타죽었다는 뜻
        System.out.println("IMPOSSIBLE");
    }
}
