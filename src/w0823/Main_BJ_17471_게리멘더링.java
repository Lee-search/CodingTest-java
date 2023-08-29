package w0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_17471_게리멘더링 {

    static int N, answer;
    static int[] population;	// 마을 주민 수
    static List<Integer>[] adjList;

    // for SubSet
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        population = new int[N + 1];
        answer = Integer.MAX_VALUE; // 최소가 되는 정답 위해 최대로 초기화

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            population[i] = stoi(st.nextToken());
        }

        // 1 ~ N번 마을까지의 연결 정보 저장
        adjList = new List[N + 1];
        for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            int adjCnt = stoi(st.nextToken());  // 인접 구역의 수

            for(int j = 0; j < adjCnt; j++) {
                adjList[i].add(stoi(st.nextToken()));
            }
        } // end of init

        //for(int i = 0; i <= N; i++) System.out.println(adjList[i]);

        // 임의로 두개 선거구로 분할
        // 주의: subset은 리스트 0부터 시작
        isSelected = new boolean[N];
        SubSet(0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    } // end of main

    public static void SubSet(int cnt) {

        List<Integer> groupA = new ArrayList<>();
        List<Integer> groupB = new ArrayList<>();
        if(cnt == N) {

            for(int i = 0; i < N; i++) {
                if(isSelected[i]) groupA.add(i + 1);
                else groupB.add(i + 1);
            }

            // 한 선거구 내에는 최소 한개의 마을이 있어야함
            if(groupA.size() == 0 || groupB.size() == 0) return;

            // 같은 선거구인데 연결되지 않으면 안됨
            if(isConnected(groupA) && isConnected(groupB)) {
                // A, B 선거구의 인구수의 차이 계산
                int sumA = 0, sumB = 0;
                for(int i : groupA) sumA += population[i];
                for(int i : groupB) sumB += population[i];
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }

            return;
        } // basis

        isSelected[cnt] = false;
        SubSet(cnt + 1);
        isSelected[cnt] = true;
        SubSet(cnt + 1);
    } // end of subset


    public static boolean isConnected(List<Integer> list) {

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int count = 0; // BFS 순회하며 방문한 간선의 갯수

        q.add(list.get(0));
        visited[list.get(0)] = true;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i = 0; i < adjList[current].size(); i++) {

                int next = adjList[current].get(i);
                if(!visited[next] && list.contains(next)) {
                    q.add(next);
                    visited[next] = true;
                    count += 1;
                }
            }
        } // end of while

        // 선거구 내 마을 수와 방문한 마을 수가 같다면 true
        return count == list.size() - 1;
    } // end of BFS

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
