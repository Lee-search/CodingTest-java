package w1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

/**
 * 계란으로 계란치기 : https://www.acmicpc.net/problem/16987
 * 백트래킹, 주어진 로직을 이해하지 못해서 오래걸림
 * 계란이 깨질 떄 까지 X, 한번 부딫힌 후 다음 계란으로 이동
 */

public class Main_BJ_16987 {
    static class Egg {
        int s, w;
        public Egg(int s, int w) {
            this.s = s; // 내구도
            this.w = w; // 무게
        }
    } // end of class
    static int N, answer;
    static Egg[] eggs;
    static int[] origin;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = parseInt(br.readLine());
        eggs = new Egg[N];
        origin = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());
            eggs[i] = new Egg(s, w);
            origin[i] = s;
        } // end of init

        collision(0, 0);
        System.out.println(answer);
    } // end of main

    public static void collision(int left, int cnt) {

        if(left == N) {
            answer = max(answer, cnt);
            return;
        } // basis

        if (eggs[left].s <= 0 || cnt == N - 1) {
            collision(left + 1, cnt);
            return;
        }

        for (int right = 0; right < N; right++) {

            // 같은 것 끼리 부딫하는 경우 or 이미 깨진 계란인 경우 pass
            if(left == right || eggs[right].s <= 0) continue;

            eggs[left].s -= eggs[right].w;
            eggs[right].s -= eggs[left].w;

            int tmp = 0;
            if (eggs[left].s <= 0) {
                tmp += 1;
            }
            if (eggs[right].s <= 0) {
                tmp += 1;
            }

            collision(left + 1, cnt + tmp);
            eggs[left].s += eggs[right].w;
            eggs[right].s += eggs[left].w;
        } // end of for
    } // end of func
}