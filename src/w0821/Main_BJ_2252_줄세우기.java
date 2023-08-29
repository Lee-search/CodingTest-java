package w0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2252_줄세우기 {


    static int N, M;
    static List<Integer>[] adjList;
    static int[] degrees;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        adjList = new ArrayList[N + 1]; // 각 정점의 연결 리스트 생성
        degrees = new int[N + 1];   // 각 정점의 차수 저장

        for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            adjList[a].add(b);
            degrees[b] += 1;    // a -> b로 들어오는 차수 있음
        } // end of init

        topological();
        System.out.println(sb);
    } // end of main

    public static void topological() {

        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[N + 1];

        // 진입 노드가 없는 노드를 queue에 삽입
        for(int i = 1; i <= N; i++) {
            if(degrees[i] == 0) {
                q.offer(i);
                visited[i] = true;
            }
        }

        while(!q.isEmpty()) {

            int current = q.poll();
            sb.append(current).append(" ");
            
            // 정점 x로 들어오는 간선의 개수를 하나씩 소거
            for(int x : adjList[current]) {
                degrees[x] -= 1;
            }

            // 진입 노드가 없는 노드를 queue에 삽입 (반복)
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && degrees[i] == 0) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        } // end of while
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
