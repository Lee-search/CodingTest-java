
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] plain;
    static int answer;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        plain = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                plain[i][j] = line.charAt(j);
            }
        } // end of init

        while (true) {

            boolean puyo = false;
            // BFS 후 뿌요 확인
            for(int r = 11; r >= 0; r--) {
                for (int c = 0; c < 6; c++) {
                    if(plain[r][c] == '.') continue;
                    if(BFS(r, c)) puyo = true;
                }
            }

            // 뿌요 터진 경우, 내리고 카운트 업
            if(puyo) {
//                System.out.println("내리기 전");
//                print();

                down();

//                System.out.println("내린 후");
//                print();
//                System.out.println("-----");

                answer += 1;
            } else {
                System.out.println(answer);
                break;
            }
        } // end of while
    } // end of main

    public static void down() {

        Queue<Character> q = new ArrayDeque<>();
        
        // 1. 각 열별로
        for (int c = 0; c < 6; c++) {
            // 2. 블럭 모두 담아서
            for (int r = 11; r >= 0; r--) {
                if(plain[r][c] != '.') {
                    q.offer(plain[r][c]);
                    plain[r][c] = '.';
                }
            }
            // 3. 재배치
            int r = 11;
            while (!q.isEmpty()) {
                plain[r][c] = q.poll();
                r -= 1;
            }
        }
    } // end of func

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static boolean BFS(int sr, int sc) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[12][6];

        // 4개 이상이면 해당 list를 통해 뿌요 없앰
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{sr, sc});

        char color = plain[sr][sc];
        int count = 1;  // 같은 색깔 뿌요 갯수
        
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(!isPossible(nr, nc) || visited[nr][nc]) continue;
                if(plain[nr][nc] != color) continue;
                
                count += 1;
                q.offer(new int[]{nr, nc});
                list.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        } // end of while

        if(count >= 4) {
            for (int[] info : list) {
                int r = info[0], c = info[1];
                plain[r][c] = '.';
            }
            return true;
        }
        return false;
    } // end of func

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < 12 && 0 <= c && c < 6;
    } // end of func

    public static void print() {
        for (int i = 0; i < 12; i++) {
            System.out.println(Arrays.toString(plain[i]));
        }
    } // end of func
}
