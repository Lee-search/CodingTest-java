import java.util.*;

public class Main {

    static int N, unused;
    static Stack<Integer> stack;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        N = sc.nextInt();
        stack = new Stack<>();
        unused = 1; // 스택에 넣을 수 있는 수 (아직 넣지 않은 수)

        for(int i = 0; i < N; i++) {

            int n = sc.nextInt();
            // 스택의 꼭대기가 n이면 pop
            if(!stack.empty() && stack.peek() == n) {
                stack.pop();
                sb.append("-\n");
            }
            // 아닌 경우
            else {
                // unused가 n보다 작거나 같은지 확인 -> 만들 수 있음
                if(unused <= n) {
                    // n까지 push
                    for(int j = unused; j <= n; j++) {
                        stack.push(j);
                        unused++;

                        sb.append("+\n");
                    }
                    // 마지막 n을 pop
                    stack.pop();
                    sb.append("-\n");
                }
                // 아닌경우 (이미 n이 스택에 들어감)
                else {
                    boolean isIn = false;
                    // 스택확인 후 없으면 NO 출력
                    while(!stack.isEmpty()) {

                        int top = stack.pop();
                        sb.append("-\n");
                        // 찾으면 pop 중단
                        if(top == n) {
                            isIn = true;
                            break;
                        }
                    }
                    // stack 다뒤졌는데도 목표하는 n이 안나오면 NO 출력
                    if(!isIn) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        } // end of for

        System.out.println(sb.toString());
    }
}
