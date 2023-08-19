# idea: https://www.acmicpc.net/board/view/82003
import sys
input = sys.stdin.readline

r, c = map(int, input().split())
plain = [list(input()) for _ in range(r)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

idx = [0] * 26   # A-Z 까지의 횟수를 나타내는 리스트 생성
idx[ord(plain[0][0]) - 65] += 1    # 출발지점 방문처리
answer = 1

def dfs(x, y, count):
    global answer
    answer = max(answer, count)
    # 4 방향 이동 후 이미 idx 된 알파벳 만나면 백트래킹
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c:
            if idx[ord(plain[nx][ny]) - 65] == 0:
                idx[ord(plain[nx][ny]) - 65] += 1
                dfs(nx, ny, count + 1)
                idx[ord(plain[nx][ny]) - 65] -= 1

dfs(0, 0, answer)
print(answer)