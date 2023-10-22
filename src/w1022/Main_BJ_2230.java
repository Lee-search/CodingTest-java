package w1022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수 고르기 : https://www.acmicpc.net/problem/2230
 * 투 포인터 문제,
 * left 고정, right += 1, n <= M 인 n을 찾았으면
 * 해당 n보다 더 작은 수를 찾을 수 있는지만을 고민 (left += 1)
 * 굳이 이중 for문을 통해 전체 케이스를 다 탐색할 필요 없음
 */

public class Main_BJ_2230 {

    static int N, M, answer;
    static int[] nArray;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        answer = Integer.MAX_VALUE;
        nArray = new int[N];
        for (int i = 0; i < N; i++) {
            nArray[i] = stoi(br.readLine());
        }

        Arrays.sort(nArray);

        // Solve01. 1912ms
//        here: for (int left = 0; left < N; left++) {
//            for (int right = left; right < N; right++) {
//                int n = nArray[right] - nArray[left];
//                if(n == M) {
//                    answer = M;
//                    break here;
//                }
//                else if (n > M) {
//                    answer = Math.min(answer, n);
//                    break;
//                }
//            }
//        } // end of for

        // Solve02. 296ms
        int left = 0, right = 0;
        while(left <= right && right < N) {
            int n = nArray[right] - nArray[left];
            if(n >= M) {
                answer = Math.min(answer, n);
                left += 1;
            }
            else right += 1;
        }

        System.out.println(answer);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
