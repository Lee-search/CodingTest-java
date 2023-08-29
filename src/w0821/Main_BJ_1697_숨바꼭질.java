package w0821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1697_숨바꼭질 {

    static int N, K, answer;
    static Queue<int[]> Q;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());	// 수빈 위치
        K = Integer.parseInt(st.nextToken());	// 동생 위치
        visited = new boolean[100_001];

        Q = new ArrayDeque<>();
        Q.offer(new int[] {N, 0});
        visited[N] = true;

        while(!Q.isEmpty()) {

            int[] info = Q.poll();
            int curr = info[0], time = info[1];

            if(curr == K) {
                answer = time;
                break;
            } // 동생에게 도달한 경우

            for(int n : new int[] {curr + 1, curr - 1, curr * 2}) {
                if(0 <= n && n <= 100_000 && !visited[n]) {
                    Q.offer(new int[] {n, time + 1});
                    visited[n] = true;
                }
            }
        }

        System.out.println(answer);
    }
}
