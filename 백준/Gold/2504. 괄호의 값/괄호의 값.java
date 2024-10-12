import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chArr = br.readLine().toCharArray();

        Stack<Character> s1 = new Stack<>();    // 괄호 보관용
        Stack<Integer> s2 = new Stack<>();      // 숫자 보관용
        int answer = 0;

        // 현재 괄호값
        for (char ch : chArr) {

            // 열린 괄호이면 스택에 넣기
            if (ch == '[' || ch == '(') {
                s1.push(ch);
                s2.push(0);
            }
            // 닫힌 괄호이면
            else {
                // 스택의 상단과 비교해서 올바른 괄호열인지 판단
                if (s1.empty() || (ch == ']' && s1.peek() == '(') || (ch == ')' && s1.peek() == '[')) {
                    System.out.println(0);
                    return;     // 올바른 괄호열 X
                }

                // 올바른 괄호열이면, 괄호 종류에 따라 값 계산 후 스택에 반영
                s1.pop();
                int sum = s2.pop(); // 원래 안에 들어있던 값의 총합
                int total = 0;      // 새로 합산한 총합
                if (ch == ')') {
                    if (sum == 0) total += 2;
                    else total += (2 * sum);
                } else {
                    if (sum == 0) total += 3;
                    else total += (3 * sum);
                }
                // 괄호 안에있으면 다시괄호 안에 넣고, 괄호 바깥에 있으면 정답에 가산
                if (!s2.isEmpty()) {
                    s2.push(s2.pop() + total);
                } else {
                    answer += total;
                }
            }
        } // end of for

        System.out.println(answer);
    }
}