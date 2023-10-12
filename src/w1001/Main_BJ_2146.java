package w1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2146 {

    static int N;
    static int[][] plain;
    static boolean[][] visited;
    static List<List<int[]>> sideList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = stoi(st.nextToken());
                if(n != 0) plain[i][j] = n;
            }
        } // end of init

        sideList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문하지 않은 위치에 대해 BFS 탐색
                if(plain[i][j] == 1 && !visited[i][j]) BFS(i, j);
            }
        } // end of for

        for (int i = 0; i < sideList.size(); i++) {
            for (int j = 0; j < sideList.get(i).size(); j++) {
                System.out.print(Arrays.toString(sideList.get(i).get(j)) + " ");
            }
            System.out.println();
        }

    } // end of main

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void BFS(int sr, int sc) {

        List<int[]> list = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int r = q.peek()[0];
            int c = q.poll()[1];
//            System.out.println(r + " " + c);

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(!isPossible(nr, nc) || visited[nr][nc]) continue;
                // 바다에 닿았으면 리스트에 외곽부분 삽입
                if(plain[nr][nc] == 0) {
                    list.add(new int[]{r, c});
                }
                // 바다에 닿지 않았으면 BFS 탐색
                else if(plain[nr][nc] == 1) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        } // end of while

        sideList.add(list);
    } // end of BFS

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
