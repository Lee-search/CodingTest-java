package w0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BJ_1927 {

    static int N;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int n = stoi(br.readLine());
            if (n == 0) {
                if (pq.isEmpty()) sb.append("0\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                pq.offer(n);
            }
        }

        System.out.println(sb);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
