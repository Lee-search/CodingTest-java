
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int r, c, h, sec;
        public Pos(int r, int c, int h, int sec) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.sec = sec;
        }
    }
    static int L, R, C;
    static char[][][] building;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while(true) {
            st = new StringTokenizer(br.readLine());

            L = stoi(st.nextToken());   // 층수
            R = stoi(st.nextToken());   // 행
            C = stoi(st.nextToken());   // 열

            if(L == 0 && R == 0 && C == 0) break;

            building = new char[L][R][C];
            Pos start = null, dest = null;
            for (int k = 0; k < L; k++) {
                char[][] target = building[k];
                for (int i = 0; i < R; i++) {
                    String line = br.readLine();
                    for (int j = 0; j < C; j++) {
                        target[i][j] = line.charAt(j);
                        if (target[i][j] == 'S') {
                            start = new Pos(i, j, k, 0);
                        } else if (target[i][j] == 'E') {
                            dest = new Pos(i, j, k, 0);
                        }
                    }
                }
                br.readLine();
            } // dest of init

            BFS(start, dest);
        } // end of while

    } // end of main

    static int[] dr = {-1, 0, 1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};

    public static void BFS(Pos start, Pos dest) {

        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[L][R][C];

        q.add(start);
        visited[start.h][start.r][start.c] = true;

        while (!q.isEmpty()) {

            Pos cur = q.poll();

            if(cur.r == dest.r && cur.c == dest.c && cur.h == dest.h) {
                System.out.println("Escaped in " + cur.sec + " minute(s).");
                return;
            } // basis

            for(int d = 0; d < 6; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int nh = cur.h + dh[d];

                if(isPossible(nr, nc, nh) && !visited[nh][nr][nc] && building[nh][nr][nc] != '#') {
                    q.add(new Pos(nr, nc, nh, cur.sec + 1));
                    visited[nh][nr][nc] = true;
                }
            } // dest of for
        } // dest of while

        System.out.println("Trapped!");
    } // dest of func

    public static boolean isPossible(int r, int c, int h) {
        return 0 <= r && r < R && 0 <= c && c < C && 0 <= h && h < L;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
