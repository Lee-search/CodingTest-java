package w0907;

/**
 * 이중 우선순위 큐 : https://www.acmicpc.net/problem/7662
 * 처음에 PriorityQueue를 두개 사용하여 구현하였으나 시간초과
 * 추후 TreeMap 자료구조를 사용해 구현함
 * 레드블랙트리를 이용해 자료구조에 삽입 시 정렬이 이루어짐
 * 참고, https://coding-factory.tistory.com/557
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_7662 {

    static int K;
    static TreeMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            K = stoi(br.readLine());
            map = new TreeMap<>();  // Map에 숫자의 등장횟수 저장

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);  // 명령어
                int n = stoi(st.nextToken());       // 삽입 숫자

                if(c == 'I') map.put(n, map.getOrDefault(n, 0) + 1);
                else {
                    if(map.isEmpty()) continue;
                    // D: 1 -> 최대값 삭제 / D: -1 -> 최소값 삭제
                    int key = n == 1 ? map.lastKey() : map.firstKey();
                    if(map.get(key) == 1) {
                        map.remove(key);
                    } else map.put(key, map.get(key) - 1);
                }
            } // end of commands

            if(map.isEmpty()) sb.append("EMPTY\n");
            else sb.append(map.lastKey()).append(" ")
                    .append(map.firstKey()).append("\n");
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
