import java.io.*;
import java.util.*;

public class Solution {

    static int N, S, answer;
    static List<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        //System.setIn(new FileInputStream(new File("./src/w0823/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for(int testCase= 1; testCase <= 10; testCase++) {

            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());   // 데이터 개수
            S = stoi(st.nextToken());   // 시직점

            // 1번부터 N번까지
            adjList = new List[101];
            visited = new boolean[101];

            for(int i = 0; i <= 100; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N / 2; i++) {
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                adjList[from].add(to);
            } // end of init

            //for(int i = 0; i < 101; i++) System.out.println(Arrays.toString(callList));

            BFS(S);

            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        } // end of tc

        System.out.print(sb);
    } // end of main

    public static void BFS(int start) {

        List<Integer> curList = adjList[start];
        Queue<int[]> q = new ArrayDeque<>();

        int curDepth = 0;  // 현재까지의 연락 깊이 카운트

        q.offer(new int[] {start, 0});
        visited[start] = true;

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int current = info[0];  // 현재 노드
            int depth = info[1];    // 현재까지의 깊이

            // 깊이가 증가했으면, 최대값 갱신
            if(depth > curDepth) {
                // 시작 시 연락받은 가장 큰 값은 자기 자신
                answer = current;
                curDepth = depth;
            }
            else {
                answer = Math.max(answer, current);
            }

            curList = adjList[current];
            for(int i = 0; i < curList.size(); i++) {

                int next = curList.get(i);
                if(!visited[next]) {
                    q.offer(new int[] {next, depth + 1});
                    visited[next] = true;
                }
            } // end of offer
        } // end of while
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
