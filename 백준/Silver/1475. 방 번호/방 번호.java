import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        int[] arr = new int[9];
        for(int i = 0; i < num.length(); i++) {
            int n = Integer.parseInt(num.substring(i, i + 1));
            if(n == 9) arr[6] += 1;
            else arr[n] += 1;
        }

        int answer = 1;
        for(int i = 0; i < 9; i++) {
            if(i == 6) {
                answer = Math.max(answer, Math.round((float) arr[i] / 2));
            }
            else {
                answer = Math.max(answer, arr[i]);
            }
        }

        System.out.println(answer);
    }
}
