package w0911;

/**
 * https://www.acmicpc.net/problem/1043
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static boolean[] knows;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());   // 사람 수
        M = stoi(st.nextToken());   // 파티 수
        knows = new boolean[51];

        // 진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        int p = stoi(st.nextToken());
        for (int i = 0; i < p; i++) {
            knows[stoi(st.nextToken())] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p = stoi(st.nextToken());   // 파티 참여자 수
            int[] tmp = new int[p]; // 입력 저장
            boolean flag = false;

            for (int j = 0; j < p; j++) {
                int n = stoi(st.nextToken());
                if(knows[n]) {
                    flag = true;
                    break;
                }
                tmp[j] = n;
            }
            if (!flag) {
                answer += 1;
            }
        }

        System.out.println(answer);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
