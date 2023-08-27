import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from, to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int V, E, answer;
    static Edge[] edgeList;
    static int[] parents;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        edgeList = new Edge[E];
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            edgeList[i] = new Edge(a, b, c);
        } // end of init

        Arrays.sort(edgeList);

        kruskal();
        System.out.println(answer);
    } // end of main

    public static void kruskal() {

        int cnt = 0; // 트리에 포함한 엣지 수
        make();

        // 가중치가 가장 적은 리스트부터 트리에 추가
        for(Edge edge : edgeList) {
            // V개의 정점을 잇는 V-1개의 간선이 선택된 경우 break
            if(cnt == V - 1) break;

            int aRoot = find(edge.from);
            int bRoot = find(edge.to);
            // 같은 부모를 가지면 사이클 생성, continue
            if(aRoot != bRoot) {
                union(edge.from, edge.to);
                answer += edge.weight;
                cnt += 1;
            }
        }
    } // end of func

    public static void make() {

        parents = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    } // end of make

    public static int find(int a) {

        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    } // end of find

    public static boolean union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        if(aRoot < bRoot) parents[bRoot] = aRoot;
        else parents[aRoot] = bRoot;

        return true;
    } // end of union

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
