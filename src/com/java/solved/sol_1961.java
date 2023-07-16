package com.java.solved;

// 1961. 숫자 배열 회전
/* 입력
1
3
1 2 3
4 5 6
7 8 9

출력
#1
741 987 369
852 654 258
963 321 147
 */

import java.util.*;
public class sol_1961 {
    public static int[][] rotate(int[][] plain, int n){
        int[][] rotated = new int [n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - (n - j)][n - (i + 1)] = plain[i][j];
            }
        }

        return rotated;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = sc.nextInt();
            int[][] plain = new int [n][n];

            // 입력
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    plain[i][j] = sc.nextInt();
                }
            }
            // 출력할 라인
            String[] lines = new String [n];
            for (int k = 0; k < n; k++)
                lines[k] = "";

            // 회전 후 출력 값에 저장
            for (int k = 0; k < 3; k++) {
                int[][] rotated = rotate(plain, n);

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        lines[i] += Integer.toString(rotated[i][j]);
                    }
                    lines[i] += " ";
                }

                plain = rotated;
            }

            System.out.println("#" + test);
            for (int k = 0; k < n; k++)
                System.out.println(lines[k]);
        }
    }
}
