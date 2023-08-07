![image](https://github.com/Lee-search/java-example/assets/33650294/46b1bb7c-36d9-45fb-87d3-1e52da5f840a)![image](https://github.com/Lee-search/java-example/assets/33650294/ed782cd2-a454-4cb8-88b4-cb7a5cca95f1)![image](https://github.com/Lee-search/java-example/assets/33650294/9c8aef11-e949-44a5-9f5a-cdcb4894b657)# README

# [Gold V] 탑 - 2493

[문제 링크](https://www.acmicpc.net/problem/2493)

### 성능 요약

메모리: 110760 KB, 시간: 624 ms

### 분류

자료 구조, 스택

### 문제 설명

KOI 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다.

예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.

탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라.

### 입력

첫째 줄에 탑의 수를 나타내는 정수 N이 주어진다. N은 1 이상 500,000 이하이다. 둘째 줄에는 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. 탑들의 높이는 1 이상 100,000,000 이하의 정수이다.

### 출력

첫째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.

---

### 풀이

- 스택 자료구조를 이용한다.
- 정답을 저장하고 출력할 배열을 선언한다.
- 좌측부터 전파가 보내져오는 방향으로 선형 탐색을 한다.

1. 스택의 가장 상단을 peek()
    1. 해당 탑이 전파를 받지 못하면, 탑을 스택에서 pop()
    2. 전파를 받을 수 있다면, 정답을 저장한 후 break
2. 탐색이 끝난 탑을 스택에 push()
    1. 수신할 탑이 없었다면,
    → 해당 탑이 제일 높은 탑이므로 스택에 추가
    2. 수신할 탑이 있었다면,
    → 가장 높은 탑이 아니더라도 나보다 낮은 탑이 보내온 전파를 해당 탑이 먼저 수신

### 예시

**탑의 수 N = 5**

**N개 탑의 높이 = [ 6, 9, 5, 7, 4 ]**

![슬라이드1.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/1.PNG?raw=true)

1. 첫번째 탑 → 수신 가능한 탑 없음

---

![슬라이드2.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/2.PNG?raw=true)

2. 두번째 탑 → 첫번째 탑 수신 불가능 → pop()

---

![슬라이드3.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/3.PNG?raw=true)

3. 세번째 탑 → 두번째 탑이 수신

---

![슬라이드4.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/4.PNG?raw=true)

4. 네번째 탑 → 세번째 탑 수신 불가능 → pop()

---

![슬라이드5.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/5.PNG?raw=true)

5. 네번째 탑 → 두번째 탑이 수신

---

![슬라이드6.PNG](https://github.com/Lee-search/java-example/blob/master/%EB%B0%B1%EC%A4%80/Gold/2493.%E2%80%85%ED%83%91/imgs/6.PNG?raw=true)

6. 다섯번째 탑 → 4번째 탑이 수신

---

### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		int[] topList = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			topList[i] = stoi(st.nextToken());
		}
		
		Stack<int[]> stack = new Stack<>();
		int[] answer = new int[N];
		
		for(int i = 0; i < N; i++) {
			int target = topList[i];	// 현재 높이
			
			while(!stack.isEmpty()) {
				// 받을 수 없는 경우
				if(stack.peek()[1] < target) {
					stack.pop();
				}
				// 받을 수 있는 경우
				else {
					answer[i] += stack.peek()[0] + 1;
					break;
				}
			}
			
			stack.add(new int[] {i, target});
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
		
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
```
