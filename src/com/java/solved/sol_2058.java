package com.java.solved;

// 2058. 자릿수 더하기

/* 입력
6789
출력
30
 */

import java.util.*;
import java.io.*;

public class sol_2058 {
    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        while (n > 0) {
            answer += n % 10;
            n = n / 10;
        }

        System.out.println(answer);
    }
}
