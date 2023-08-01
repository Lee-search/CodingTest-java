package others.swea;

// 2072. 홀수만 더하기
/* 입력
3
3 17 1 39 8 41 2 32 99 2
22 8 5 123 7 2 63 7 3 46
6 63 2 3 58 76 21 33 8 1

출력
#1 200
#2 208
#3 121
* */

import java.util.*;
import java.io.*;

public class sol_2072 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        String[] arr;
        int n, sum;

        for (int test_case = 1; test_case <= T; test_case++) {
            sum = 0;

            for (int i = 0; i < 10; i++) {
                n = sc.nextInt();
                if (n % 2 != 0) sum += n;
            }

            System.out.println("#" + test_case + " " + sum);
        }
    }
}