package w0908;

/**
 * A -> B : https://www.acmicpc.net/problem/16953
 * 단순 BFS로 풀면 시간초과,
 * 그리디 알고리즘을 적용해서 최적화 후 Queue에 삽입 필요
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int A, B;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{B, 1});

        while (!q.isEmpty()) {

            int n = q.peek()[0], cnt = q.poll()[1];
//            System.out.println(n + " " + cnt);
            if(n == A) {
                System.out.println(cnt);
                return;
            }
            else if(n > A) {

                if(n % 10 == 1) q.offer(new int[] {n / 10, cnt + 1});
                else if(n %  2 == 0) q.offer(new int[] {n / 2, cnt + 1});
            }
        } // end of while

        System.out.println(-1);
    } // end of main
}
