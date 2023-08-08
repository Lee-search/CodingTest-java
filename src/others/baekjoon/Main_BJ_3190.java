package others.baekjoon;

// https://www.acmicpc.net/problem/3190
// 뱀: 구현, 시뮬레이션, 큐


import java.util.*;

public class Main_BJ_3190 {
    private static final String ArrayList = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* INPUT */
        // n: 보드 사이즈 / k: 사과 갯수
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] plain = new int [n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            // 사과 위치 받아서 저장
            int r = sc.nextInt(), c = sc.nextInt();
            plain[r][c] = 1;
        }

        int l = sc.nextInt();
        char[] rotate = new char [10000];

        // n초 뒤 L / D 로 90도 회전
        for (int i = 0; i < l; i++) {
            int sec = sc.nextInt();
            char c = sc.next().charAt(0);

            rotate[sec] = c;
        }

        /* MAIN */
        // 뱀 기본좌표
        int r = 1, c = 1, d = 1;
        plain[r][c] = -1;
        // 현재 꼬리 위치 저장
        List<int[]> snake = new ArrayList<>();
        snake.add(new int[] {r, c});

        // 상, 우, 하, 좌
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};


        // 1초에 한칸 씩 움직임
        for (int sec = 1; sec <= 10000; sec++) {

            int nr = r + dr[d];
            int nc = c + dc[d];
			/*
			System.out.print("시간: " + sec + "초\n" + "이동: " +  nr + " " + nc + "\n" + "회전: " + rotate[sec] + "\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(plain[i][j] + " ");
				}
				System.out.println();
			}
			*/

            // OOR
            if (nr > n || nr <= 0 || nc > n || nc <= 0) {
                System.out.println(sec);
                break;
            }
            // 스스로 부딪힘
            if (plain[nr][nc] == -1) {
                System.out.println(sec);
                break;
            }
            // 사과 만남
            else if (plain[nr][nc] == 1) {
                // 머리 이동
                plain[nr][nc] = -1;
                snake.add(new int[] {nr, nc});
            }
            else {
                // 머리 이동
                plain[nr][nc] = -1;
                snake.add(new int[] {nr, nc});
                // 꼬리 이동
                plain[snake.get(0)[0]][snake.get(0)[1]] = 0;
                snake.remove(0);

            }
            // 이동
            r = nr;
            c = nc;

            // 끝난 뒤 방향 변경
            if (rotate[sec] == 'L') {
                if (d == 0) {
                    d = 3;
                }
                else {
                    d -= 1;
                }
            }
            else if (rotate[sec] == 'D') {
                if (d == 3) {
                    d = 0;
                }
                else {
                    d += 1;
                }
            }
        }
    }
}
