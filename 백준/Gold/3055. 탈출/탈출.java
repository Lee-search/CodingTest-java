
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] plain;

    static Queue<int[]> water;
    static Queue<int[]> dochi;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        plain = new char[R][C];
        water = new ArrayDeque<>();
        dochi = new ArrayDeque<>();

        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                plain[i][j] = line.charAt(j);
                if(plain[i][j] == 'S') dochi.offer(new int[] {i, j, 0});
                else if(plain[i][j] == '*') water.offer(new int[] {i, j});
            }
        } // end of init

        BFS();
        System.out.println("KAKTUS");

    } // end of main

    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static void BFS() {

        while(!dochi.isEmpty()) {
            // BFS 트리 높이만큼만 반복
            int len = dochi.size();
            for(int i = 0; i < len; i++) {
                
                int[] info = dochi.poll();
                int r = info[0], c = info[1], cnt = info[2];

                // 고슴도치 죽었으면 continue
                if(plain[r][c] != 'S') continue;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 고슴도치 이동 가능한 지역 확인
                    if(isPossible(nr, nc) && plain[nr][nc] != '*' && plain[nr][nc] != 'S') {

                        if(plain[nr][nc] == 'D') {
                            System.out.println(cnt + 1);
                            System.exit(0);
                        } // basis

                        plain[nr][nc] = 'S';
                        dochi.offer(new int[] {nr, nc, cnt + 1});
                    }
                } // end of for
            } // end of dochi-for

            len = water.size();
            for(int i = 0; i < len; i++) {

                int[] info = water.poll();
                int r = info[0], c = info[1];

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 물이 이동가능한 지역 확인
                    if (isPossible(nr, nc) && plain[nr][nc] != 'D' && plain[nr][nc] != '*') {
                        plain[nr][nc] = '*';
                        water.offer(new int[] {nr, nc});
                    }
                } // end of for
            } // end of water-for

        } // end of while
    } // end of func

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C  && plain[r][c] != 'X';
    } // end of func

}
