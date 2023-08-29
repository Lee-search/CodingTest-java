package others.baekjoon.SOLVED;

// https://www.acmicpc.net/problem/15686
// 치킨 배달: DFS 백트래킹으로 해결,
// 치킨집 m개를 백트래킹으로 선택한 후 일반집.size 만큼 완전탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_15686 {
    static int n, m;
    static List<int[]> h_list;
    static List<int[]> c_list;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        h_list = new ArrayList<>();
        c_list = new ArrayList<>();

        // 0: 빈칸, 1: 집, 2: 치킨
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int loc = Integer.parseInt(st.nextToken());
                // 집, 치킨집 각각 추가
                if (loc == 1) h_list.add(new int[] { i, j });
                else if (loc == 2) c_list.add(new int[] { i, j });
            }
        }
        // 치킨집 갯수만큼 방문확인 배열 추가
        visited = new boolean[c_list.size()];
        answer = Integer.MAX_VALUE;
        DFS(0, 0);
        System.out.println(answer);
        br.close();
    }

    public static void DFS(int start, int count) {
        // 치킨집 m개 임의 선택 완료되었다면,
        if (count == m) {
            int sum = 0;
            // 모든 집과 해당 치킨집 사이의 멘하탄 거리 합 계산
            for (int i = 0; i < h_list.size(); i++) {
                int tmp = Integer.MAX_VALUE;
                for (int j = 0; j < c_list.size(); j++) {
                    if (visited[j]) {
                        int dist = Math.abs(h_list.get(i)[0] - c_list.get(j)[0]) + Math.abs(h_list.get(i)[1] - c_list.get(j)[1]);
                        tmp = Math.min(tmp, dist);
                    }
                }
                sum += tmp;
            }
            answer = Math.min(answer, sum);
            return;
        }
        
        // 백트래킹으로 combination 구현
        for (int k = start; k < c_list.size(); k++) {
            visited[k] = true;
            DFS(k + 1, count + 1);
            visited[k] = false;
        }
    }
}
