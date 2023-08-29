package w0810;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {

    public static int N, answer;
    public static int[][] plain;
    public static boolean[] isSelected;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream(new File("./src/w0810/input_1.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {

            N = stoi(br.readLine());
            plain = new int[N][N];
            isSelected = new boolean[N];
            answer = Integer.MAX_VALUE; // 시너지 차이의 최소값
            
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    plain[i][j] = stoi(st.nextToken());
                }
            } // 시너지 배열 입력

            combination(0, 0);
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static void calc() {
        int sum_a = 0;
        int sum_b = 0;  // a,b 음식의 시너지합

        // (i, j) : 음식의 재료
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 첫번째 음식에만 selected 배열 사용
                if(isSelected[i] && isSelected[j]) {
                    sum_a += plain[i][j] + plain[j][i];
                }
                else if(!isSelected[i] && !isSelected[j]) {
                    sum_b += plain[i][j] + plain[j][i];
                }
            }
        }

        answer = Math.min(answer, Math.abs(sum_a - sum_b) / 2);
    }

    public static void combination(int cnt, int start) {

        if(cnt == N / 2) { // 기저조건
            calc(); // 시너지합의 최소 구하기
            return;
        }

        for(int i = start; i < N; i++) {
            if(isSelected[i]) continue;

            isSelected[i] = true;
            combination(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
