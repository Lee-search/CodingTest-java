package w0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1629 {

    static long A, B, C;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = stol(st.nextToken());   // A를
        B = stol(st.nextToken());   // B번 곱하고
        C = stol(st.nextToken());   // C로 나누시오

        System.out.println(calc(A, B));
    } // end of main

    public static long calc(long a, long b) {

        if(b == 1) return a % C; // end of basis

        long half = calc(a, b / 2);
        if(b % 2 == 1)          // 홀수
            return (half * half % C) * (a % C);
        return half * half % C; // 짝수
    } // end of func

    public static long stol(String s) {
        return Long.parseLong(s);
    } // end of stol
}
