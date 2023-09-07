package w0906;

/**
 * 이중 우선순위 큐: https://www.acmicpc.net/problem/7662
 * PriorityQueue 두개와 HashMap 이용하였으나 시간초과
 * remove시 원소 전체에 대해 순차탐색을 하여 오래걸리는듯함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_7662_unsol {

    static int K;
    static PriorityQueue<Integer> pqMax, pqMin;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            K = stoi(br.readLine());
            pqMax = new PriorityQueue<>(Collections.reverseOrder());
            pqMin = new PriorityQueue<>();
            map = new HashMap<>();  // Map에 숫자의 등장횟수 저장

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                if(st.nextToken().equals("I")) {

                    int n = stoi(st.nextToken());
                    pqMax.offer(n);
                    pqMin.offer(n);
                    // MAP에 이미 키가 있으면 등장 횟수 추가
                    if(map.containsKey(n)) map.put(n, map.get(n) + 1);
                    else map.put(n, 1);
                }
                else { // D: 최대 1, 최소 -1 삭제
                    int n = stoi(st.nextToken());

//                    System.out.println("D " + n);

                    if(pqMax.isEmpty() || pqMin.isEmpty()) continue;
                    if(n == 1) {
                        remove(pqMax.poll(), pqMin);
                    }
                    else {
                        remove(pqMin.poll(), pqMax);
                    }

//                    System.out.println("pqMax: " + pqMax);
//                    System.out.println("pqMin: " + pqMin);
//                    System.out.println(map);
                }
            } // end of commands

            if(pqMin.isEmpty() || pqMax.isEmpty()) {
                sb.append("EMPTY\n");
            }
            else {
                sb.append(getValue(pqMax)).append(" ").append(getValue(pqMin)).append("\n");
            }
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static int getValue(PriorityQueue<Integer> pq) {

        int num = 0;
        while (true) {
            num = pq.poll();

            if(!map.containsKey(num) || map.get(num) == 0) continue;

            if(map.get(num) == 1) map.remove(num);
            else map.put(num, map.get(num) - 1);
            break;
        }
        return num;
    } // end of func

    public static void remove(int num, PriorityQueue<Integer> pq) {

        // 한번도 나오지 않은 수는 아무 작업도 하지 않음
        if(!map.containsKey(num) || map.get(num) == 0) return;
        // 삭제 작업
        map.put(num, map.get(num) - 1);
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
