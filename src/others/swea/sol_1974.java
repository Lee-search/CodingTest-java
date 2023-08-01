package others.swea;

// 1974. 스도쿠 검증
/* 입력
1
7 3 6 4 2 9 5 8 1
5 8 9 1 6 7 3 2 4
2 1 4 5 8 3 6 9 7
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 4 3 9

출력
#1 1
 */

import java.util.*;
public class sol_1974 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test = 1; test <= T; test++) {
            int answer = 1;
            int[][] plain = new int [9][9];
            // 입력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    plain[i][j] = sc.nextInt();
                }
            }

            // 검증
            for (int i = 0; i < 9; i++) {
                if (answer == 0) break;	// 스도쿠 성립 X
                for (int j = 0; j < 9; j++) {
                    if (answer == 0) break;	// 스도쿠 성립 X

                    // 1. 상하좌우 검증
                    int[] cols = new int [10];
                    int[] rows = new int [10];

                    for (int k = 0; k < 9; k++) {
                        // i는 그대로인 상태에서 각 열 검증
                        if (rows[plain[i][k]] != 0 || cols[plain[k][j]] != 0) {
                            answer = 0;
                            break;
                        }
                        rows[plain[i][k]] += 1;
                        // j는 그대로인 상태에서 각 행 검증
                        cols[plain[k][j]] += 1;
                    }

                    // 2. 3X3 검증
                    int[] rect = new int [10];

                    // 각 칸의 첫번째 원소애 대해서만 검증 이후 pass
                    if (i % 3 == 0 && j % 3 == 0) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (rect[plain[i + k][j + l]] != 0) {
                                    answer = 0;
                                    break;
                                }
                                rect[plain[i + k][j + l]] += 1;
                            }
                        }
                    }
                }
            }

            System.out.println("#" + test + " " + answer);
        }
    }
}
