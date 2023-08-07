import sys
input = sys.stdin.readline

n = int(input())
top_list = list(map(int, input().split()))

stack = []
answer = [0] * n

for i in range(n):
    target = top_list[i]    # 현재 탑의 높이
    while stack:
        if stack[-1][0] < target:
            stack.pop()
        else:
            answer[i] = stack[-1][1] + 1
            break

    stack.append((target, i)) # 높이, 위치

print(' '.join(str(s) for s in answer))