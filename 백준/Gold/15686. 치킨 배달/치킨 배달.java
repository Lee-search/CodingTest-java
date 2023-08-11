import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, M, answer;
	public static int[][] plain;
	public static List<int[]> homes;
	public static List<int[]> chickens;
	
	public static int[] selector;
	public static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		plain = new int[N][N];
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				plain[i][j] = stoi(st.nextToken());
				// 0: 빈칸, 1: 집, 2: 치킨집
				if(plain[i][j] == 1) homes.add(new int[] {i , j});
				if(plain[i][j] == 2) chickens.add(new int[] {i , j});
			}
		} // end of input
		
		// for combinations
		selector = new int[M];
		isSelected = new boolean[chickens.size()]; // 치킨 갯수 중 개 중 M개 선택
		
		combinations(0, 0);
		System.out.println(answer);
		
	} // end of main
	
	// 해당 집에서 선택된 치킨집 사이의 거리 최소값 구함
	public static void calc() {
		
		int sum = 0;
		
		// 1. 각 집들에 대해서
		for(int i = 0; i < homes.size(); i++) {
			int h_r = homes.get(i)[0];
			int h_c = homes.get(i)[1];
			
			int min = Integer.MAX_VALUE; // 치킨거리 합
			
			for(int j = 0; j < M; j++) {
				// selector : M개의 치킨집 저장
				int ch_r = chickens.get(selector[j])[0];
				int ch_c = chickens.get(selector[j])[1];
				
				int dist = Math.abs(ch_r - h_r) + Math.abs(ch_c - h_c);
				min = Math.min(min, dist);
				
			} // 2. 치킨집 M개에 대한 치킨거리의 최소값 구하기
			
			sum += min;
		} // 3. 치킨거리합의 최소 구하기
		
		answer = Math.min(answer, sum);
	} // end of func
	
	public static void combinations(int cnt, int start) {
		
		if(cnt == M) {
//			System.out.println(Arrays.toString(selector));
			calc();
			return;
		} // 기저조건
		
		for(int i = start; i < chickens.size(); i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			selector[cnt] = i;
			combinations(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
