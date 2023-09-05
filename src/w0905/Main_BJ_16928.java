package w0905;

/**
 * 뱀과 사다리 게임 : https://www.acmicpc.net/problem/16928
 * BFS 1차원 배열 완전탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_16928 {

    static int N, M;
    static int[] adjInfo;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());   // 사다리 갯수
        M = stoi(st.nextToken());   // 뱀 갯수

        adjInfo = new int[100 + 1];
        Arrays.fill(adjInfo, -1);

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());

            adjInfo[x] = y;
        } // end of init

        // From 1 to 100
        BFS(1);

    } // end of main

    public static void BFS(int start) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        q.offer(new int[] {start, 0});   //  처음 칸, 던진 주사위 횟수
        visited[start] = true;

        while (!q.isEmpty()) {

            int[] info = q.poll();
            int pos = info[0], cnt = info[1];

            if(pos == 100) {
                System.out.println(cnt);
                return;
            }

            for (int i = 1; i <= 6; i++) {

                int next = pos + i;
                if(next > 100 || visited[next]) continue; // 이미 방문한 곳 다시 가지 않음

                visited[next] = true;
                if(adjInfo[next] != -1) { // 사다리, 뱀 타고 이동
                    q.offer(new int[] {adjInfo[next], cnt + 1});
                    visited[adjInfo[next]] = true;
                } 
                else{ // 일반 주사위 이동
                    q.offer(new int[] {next, cnt + 1});
                }
            }
        }
    } // end of BFS

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
