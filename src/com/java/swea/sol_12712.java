package com.java.swea;

// 12712. 파리퇴치3
/* 입력
1
5 2
1 3 3 6 7
8 13 9 12 8
4 16 11 12 6
2 4 1 23 2
9 13 4 7 3

출력
#1 64
 */

import java.util.*;

public class sol_12712 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = sc.nextInt();	// 영역
            int m = sc.nextInt();	// 분사력
            int answer = 0;

            int[][] plain = new int [n][n];
            // 배열 채우기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    plain[i][j] = sc.nextInt();
                }
            }

            // 배열 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int sum_plus = plain[i][j];
                    int sum_x = plain[i][j];

                    for (int k = 1; k < m; k++) {
                        // 1. + 탐색
                        // 1. 위
                        if (i - k >= 0)
                            sum_plus += plain[i - k][j];
                        // 2. 아래
                        if (i + k < n)
                            sum_plus += plain[i + k][j];
                        // 3. 좌
                        if (j - k >= 0)
                            sum_plus += plain[i][j - k];
                        // 4. 우
                        if (j + k < n)
                            sum_plus += plain[i][j + k];

                        // 2. X 탐색
                        // 1. 11시
                        if (i - k >= 0 && j - k >= 0)
                            sum_x += plain[i - k][j - k];
                        // 2. 1시
                        if (i - k >= 0 && j + k < n)
                            sum_x += plain[i - k][j + k];
                        // 3. 5시
                        if (i + k < n && j + k < n)
                            sum_x += plain[i + k][j + k];
                        // 4. 7시
                        if (i + k < n && j - k >= 0)
                            sum_x += plain[i + k][j - k];
                    }

                    if (sum_x > answer) answer = sum_x;
                    if (sum_plus > answer) answer = sum_plus;
                }
            }

            System.out.println("#" + test + " " + answer);
        }
    }
}
