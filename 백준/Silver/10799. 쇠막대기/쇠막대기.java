import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chArray = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for (int i = 0; i < chArray.length; i++) {

            char ch = chArray[i];
            // 열리는 괄호면 스택에 추가
            if(ch == '(') {
                stack.add(ch);
            }
            // 닫히는 괄호이면,
            else {
                stack.pop();
                // 직전 괄호가 ( 이면 -> 레이저
                if(chArray[i - 1] == '(') {
                    // 스택에 남아있는 파이프 만큼 잘린 조각 수 추가
                    answer += stack.size();
                }
                // 직전 괄호가 ) 이면 -> 파이프 끊김
                else {
                    // 끊어진 파이프 갯수 추가
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
    }
}
