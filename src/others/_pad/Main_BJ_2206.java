package others._pad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2206 {

    static int N, M, answer;
    static int[][] plain;
    static boolean[][][] visited;
    static int[] dr = {-1 , 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());   // 행
        M = stoi(st.nextToken());   // 열
        plain = new int[N][M];
        visited = new boolean[N][M][2];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                plain[i][j] = line.charAt(j)  - '0';
            }
        } // end of input

//        for(int i = 0; i < N; i++) System.out.println(Arrays.toString(plain[i]));

        BFS();
        // 도착 못하고 BFS 종료된 경우 -1
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);

    } // end of main

    public static void BFS() {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0, 0});   // R, C, 벽 부쉈는지 여부
        visited[0][0][0] = true;    // 벽 부수지 않고 스타트

        while (!q.isEmpty()) {

            int[] info = q.poll();
            // r, c,
            // isBroke:벽 부수기 사용 여부 (0: 사용안함, 1: 사용), cnt: 이동 횟수
            int r = info[0], c = info[1], isBroke = info[2], cnt = info[3];
            
            // r, c가 도착지점이면 값 비교
            if(r == N - 1 && c == M - 1) {

                answer = Math.min(answer, cnt + 1);
                continue;   // 비교 후 이동 X
            }


            for(int d = 0; d < 4; d++ ){

                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isPossible(nr, nc, isBroke) && plain[nr][nc] == 0) { // 그냥 지나갈 수 있으면
                    q.offer(new int[] {nr, nc, isBroke, cnt + 1});
                    visited[nr][nc][isBroke] = true;
                }
                else if(isPossible(nr, nc, isBroke) && plain[nr][nc] == 1){ // 벽에 막혔으면
                    if(isBroke == 0) { // 벽을 최대 한번 부수고 이동 가능
                        plain[nr][nc] = 0;
                        q.offer(new int[] {nr, nc, 1, cnt + 1});
                        visited[nr][nc][1] = true;
                    }
                }
            }
        } // end of while
    } // end of func

    public static boolean isPossible(int r, int c, int isBroke) {
        return (0 <= r && r < N && 0 <= c && c < M) && !visited[r][c][isBroke];
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
} // end of class
