package others.baekjoon.data_structure;

/**
 * 스택 : https://www.acmicpc.net/problem/10828
 * 스택의 기본 개념 및 리스트 구현 이해 확인
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10828 {

    public static int N, size;
    public static int[] stack;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        stack = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "top":
                    sb.append(top()).append("\n");
                    break;
            }
        } // end of for

        System.out.println(sb);
    } // end of main

    public static void push(int num) {
        stack[size++] = num;
    }

    public static int pop() {
        if(size == 0) return -1;
        return stack[size-- - 1];
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if(size == 0) return 1;
        return 0;
    }

    public static int top() {
        if(size == 0)  return -1;
        return stack[size - 1];
    }
} // end of class