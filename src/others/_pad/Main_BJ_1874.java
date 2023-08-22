package others._pad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_1874 {

    static int N, start;
    static Stack<Integer> stack;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = stoi(br.readLine());

        stack = new Stack<>();
        start = 1;    // 배열 삽입 변수

        for(int testCase = 0; testCase < N; testCase++) {

            int now = stoi(br.readLine());  // 현재 원하는 값

            if(now > start) {   // 원하는 값보다 현재 값이 작으면 큰갑 계속 push
                while(start <= now) {
                    stack.push(start);
                    sb.append("+\n");
                    start++;
                }
            }

            if(stack.peek() != now) {
                System.out.println("NO");
                System.exit(0);
            }  // 원하는 값이 없음

             // 원하는 값이 stack 에 들어있는 경우
            stack.pop();
            sb.append("-\n");

//            System.out.println(stack);
        } // end of tc

        System.out.print(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
