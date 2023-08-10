package others.baekjoon.structure;

/**
 * 최대 힙 : https://www.acmicpc.net/problem/11279
 * 최대 힙, 구현 안하고 PriorityQueue 사용법만 익힘
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_BJ_11279 {

    public static int N;
    public static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        pq  = new PriorityQueue<>(Collections.reverseOrder());  // Max-Heap

        for(int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());

            if(num == 0) { // 제거 연산
                if(pq.peek() == null) {
                    sb.append("0\n");
                }
                else {
                    sb.append(pq.poll()).append("\n");
                }
            } else pq.offer(num); // 추가 연산
        }

        System.out.println(sb);
    } // end of main
} // end of class
