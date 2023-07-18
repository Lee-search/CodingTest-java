package com.java.swea;

// 1959. 두 개의 숫자열
/* 입력
1
3 5
1 5 3
3 6 -7 5 4
출력
#1 30
 */

import java.util.*;

public class sol_1959 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] A = new int[n];
            int[] B = new int[m];

            for (int i = 0; i < n; i++)A[i] = sc.nextInt();
            for (int j = 0; j < m; j++) B[j] = sc.nextInt();

            int answer = 0;
            int sum;

            if (n > m) {
                for (int i = 0; i <= n - m; i++){
                    sum = 0;
                    for (int j = 0; j < m; j++) {
                        sum += A[j + i] * B[j];
                    }

                    answer = Math.max(answer, sum);
                }
            }
            else if (n < m) {
                for (int i = 0; i <= m - n; i++){
                    sum = 0;
                    for (int j = 0; j < n; j++) {
                        sum += A[j] * B[j + i];
                    }

                    answer = Math.max(answer, sum);
                }
            }
            else {
                sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += A[j] * B[j];
                }

                answer = Math.max(answer, sum);
            }

            System.out.println("#" + test + " " + answer);
        }
    }
}