package w0803;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_2023 {

    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 자릿수
        dfs(0, N);
        System.out.println(sb);
    }

    // num: 소수인지 판별할 수, n: 자릿수
    public static void dfs(int num, int n) {
        if(n == 0) {
            sb.append(num).append("\n");
        }
        for(int i = 1; i < 10; i++) {   // 0으로 끝나면 어짜피 소수 아님
            int tmp = 10 * num + i;
            if(n > 0 && isPrime(tmp)) {
                dfs(tmp, n - 1);
            }
        }
    }

    public static boolean isPrime(int num) {
        if(num < 2) return false;
        // 에라토스테네스의 채
        for(int i = 2 ; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
