package others.baekjoon.structure;

/**
 * 덱 : https://www.acmicpc.net/problem/10866
 * 덱 구현 문제, 이미 구현되어있는 자료구조 사용
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BJ_10866 {

    public static int N, front, back;
    public static Deque<Integer> deque = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push_front":
                	deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                	deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                	sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
                	break;
                case "pop_back":
                    sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
                    break;
                case "size":
                    sb.append(deque.size()).append("\n");
                    break;
                case "empty":
                    sb.append(deque.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
                    break;
                case "back":
                    sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
                    break;
            }
        } // end of for

        System.out.println(sb);
    } // end of main
} // end of class