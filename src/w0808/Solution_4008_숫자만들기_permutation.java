package w0808;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기_permutation {
	static int N;
	static int[] opers; //연산자 '+', '-', '*', '/' 순서
	static int[] numbers; //숫자
	static int Min, Max;
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream(new File("./src/w0808/input_3.txt")));
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = null;
		 StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			//@@ TODO: 입력으로 주어진 연산자를 목적에 맞게 입력 받으세요
			opers = new int[4]; //연산자 종류 만큼 배열 생성
			for (int i = 0; i < 4; i++) {
				opers[i] = Integer.parseInt(st.nextToken());
			}

			//연산에 활용할 숫자 정보
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N;i++) {
				numbers[i]=Integer.parseInt(st.nextToken());
			}
			
			//--------------INPUT END-------------------------
			//테스트 케이스 별로 값이 유지 되기 때문에 초기화 필수
			Min =Integer.MAX_VALUE;
			Max = Integer.MIN_VALUE;
			//@@ TODO : 연산자 순열을 만들어서, 수식을 완성시켜 보세요.
			makePer(0, new int[N - 1]);
			
			sb.append(String.format("#%d %d%n",t,Max-Min));
			
		}
		System.out.println(sb);

	}
	
	/**
	 * @TODO : 연산자 순열 만들기
	 */
	static void makePer(int cnt, int[] operSelect) {
		if(cnt == N - 1) { // 기저 조건
			int result = calc(operSelect); // 완성된 연산자 순서 -> 계산
			Min = Math.min(Min, result);
			Max = Math.max(Max, result);
			return;
		}

		// 4종류 연산자 확인
		for(int i = 0; i < 4; i++) {
			if(opers[i] > 0) {
				operSelect[cnt] = i;
				opers[i] -= 1;	// 사용
				makePer(cnt + 1, operSelect);
				opers[i] += 1;	// 사용 취수
				// 연산자를 사용하지 않고 수식이 완성되는 경우는 없음
				// 선택 없이 순열이 다음으로 넘어가는 경우 X
			}
		}
	}
	
	/**
	 * @TODO : 연산식 계산하기
	 * @return
	 */
	static int calc(int[] operSelect) {
		int num = numbers[0];
		for(int i = 0; i < operSelect.length; i++) {
			int oper = operSelect[i];
			
			if(oper == 0) {
				num += numbers[i + 1];
			}
			else if (oper == 1) {
				num -= numbers[i + 1];
			}
			else if (oper == 2) {
				num *= numbers[i + 1];
			}
			else if (oper == 3) {
				num /= numbers[i + 1];
			}
		}

		return num;
	}
}
