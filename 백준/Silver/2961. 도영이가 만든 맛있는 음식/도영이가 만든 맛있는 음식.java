import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static long[][] ingredients;
	public static int[] sets;
	public static boolean[] isSelected;
	public static long answer = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = stoi(br.readLine());	// 재료 갯수
		ingredients = new long[n][2];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ingredients[i][0] = stoi(st.nextToken());
			ingredients[i][1] = stoi(st.nextToken());
		}
		
		// 모든 재료의 조합 구한 뒤, abs(신맛의 합 - 쓴맛의 합) 구하기
		isSelected = new boolean[n];
		sets = new int[n];
		
		getSubsets(0, 0);
		System.out.println(answer);
		
	} // end of main
	
	public static void getSubsets(int idx, int selected) {
		
		if(idx == n) {
			if(selected == 0) return; // 1개 이상 선택 -> 공집합이 최소값이 되는 것 방지
//			System.out.println(Arrays.toString(isSelected));
			
			int fv1 = 1, fv2 = 0; // 신맛곱, 쓴맛합
			for(int i = 0; i < n; i++) {
				if(isSelected[i]) {
					fv1 *= ingredients[i][0];
					fv2 += ingredients[i][1];
				}
			}
			// 신맛합 쓴맛합의 차이가 가장 큰 값 리턴
			answer = Math.min(answer, Math.abs(fv1 - fv2));
			
			return;
		}
		
		isSelected[idx] = true;
		getSubsets(idx + 1, selected + 1);
		isSelected[idx] = false;
		getSubsets(idx + 1, selected);
	} // end of methods
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
