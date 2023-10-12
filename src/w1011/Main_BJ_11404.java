package w1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 플로이드 : https://www.acmicpc.net/problem/11404
 플로이드 워셜 알고리즘
 */
public class Main_BJ_11404 {

    static final int INF = 100_000_000;
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());    // 도시의 수
        M = stoi(br.readLine());    // 버스의 수
        dist = new int[N][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());   // 시작도시
            int b = stoi(st.nextToken());   // 도착도시
            int c = stoi(st.nextToken());   // 비용
            
            // 중복 버스 노선중에서 더 싼 노선 채택
            if(dist[a - 1][b - 1] != 0)
                dist[a - 1][b - 1] = Math.min(dist[a - 1][b - 1], c);
            else
                dist[a - 1][b - 1] = c;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j)  // 자기자신으로 가는 비용은 0
                    dist[i][j] = 0;
                else if(dist[i][j] == 0)    // 경로가 없는 경우 임의의 큰 값 처리
                    dist[i][j] = INF;
            }
        }

        // 플로이드 워셜 수행
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                if(i == k) continue;
                for(int j = 0; j < N; j++) {
                    if(i == j || j == k) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 갈수 없는 경우는 0으로 출력
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(dist[i][j] == INF)
                    dist[i][j] = 0;
            }
        }

        print();
    } // end of main

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    } // end of print

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
