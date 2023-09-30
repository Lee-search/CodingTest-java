package w0930;

/**
 * 불 : https://www.acmicpc.net/problem/5427
 * BFS, 틱 단위로 끊어서 이동하기 위해 queue의 size만큼 반복문 사용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_5427 {

    static int R, C;
    static char[][] plain;

    static Queue<int[]> fire;
    static Queue<int[]> mover;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = stoi(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            C = stoi(st.nextToken());
            R = stoi(st.nextToken());
            plain = new char[R][C];
            fire = new ArrayDeque<>();
            mover = new ArrayDeque<>();

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    plain[i][j] = line.charAt(j);
                    if(plain[i][j] == '@') {
                        mover.offer(new int[] {i, j, 0});
                    } else if(plain[i][j] == '*') {
                        fire.offer(new int[] {i, j});
                    }
                }
            } // end of init

            BFS();
        } // end of tc

        System.out.println(sb.toString());
    } // end of main

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void BFS() {

        while(!mover.isEmpty()) {

//            print();

            // 1. 상근이 이동
            int mvLen = mover.size();
            for (int i = 0; i < mvLen; i++) {

                int[] info = mover.poll();
                int r = info[0], c = info[1], cnt = info[2];
                
                // 불에 닿아서 상근이 사망
                if(plain[r][c] == '*') continue;

                // 가장자리에 도달하면 out!
                if(r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                    sb.append(cnt + 1 + "\n");
                    return;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(isPossible(nr, nc) && plain[nr][nc] == '.') {
                        plain[nr][nc] = '@';
                        mover.offer(new int[] {nr, nc, cnt + 1});
                    }
                }
            } // end of for

            // 2. 불 이동
            int frLen = fire.size();
            for (int i = 0; i < frLen; i++) {

                int[] info = fire.poll();
                int r = info[0], c = info[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(isPossible(nr, nc) && (plain[nr][nc] == '.' || plain[nr][nc] == '@')) {
                        plain[nr][nc] = '*';
                        fire.offer(new int[] {nr, nc});
                    }
                }
            } // end of for
        } // end of while

        sb.append("IMPOSSIBLE\n");
    } // end of func

    public static void print() {
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(plain[i]));
        }
        System.out.println();
    }

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
