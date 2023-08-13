import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[][] plain;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        plain = new int[19][19];

        for(int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 19; j++) {
                plain[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of init

        for(int r = 0; r < 19; r++) {
            for(int c = 0; c < 19; c++) {
                if(plain[r][c] == 1 || plain[r][c] == 2) {
                    BFS(r, c, plain[r][c]);
                }
            }
        }

        System.out.println(0);

    } // end of main

    // vectors -> 1시, 3시, 5시, 6시
    public static int[] dr = {-1, 0, 1, 1};
    public static int[] dc = {1, 1, 1, 0};

    public static void BFS(int sr, int sc, int color) {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc, 1, 0});
        q.offer(new int[] {sr, sc, 1, 1});
        q.offer(new int[] {sr, sc, 1, 2});
        q.offer(new int[] {sr, sc, 1, 3});

        while (!q.isEmpty()) {

            int[] info = q.poll();
            // cnt: 같은 알이 나온 갯수, direct: 해당 경우에 이동한 방향
            int r = info[0], c = info[1], cnt = info[2], direct = info[3];
            //System.out.println(r + " " + c + " " + cnt);

            // 오목 나온 경우, 검증 및 6목 예외 처리
            if(cnt == 5) {
                // 시작 칸의 한칸 앞으로 가서 6목인지 확인
                if(isPossible(sr - dr[direct], sc - dc[direct]) && plain[sr - dr[direct]][sc - dc[direct]] == color) continue;
                // 마지막 칸의 한칸 뒤로 가서 6목인지 확인
                if(isPossible(r + dr[direct], c + dc[direct]) && plain[r + dr[direct]][c + dc[direct]] == color) continue;

                System.out.println(color);
                System.out.println((sr + 1) + " " + (sc + 1));
                System.exit(0);
            }

            int nr = r + dr[direct];
            int nc = c + dc[direct];

            if(!isPossible(nr, nc)) continue;
            if(plain[nr][nc] == color) {
                q.offer(new int[] {nr, nc, cnt + 1, direct});
            }

        }
    } // end of bfs

    public static boolean isPossible(int r, int c) {
        return (0 <= r && r < 19 && 0 <= c && c < 19);
    }
}