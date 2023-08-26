import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D, sum, answer;
    static int[][] plain;
    static int[][] origin;
    static boolean[] isSelected;
    static Queue<int[]> deadQueue;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());   // 행
        M = stoi(st.nextToken());   // 열
        D = stoi(st.nextToken());   // 공격 거리 제한
        plain = new int[N][M];
        origin = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int n = stoi(st.nextToken());
                if(n != 0) {
                    plain[i][j] = n;
                    origin[i][j] = n;
                }
            }
        } // end of init

        isSelected = new boolean[M];
        combination(0, 0);
        System.out.println(answer);
    } // end of main

    public static void combination(int cnt, int start) {

        if(cnt == 3) {
            fight();
            restore();  // 싸우기 전 상태로 복구
            return;
        } // basis

        for(int i = start; i < M; i++) {

            isSelected[i] = true;
            combination(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    } // end of func

    public static void fight() {

        sum = 0;
        deadQueue = new ArrayDeque<>();

        // 궁수가 위치한 행 변경
        for(int h = N - 1; h >= 0; h--) {
            // 궁수 한칸 위부터 탐색 시작
            for(int i = 0; i < M; i++) {
                if(isSelected[i]) {
                    BFS(h, i);
                }
            }

            // 3번의 BFS 종료 이후, plain에서 적 삭제
            while(!deadQueue.isEmpty()) {

                int[] info = deadQueue.poll();
                // 이미 다른 궁수에 의해 적이 죽었음
                if(plain[info[0]][info[1]] == 0) continue;

                plain[info[0]][info[1]] = 0;
                sum += 1;
            }
        }

        answer = Math.max(answer, sum);
    }

    public static int[] dr = {0, -1, 0};
    public static int[] dc = {-1, 0, 1};    // 좌, 상, 우
    public static Queue<int[]> q;
    public static boolean[][] visited;

    // BFS실행 시, 무조건 첫 단추는 궁수 한칸 위로 지정!!
    public static void BFS(int sr, int sc) {

        q = new ArrayDeque<>();
        visited = new boolean[N][M];
        q.offer(new int[] {sr, sc, 1});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1], dist = info[2];
            
            // 공격 가능한 범위롤 초과하는 적이면 pass
            if(dist > D) continue;

            // 이동한 칸에 적이 있으면 죽이고 종료
            if(plain[r][c] == 1) {
                // 같은 적이 여러 궁수에게 공격당할 수 있음
                deadQueue.offer(new int[] {r, c});
                return;
            }
            
            for(int d = 0; d < 3; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];
                // 공격 가능한 거리에 있음을 검증
                if(isPossible(nr, nc)) {
                    q.offer(new int[] {nr, nc, dist + 1});
                    visited[nr][nc] = true;
                }
            } // end of for
        } // end of while
    } // end of BFS

    public static void restore() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(plain[i][j] != origin[i][j]) plain[i][j] = origin[i][j];
            }
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi

    public static boolean isPossible(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M) && !visited[r][c];
    } // end of func
}
