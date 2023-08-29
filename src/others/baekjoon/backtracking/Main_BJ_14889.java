package others.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14889 {

    static int N, answer;
    static int[][] plain;
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                if(i == j) st.nextToken();  // 자기 자신이면 어짜피 0이니 버림
                else plain[i][j] = stoi(st.nextToken());
            }
        } // end of init

        isSelected = new boolean[N];
        combination(0, 0);
        System.out.println(answer);
    } // end of main

    public static void combination(int cnt, int start) {

        if(cnt == N / 2) {

            int teamS = 0, teamL = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    // 조합에서 같은 팀이면
                    if(isSelected[i] == isSelected[j] && isSelected[i]) {
                        teamS += plain[i][j];
                    }
                    else if(isSelected[i] == isSelected[j] && !isSelected[i]) {
                        teamL += plain[i][j];
                    }
                }
            } // end of for

            answer = Math.min(answer, Math.abs(teamL - teamS));
            return;
        } // basis

        for(int i = start; i < N; i++) {

            isSelected[i] = true;
            combination(cnt + 1, i + 1);
            isSelected[i] = false;
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
