package w0825;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_하나로_unsol {

    static class Node implements Comparable<Node> {

        int pos;    // 섬 위치
        long distance;  // 섬까지 거리

        public Node(int pos, long distance) {
            this.pos = pos;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    } // end of class

    static int N;
    static int[][] islands;

    static double E;
    static long answer;
    static final long INF = Long.MAX_VALUE;

    static long[] dist;
    static boolean[] visited;


    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream(new File("./src/w0825/input_2.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());
        for(int testCase = 1; testCase <= 3; testCase++) {

            N = stoi(br.readLine());    // 섬의 개수
            islands = new int[N][2];    // 섬 리스트 초기화
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) islands[i][0] = stoi(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) islands[j][1] = stoi(st.nextToken());
            E = Double.parseDouble(br.readLine());  // 세율
            // end of init

            dijkstra();
            System.out.println(Math.round(answer * E));
        }
    } // end of main

    public static void dijkstra() {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N];
        dist = new long[N];  // 0 ~ N - 1 번째 섬의 최소거리
        Arrays.fill(dist, INF);

        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {


            Node cur = pq.poll();
            System.out.println(Arrays.toString(dist));

            if(visited[cur.pos]) continue;
            visited[cur.pos] = true;
            dist[cur.pos] = cur.distance;
//            answer += dist[cur.pos];

            for(int i = 0; i < N; i++) {

                // 이어지는 섬들에 대해 최소거리 탐색 후 pq에 삽입
                long tmp = getDist(cur.pos, i);
                if(dist[i] > cur.distance + tmp) {
                    pq.offer(new Node(i, dist[cur.pos] + tmp));
                }
            }
        } // end of while

    } // end of func

    public static long getDist(int a, int b) {
        return (long) Math.sqrt(Math.pow(Math.abs(islands[a][0] - islands[b][0]), 2) + Math.pow(Math.abs(islands[a][1] - islands[b][1]), 2));
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
