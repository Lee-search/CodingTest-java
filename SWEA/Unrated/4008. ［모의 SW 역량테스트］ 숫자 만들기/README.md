# [Unrated] [모의 SW 역량테스트] 숫자 만들기 - 4008 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH) 

### 성능 요약

메모리: 21,752 KB, 시간: 136 ms, 코드길이: 1,933 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do



### 입력
- N개의 숫자
- +, -, *, / 의 연산자 카드를 숫자 사이에 끼워넣기
- 수식 계산시, 연산자 우선순위 고려하지 않음
- 왼쪽에서 오른쪽으로 계산

### 문제
- 주어진 연산 카드를 사용하여 수식 계산, 최대 - 최소 수식의 차를 출력
1. 주어진 연산자 후보군 중 하나 선택
2. 이미 쓴 연산자는 다른 자리에서 쓸 수 없음
3. 자리에 따라서 의미가 달라진다
- 순열 사용 시, 3 <= N <= 12 이므로 11!의 시간복잡도를 가짐

### 구현
- 연산자 구성을 순열로 함
- 기저조건: 연산자의 선택 순서가 N - 1(연산자의 개수)이면 종료.
- 수식 계산 후 최대, 최소값 갱신
- 각 순서의 연산자 선택, 연산자 있으면 사용 후 다음 순열 진행