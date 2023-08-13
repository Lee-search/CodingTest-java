# 순열, 조합, 부분집합 정리

### 순열 (Permutation)

- 개념
    - 서로 다른 것들 중 몇 개를 뽑아서 한 줄로 나열하는 것
    - 순서의 개념이 있음
    - N 개의 요소들에 대해 N! 개의 순열들이 존재
- 구현
  - nPr의 갯수 → n * (n - 1) * (n - 2) * … * (n - r + 1)
  - nPn의 갯수 → n * (n -1) * (n - 2) * … * 2 * 1
  - N = 6, R = 3 인 경우, nPr은 6 * 5 * 4 = 120 개의 경우가 나오게 된다.
```java
public static void permutation(int cnt) {

      if(cnt == R) {
          // 기저조건
          System.out.println(Arrays.toString(numbers));
          return;
      }

      for(int i = 0; i < N; i++) {
          if(isSelected[i]) continue;

          numbers[cnt] = i + 1;
          isSelected[i] = true;
          permutation(cnt + 1);
          isSelected[i] = false;
      }
  }
```

- 활용예시 : N개의 눈이 있는 주사위를 R번 던져서 나오는 모든 경우
    

### 조합

- 개념
    - 서로 다른 n개의 원소 중 r개를 순서 없이 골라낸 것
    - 순서 개념이 없음
- 구현
    - nCr의 갯수 → n! / ((n - r)! * r!)
    - N = 6, R = 3 인 경우, nCr은 6! / (3! * 3!) = 20  개의 경우가 나오게 된다.

```java
public static void combination(int cnt, int start) {

      if(cnt == R) {
          // 기저조건
          System.out.println(Arrays.toString(numbers));
          return;
      }

      for(int i = start; i < N; i++) {

          numbers[cnt] = i + 1;
          combination(cnt + 1, i + 1);
      }
  }
```

- 활용예시 : N개의 눈이 있는 주사위를 R번 던져서 중복을 제외하고 나올 수 있는 모든 경우

### 부분집합

- 개념
    - 집합에 포함된 원소들을 선택하는 것
    - 순열/조합과 달리, 부분집합은 뽑는 원소를 기준으로 함
- 구현
    - 집합에 n개의 원소가 있을 때, 부분집합은 2**n개가 존재한다.
    - N = 4일때 부분집합의 개수는 2 * 2 * 2 * 2 = 16 가지
```java
public static void generateSubSet(int cnt) {

      if(cnt == N) {
          // 기저조건
          for(int i = 0; i < N; i++) {
              if(isSelected[i]) System.out.print(i + 1 + "\t");
          }
          System.out.println();

          return;
      }

      isSelected[cnt] = true;
      generateSubSet(cnt + 1);
      isSelected[cnt] = false;
      generateSubSet(cnt + 1);
  }
```

- 활용 예시 : N명 중 주사위를 던질 사람과 아닌 사람을 뽑는 경우의 수