package w1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2110
 */

public class Main_BJ_2110 {

    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        divide(0, N - 1);
    } // end of main

    public static void divide(int start, int end) {

        int mid = (houses[end] - houses[start]) / 2;

        if(C <= 0) {
            System.out.println(mid);
            return;
        } // basis

        for(int i = start; i < end; i++) {
            // 현재 설치한 공유기 사이의 최대거리를 벗어나는지 확인
            if(houses[i + 1] - houses[i] >= mid) {
                C -= 2;
                divide(start, i);
                divide(i + 1, end);
                break;
            }
        }
    }
}
