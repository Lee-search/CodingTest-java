import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] stack = new int[100_001];
        int pos = 0;

        int K = sc.nextInt();

        for(int i = 0; i < K; i++) {

            int n = sc.nextInt();
            if(n != 0) {   // push
                stack[pos++] = n;
            }
            else {  // pop
                pos--;
            }
        }

        int answer = 0;
        for(int i = 0; i < pos; i++) {
            answer += stack[i];
        }

        System.out.println(answer);
    }
}
