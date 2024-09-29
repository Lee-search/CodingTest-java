import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        int x = Integer.parseInt(br.readLine());
        for(int a : set) {
            if(set.contains(x - a)) {
                answer += 1;
            }
        }

        System.out.println(answer / 2);
    }
}
