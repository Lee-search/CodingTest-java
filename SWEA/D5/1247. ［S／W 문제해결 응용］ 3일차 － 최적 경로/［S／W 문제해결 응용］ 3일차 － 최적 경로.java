import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, answer;
	static int startRow, startCol;	// 회사 좌표
	static int destRow, destCol;	// 집 좌표
	static int[] nRows, nCols;		// 고객 좌표 리스트
	
	static int[] selector;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream(new File("./src/w0817/input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			nRows = new int[N];
			nCols = new int[N];
			answer = Integer.MAX_VALUE;	// 최적 거리값 초기화
			
			st = new StringTokenizer(br.readLine());
			
			startRow = stoi(st.nextToken());
			startCol = stoi(st.nextToken());
			destRow = stoi(st.nextToken());
			destCol = stoi(st.nextToken());
			for(int i = 0; i < N; i++) {
				nRows[i] = stoi(st.nextToken());
				nCols[i] = stoi(st.nextToken());
			} // end of init
			
			selector = new int[N];
			isSelected = new boolean[N];
			permutations(0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	} // end of main
	
	public static void exec() {
		
		int sumDist = 0;
		// 1. 회사 -> 고객 1번
		sumDist += getDistance(startRow, startCol, nRows[selector[0]], nCols[selector[0]]);
		
		// 2. 고객 집 순회
		for(int i = 0; i < N - 1; i++) 
			sumDist += getDistance(nRows[selector[i]], nCols[selector[i]], nRows[selector[i + 1]], nCols[selector[i + 1]]);
		
		// 3. 고객 N-1번 -> 집
		sumDist += getDistance(nRows[selector[N - 1]], nCols[selector[N - 1]], destRow, destCol);
		
		answer = Math.min(answer, sumDist);
	} // end of func
	
	public static void permutations(int cnt) {
		
		if(cnt == N) {
			exec();
			return;
		} // basis
		
		for(int i = 0; i < N; i++) {
			
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			selector[cnt] = i;
			permutations(cnt + 1);
			isSelected[i] = false;
		}
		
	} // end of func
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
