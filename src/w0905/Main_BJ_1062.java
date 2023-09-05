package w0905;

/**
 * 가르침 : https://www.acmicpc.net/problem/1062
 * 브루트포스, 조합, HashSet 라이브러리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1062 {

    static int N, K, answer;
    static boolean[] isSelected;
    static HashSet<Character> usedAlpha;
    static List<Character>[] words;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        isSelected = new boolean[26];
        usedAlpha = new HashSet<>();

        words = new List[N];
        for (int i = 0; i < N; i++) words[i] = new ArrayList<>();
        
        // 필수 단어를 배울 수 없는 경우
        if(K < 5) {
            System.out.println(0);
            return;
        }

        K -= 5; // a, c, i, n, t 미리 선택
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 4; j < line.length() - 4; j++) {
                char ch = line.charAt(j);
                if(ch == 'a' || ch == 'c' || ch == 'i' || ch == 'n' || ch == 't') continue;
                words[i].add(ch);
                usedAlpha.add(ch);
            }
        }

        // K가 충분, 필요한 모든 단어를 다 배울 수 있는 경우
        if(usedAlpha.size() <= K) {
            System.out.println(N);
            return;
        }

        combination('a', K);
        System.out.println(answer);
    } // end of main

    public static void combination(char start, int k) {

        if(k == 0) {
            // 단어 전체 돌면서 만들 수 있는 단어이면 count += 1
            answer = Math.max(answer, count());
            return;
        } // basis

        for(char ch = start; ch <= 'z'; ch++) {
            // 선택할 필요 없는 캐릭터 pass
            if(!usedAlpha.contains(ch) || isSelected[ch - 'a']) continue;

            isSelected[ch - 'a'] = true;
            combination((char) (ch + 1), k - 1);
            isSelected[ch - 'a']= false;
        }
    } // end of combination

    public static int count() {

        int count = N;
        for (List<Character> word : words) {
            // 해당 단어의 한 캐릭터가 selected 되어있는지 확인
            for(char ch : word) {
                if(!isSelected[ch - 'a']) {
                    count -= 1;
                    break;
                }
            }
        } // end of for

        return count;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
