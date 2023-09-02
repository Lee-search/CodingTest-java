package w0902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 맥주 마시면서 걸어가기 : https://www.acmicpc.net/problem/9205
 * Union-Find 알고리즘(서로소 집합)을 통해 풀이
 * BFS 완전탐색을 통한 풀이도 가능함.
 */

public class Main_BJ_9205 {

    static int N;
    static int[] parents;
    static Node[] nodeList;

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    } // end of innerClass

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            N = stoi(br.readLine());    // 편의점 갯수
            nodeList = new Node[N + 2]; // 정점 리스트
            make();

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int r = stoi(st.nextToken());
                int c = stoi(st.nextToken());

                nodeList[i] = new Node(r, c);
            } // end of init

            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    // Brute-force, 각 노드간 연결가능한지 확인 후 그래프 연결
                    if(i == j) continue;    // 같은 노드는 비교 X
                    Node a = nodeList[i];
                    Node b = nodeList[j];

                    // 50미터마다 맥주 1병, 맥주는 총 20병 --> 1000 이상 거리는 이동할 수 없음
                    if(getDistance(a.r, a.c, b.r, b.c) <= 1000) {
                        union(i, j);
                    }
                }
            } // end of for

            sb.append(parents[0] == parents[N + 1] ? "happy" : "sad").append("\n");
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static void make() {
        // 자기 자신을 부모노드로 설정
        parents = new int[N + 2];
        for (int i = 0; i < N + 2; i++) {
            parents[i] = i;
        }
    } // end of make

    public static int find(int a) {
        // a가 부모노드이면 a 리턴, 아니면 부모 리턴 후 경로 최적화
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    } // end of find

    public static void union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        // a와 b의 부모가 다른 경우에만 접합 및 경로최적화
        if(aRoot == bRoot) return;
        if(aRoot < bRoot) parents[bRoot] = aRoot;
        else parents[aRoot] = bRoot;
    } // end of union

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // 맨허튼 거리
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
