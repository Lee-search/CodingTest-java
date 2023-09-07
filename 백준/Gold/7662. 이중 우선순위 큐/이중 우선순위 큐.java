import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

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
