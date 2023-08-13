package others.baekjoon.brute_force;

/**
 * 링크와 스타트 : https://www.acmicpc.net/problem/15661
 * 완전탐색, 재귀를 통한 부분집합(Subset) 탐색
 * 한쪽이 0명이 되는 경우 제외한 나머지 모든 경우에 대해 합의 최소 계산
 * 집합의 시간복잡도: O(2**N), N = 20이 문제의 제한이므로 safe (2**20 = 1,048,576)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15661 {

    public static int N, answer;
    public static int[][] plain;

    public static boolean[] isSelected;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                plain[i][j] = stoi(st.nextToken());
            }
        } // end of init

        isSelected = new boolean[N];
        generateSubset(0);
        System.out.println(answer);

    } // end of main

    public static void calc_min() {

        //System.out.println("----start calc-----");
        int team_a = 0, team_b = 0;

        for(int i = 0; i < N; i++) {
            if(isSelected[i]) { // TEAM A
                for(int j = 0; j < N; j++) {
                    if(isSelected[j]) {
                        //System.out.println(String.format("A팀: (%d, %d)", i, j));
                        team_a += plain[i][j];
                    }
                }
            }
            if(!isSelected[i]) { // TEAM B
                for(int j = 0; j < N; j++) {
                    if(!isSelected[j]) {
                        //System.out.println(String.format("B팀: (%d, %d)", i, j));
                        team_b += plain[i][j];
                    }
                }
            }
        }

        answer = Math.min(answer, Math.abs(team_a - team_b));
    }

    public static void generateSubset(int cnt) {

        if(cnt == N) { // 기저조건

            int tmp = 0;
            for(int i = 0; i < N; i++) {
                if(isSelected[i]) tmp += 1;
            }

            // 어느 한쪽이 0명이 되는 경우 제거
            if(tmp == 0 || tmp == N) return;

            calc_min();  // 능력의 차가 최소가 되는 조합 구하기
            return;
        }

        isSelected[cnt] = true;
        generateSubset(cnt + 1);
        isSelected[cnt] = false;
        generateSubset(cnt + 1);
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
