package com.java.baekjoon;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14501
// 퇴사: DFS, DP(로 풀수도있음)

public class sol_14501 {

    public static int n;
    public static int[][] workList;
    public static int answer = 0;	// 최대 급여

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        workList = new int [n][2];

        for (int i = 0; i < n; i++) {
            workList[i][0] = sc.nextInt();
            workList[i][1] = sc.nextInt();
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int day, int pay) {
        if (day >= n) {
            answer = Math.max(answer, pay);
            return;
        }

        // 상담 가능 하면 일 받고
        if (day + workList[day][0] <= n) {
            dfs(day + workList[day][0], pay + workList[day][1]);
        }
        // 아니면 날짜 ++ 종료 조건 생성
        else {
            dfs(day + workList[day][0], pay);
        }

        // 그냥 날짜만 ++
        dfs(day + 1, pay);
    }
}