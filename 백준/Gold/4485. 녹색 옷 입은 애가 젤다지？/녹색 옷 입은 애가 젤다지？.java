
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] plain, costArr;

    static class Node implements Comparable<Node> {
        int r, c, weight;
        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    } // end of class

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int itor = 0;

        while(true) {

            N = stoi(br.readLine());
            if(N == 0) break;

            plain = new int[N][N];    // 동굴 배열
            costArr = new int[N][N];    // 비용 배열

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    plain[i][j] = stoi(st.nextToken());
                }
                Arrays.fill(costArr[i], Integer.MAX_VALUE);
            } // end of init

            dijkstra(0, 0);
            sb.append("Problem " + ++itor + ": " + costArr[N - 1][N - 1] + "\n");
        } // end of tc

        System.out.print(sb.toString());
    } // end of main

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void dijkstra(int sr, int sc) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(sr, sc, plain[sr][sc]));
        costArr[sr][sc] = plain[sr][sc];    // 초기 가중치

        while(!pq.isEmpty()) {

            Node current = pq.poll();
            int r = current.r;
            int c = current.c;
            int cost = current.weight;

            if(r == N - 1 && c == N - 1) break;

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isPossible(nr, nc)) {
                    if(cost + plain[nr][nc] < costArr[nr][nc]) {
                        costArr[nr][nc] = cost + plain[nr][nc];
                        pq.add(new Node(nr, nc, costArr[nr][nc]));
                    }
                }
            } // end of for
        } // end of while
    } // end of dijkstra

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
