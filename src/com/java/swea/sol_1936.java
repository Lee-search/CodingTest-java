package com.java.swea;

// 1936. 1대1 가위바위보
/* 입력
3 2
출력
A
 */

import java.util.*;
import java.io.*;
public class sol_1936 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
        // 가위: 1, 바위: 2, 보: 3
        if ((a == 1 && b == 3) || (a == 2 && b == 1) || (a == 3 && b == 2))
            System.out.println("A");
        else
            System.out.println("B");
    }
}
