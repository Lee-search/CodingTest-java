import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < N; i++) {

            char[] line = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            boolean flag = false;

            for (char ch : line) {
                
                // 다른 알파벳 나오면 스택에 삽입
                if(stack.isEmpty() || stack.peek() != ch) {
                    stack.push(ch);
                }
                // 같은 단어이면 짝짓기
                else if(stack.peek() == ch) {
                    stack.pop();
                }
            }
            
            // 스택에 남은게 없어야 좋은 단어임
            if(stack.isEmpty()) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }
}
