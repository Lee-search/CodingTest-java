package others._pad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] plain = new int[19][19];
        boolean[][] visited = new boolean[19][19];

        // 입력 받기
        for(int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 19; j++) {
                plain[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 칸에 대해서 5개가 완성되어있는지 검증
        for(int r = 0; r < 19; r++) {
            for(int c = 0; c < 19; c++) {
                if(plain[r][c] == 1 || plain[r][c] == 2) {
                    int d = plain[r][c];
                    int[] sums = { 1, 1, 1, 1 };

                    // 윗쪽, 왼쪽으론 계산할 필요 없음, 이미 훑고 왔음
                    for(int i = 1; i < 6; i++) {
                        // 아래
                        if(r + 4 < 19) {
                            if(plain[r + i][c] == plain[r + i - 1][c]) {
                                sums[0] += 1;
                            }
                        }
                        // 위
                        if(r - 4 >= 0) {
                            if(plain[r - i][c] == plain[r - i + 1][c]) {
                                sums[0] += 1;
                            }
                        }

                        // 오른쪽
                        if(c + 4 < 19) {
                            if(plain[r][c + i] == plain[r][c + i - 1]) {
                                sums[1] += 1;
                            }
                        }
                        // 왼쪽
                        if(c - 4 >= 0) {
                            if(plain[r][c - i] == plain[r][c - i + 1]) {
                                sums[1] += 1;
                            }
                        }

                        // 5시 대각선
                        if(r + 4 < 19 && c + 4 < 19) {
                            if(plain[r + i][c + i] == plain[r + i - 1][c + i - 1]) {
                                sums[2] += 1;
                            }
                        }
                        // 11시 대각선
                        if(r - 4 >= 0 && c - 4 >= 0) {
                            if(plain[r - i][c - i] == plain[r - i + 1][c - i + 1]) {
                                sums[2] += 1;
                            }
                        }

                        // 1시 대각선
                        if(r - 4 >= 0 && c + 4 < 19) {
                            if(plain[r - i][c + i] == plain[r - i + 1][c + i - 1]) {
                                sums[3] += 1;
                            }
                        }
                        // 7시 대각선
                        if(r + 4 < 19 && c - 4 >= 0) {
                            if(plain[r + i][c - i] == plain[r + i - 1][c - i + 1]) {
                                sums[3] += 1;
                            }
                        }
                    }

                    // 하나라도 5개 있는지 확인
                    if(sums[0] == 5 || sums[1] == 5 || sums[2] == 5 || sums[3] == 5) {
                        System.out.println(Arrays.toString(sums));
                        System.out.println(d);
                        System.out.println((r + 1) + " " + (c + 1));
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}