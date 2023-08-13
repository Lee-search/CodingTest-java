package others.baekjoon.structure;

/**
 * 풍선 터뜨리기 : https://www.acmicpc.net/problem/2346
 * 앞 뒤로 이동해야하므로 deque 이용
 * 우측 이동 시, i - 1 만큼 뒤로 붙히기 (앞서 poll한 것 카운트)
 * 좌측 이동 시, i 만큼 앞으로 가져오기
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BJ_2346 {

    static int N;
    static Deque<int[]> q;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            q.offer(new int[] {i, stoi(st.nextToken())});
        } // init

        while (true) {
            int pos = q.peek()[0];    // 순번
            int val = q.poll()[1];    // 이동 값
            sb.append(pos).append(" ");

            if(q.isEmpty()) break;

            if(val > 0) {   // 양수이면, 앞의 것 빼서 뒤로 이동
                for(int i = 0; i < val - 1; i++) {
                    q.offerLast(q.pollFirst());
                }
            }
            else {  // 음수이면, 뒤의 것 빼서 앞으로 이동
                for(int i = 0; i < Math.abs(val); i++) {
                    q.offerFirst(q.pollLast());
                }
            }
        } // end of while

        System.out.println(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
