package w0821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_BJ_10026_적록색약 {

    static int N, ansNomal, ansBlind;
    static char[][] plain;
    static boolean[][] vstNomal, vstBilnd;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        plain = new char[N][N];
        vstNomal = new boolean[N][N];
        vstBilnd = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                plain[i][j] = line.charAt(j);
            }
        } // end of init

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!vstNomal[i][j]) {
                    BFS(i, j, vstNomal);
                    ansNomal += 1;
                }

                if(!vstBilnd[i][j]) {
                    BFS2(i, j, vstBilnd);
                    ansBlind += 1;
                }
            }
        }

        System.out.println(ansNomal + " " + ansBlind);
    } // end of main

    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static void BFS(int sr, int sc, boolean[][] visited) {

        Queue<int[]> q = new ArrayDeque<>();
        char color = plain[sr][sc];

        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1];

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];

                if(isPossible(nr, nc) && !visited[nr][nc]) {

                    if(plain[nr][nc] == color) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

        }
    } // end of BFS

    public static void BFS2(int sr, int sc, boolean[][] visited) {

        Queue<int[]> q = new ArrayDeque<>();
        char color = plain[sr][sc];
        char color2 = '.';

        if(color == 'R') color2 = 'G';
        else if(color == 'G') color2 = 'R';

        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1];

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];

                if(isPossible(nr, nc) && !visited[nr][nc]) {

                    if(plain[nr][nc] == color || plain[nr][nc] == color2) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

        }
    } // end of BFS

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    } // end of func
}