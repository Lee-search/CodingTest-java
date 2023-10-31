import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 내려가기 : https://www.acmicpc.net/problem/2096
 * dp[100_000][3] 배열을 통해 내려가면서 숫자 계산
 * 100_000 * 3 * 4 = 1_200_200Bytes ~= 1.2MB
 * 입력 / dpMax / dpMin 3개 = 3.6MB (문제 메모리 제한 4MB)
 */

public class Main_BJ_2096 {

    static int N;
    static int[][] plain;
    static int[][] dpMax, dpMin;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = parseInt(br.readLine());
        plain = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                plain[i][j] = parseInt(st.nextToken());
            }
        } // end of init

        // 최대, 최소 계산
        dpMax = new int[N][3];
        dpMax[0][0] = plain[0][0];
        dpMax[0][1] = plain[0][1];
        dpMax[0][2] = plain[0][2];
        dpMin = new int[N][3];
        dpMin[0][0] = plain[0][0];
        dpMin[0][1] = plain[0][1];
        dpMin[0][2] = plain[0][2];

        for(int i = 1; i < N; i++) {

            // 1. 첫번째 칸
            dpMax[i][0] = max(dpMax[i - 1][0], dpMax[i - 1][1]) + plain[i][0];
            dpMin[i][0] = min(dpMin[i - 1][0], dpMin[i - 1][1]) + plain[i][0];

            // 2. 두번째 칸
            dpMax[i][1] = max(max(dpMax[i - 1][0], dpMax[i - 1][1]), dpMax[i - 1][2]) + plain[i][1];
            dpMin[i][1] = min(min(dpMin[i - 1][0], dpMin[i - 1][1]), dpMin[i - 1][2]) + plain[i][1];

            // 3. 세번째 칸
            dpMax[i][2] = max(dpMax[i - 1][1], dpMax[i - 1][2]) + plain[i][2];
            dpMin[i][2] = min(dpMin[i - 1][1], dpMin[i - 1][2]) + plain[i][2];
        } // end of for

        sb.append(max(max(dpMax[N - 1][0], dpMax[N - 1][1]), dpMax[N - 1][2]));
        sb.append(" ");
        sb.append(min(min(dpMin[N - 1][0], dpMin[N - 1][1]), dpMin[N - 1][2]));
        System.out.println(sb.toString());

    } // end of main

/*    public static void print() {
        System.out.println("dpMax --------------------");
        for(int i = 1; i < N; i++) {
            System.out.println(Arrays.toString(dpMax[i]));
        }
        System.out.println("dpMin --------------------");
        for(int i = 1; i < N; i++) {
            System.out.println(Arrays.toString(dpMin[i]));
        }
    }*/
}
