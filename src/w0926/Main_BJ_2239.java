package w0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BJ_2239 {

    static int[][] plain;
    static List<int[]> zeros;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        plain = new int[9][9];
        zeros = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            String line = br.readLine();
            for(int j = 0; j < 9; j++) {
                int n = line.charAt(j) - '0';
                if(n != 0) plain[i][j] = n;
                else zeros.add(new int[] {i, j});
            }
        } // end of init

        DFS(0);
    }

    public static void DFS(int cnt) {

        if(cnt == zeros.size()) {
            print();
            System.exit(0);
        }

        int r = zeros.get(cnt)[0], c = zeros.get(cnt)[1];
        for(int i = 1; i <= 9; i++) {
            if(check(r, c, i)) {

                plain[r][c] = i;
                DFS(cnt + 1);
                plain[r][c] = 0;
            }
        }
    }

    public static boolean check(int r, int c, int n) {

        for(int i = 0; i < 9; i++) {
            // 같은 행에 n이 하나라도 위치하면 false 리턴
            if(plain[i][c] == n) return false;

            // 같은 열에 n이 하나라도 위치하면 false 리턴
            if(plain[r][i] == n) return false;
        }

        // 3 x 3 공간에 n이 하나라도 위치하면 false 리턴
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(plain[(r / 3) * 3 + i][(c / 3) * 3 + j] == n) return false;
            }
        }
        return true;
    }

    public static void print() {

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(plain[i][j]);
            }
            System.out.println();
        }
    }
}