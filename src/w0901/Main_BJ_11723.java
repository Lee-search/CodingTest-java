package w0901;

/**
 * 집합 : https://www.acmicpc.net/problem/11723
 * 집합 자료구조와 Hashset 라이브러리를 이용한 단순 구현 예제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BJ_11723 {

    static int N;
    static HashSet<Integer> hset;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        hset = new HashSet<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "add":
                    hset.add(stoi(st.nextToken()));
                    break;
                case "remove":
                    hset.remove(stoi(st.nextToken()));
                    break;
                case "check":
                    if(hset.contains(stoi(st.nextToken()))) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "toggle":
                    int x = stoi(st.nextToken());
                    if(hset.contains(x)) hset.remove(x);
                    else hset.add(x);
                    break;
                case "all":
                    for(int j = 1; j <= 20; j++) hset.add(j);
                    break;
                case "empty":
                    hset.clear();
                    break;
            } // end of switch
        } // end of for

        System.out.println(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}