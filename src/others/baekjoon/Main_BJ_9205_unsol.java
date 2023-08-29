package others.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9205_unsol {

    static int N;
    static boolean[][] visited;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[][] stores;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();

        int T = stoi(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            N = stoi(br.readLine());    // 편의점 갯수
            stores = new int[N][2];

            st = new StringTokenizer(br.readLine());
            start[0] = stoi(st.nextToken());
            start[1] = stoi(st.nextToken());    // 집

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                stores[i] = new int[]{stoi(st.nextToken()), stoi(st.nextToken())};
            } // 편의점

            st = new StringTokenizer(br.readLine());
            end[0] = stoi(st.nextToken());
            end[1] = stoi(st.nextToken());  // 축제장
            // end of init

            BFS();
            if(visited[end[0]][end[1]]) sb.append("happy\n");
            else sb.append("sad\n");
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static void BFS() {

        Queue<int[]> q = new ArrayDeque<>();
        // r, c, 이동 횟수, 남은 맥주
        q.offer(new int[]{start[0], start[1], 0, 20});

        visited = new boolean[end[0]][end[1]];
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1], cnt = info[2], beer = info[3];
            if(r == end[0] && c == end[1]) return;

            if(cnt % 50 == 0) {
                if(beer > 0) beer -= 1;
                else continue;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(isPossible(nr, nc)) {
                        q.offer(new int[] {nr, nc, cnt + 1, beer});
                    }
                }
            }
        } // end of while
    } // end of BFS
    public static boolean isPossible(int r, int c) {
        return (0 <= r && r < end[0] && 0 <= c && c <= end[1]) && !visited[r][c];
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
