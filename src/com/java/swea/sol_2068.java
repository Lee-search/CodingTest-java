package com.java.swea;

// 2068. 최대수 구하기
/* 입력
3
3 17 1 39 8 41 2 32 99 2
22 8 5 123 7 2 63 7 3 46
6 63 2 3 58 76 21 33 8 1

출력
#1 99
#2 123
#3 76
* */

import java.util.*;
import java.io.*;

public class sol_2068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();	// 엔터 처리

        String[] s;
        int answer = 0;

        for (int test_case = 0; test_case < T; test_case++) {
            s = sc.nextLine().split(" ");

            for (String _s : s) {
                if (Integer.parseInt(_s) > answer) {
                    answer = Integer.parseInt(_s);
                }
            }

            System.out.println("#" + (test_case + 1) + " " + answer);
            answer = 0;
        }
    }
}
