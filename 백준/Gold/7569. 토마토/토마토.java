import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, answer;
    static int[][][] plain;
    static boolean[][][] visited;
    static Queue<int[]> q;

    static int tomatoCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = stoi(st.nextToken());   // 가로
        N = stoi(st.nextToken());   // 세로
        H = stoi(st.nextToken());   // 높이
        plain = new int[H][N][M];   // 박스
        visited = new boolean[H][N][M];
        q = new ArrayDeque<>(); // 토마토 저장

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    plain[i][j][k] = stoi(st.nextToken());
                    if(plain[i][j][k] == 1) {
                        // 익은 토마토 만나면, 좌표 및 날짜 저장
                        q.offer(new int[] {i, j, k, 0});
                        visited[i][j][k] = true;
                    }
                    else if(plain[i][j][k] == 0) {
                        // 안익은 토마토 만나면 카운트
                        tomatoCnt += 1;
                    }
                }
            }
        } // end of init
        
        // 덜익은 토마토가 없으면 종료
        if(tomatoCnt == 0) {
            System.out.println(0);
            return;
        }

        BFS();
        System.out.println(answer);
    } // end of main

    public static int[] dh = {0, 0, 0, 0, -1, 1};
    public static int[] dr = {-1, 0, 1, 0, 0, 0};
    public static int[] dc = {0, 1, 0, -1, 0, 0};

    public static void BFS() {

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int h = info[0], r = info[1], c = info[2], day = info[3];
            if(day > answer) answer += 1;    // 날짜 갱신

            for(int d = 0; d < 6; d++) {
                int nh = h + dh[d], nr = r + dr[d], nc = c + dc[d];
                if(isPossible(nh, nr, nc)) {

                    if(plain[nh][nr][nc] == 0) {
                        tomatoCnt -= 1;
                        plain[nh][nr][nc] = 1;
                        q.offer(new int[] {nh, nr, nc, day + 1});
                    }
                }
            } // end of for
        } // end of while

        if(tomatoCnt != 0) {
            System.out.println(-1);
            System.exit(0);
        }
    } // end of BFS

    public static boolean isPossible(int h, int r, int c) {
        return (0 <= h && h < H && 0 <= r && r < N && 0 <= c && c < M) && !visited[h][r][c];
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
