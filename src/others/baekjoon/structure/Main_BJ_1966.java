package others.baekjoon.structure;

/**
 * 프린터 큐 : https://www.acmicpc.net/problem/1966
 * 큐 응용, pop 한 후 조건에 맞으면 next, 그렇지 않으면 다시 queue에 삽입
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1966 {

    static int N, M;
    static List<Integer> values;
    static Queue<int[]> q;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());   // 문서 갯수
            M = stoi(st.nextToken());   // 알고자 하는 문서 위치

            values = new ArrayList<>(); // 최대값 구하기 위한 arr
            q = new ArrayDeque<>();     // 프린터 큐

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i <N; i++) {
                int value = stoi(st.nextToken());
                values.add(value);

                q.offer(new int[] {i, value});
            } // init

            values.sort(Collections.reverseOrder());

            int max = 0;    // values의 가장 큰 값 포인팅
            int count = 0;  // 인쇄 횟수

            while(!q.isEmpty()) {

                int pos = q.peek()[0];
                int val = q.poll()[1];

                if(val == values.get(max)) {
                    max += 1;
                    count += 1;
                    if(pos == M) {
                        // pop 한 값이 알고자 하는 값
                        sb.append(count).append("\n");
                        break;
                    }
                } else q.offer(new int[] {pos, val});
            }
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
