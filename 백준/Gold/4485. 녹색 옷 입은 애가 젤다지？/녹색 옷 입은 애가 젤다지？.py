# -- METHODS -- #
import heapq
import sys
input = sys.stdin.readline

def dijkstra():
    # 우선순위 큐
    queue = []

    # 시작 지점 초기화, [가중치, 좌표 X, Y)
    heapq.heappush(queue, [plain[0][0], 0, 0])
    cost_array[0][0] = plain[0][0]

    # 좌표축 이동을 위한 direct 변수 초기화
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    while queue:
        cost, x, y = heapq.heappop(queue)
        if x == n - 1 and y == n - 1:
            break

        # 이미 처리된 좌표인 경우
        if cost_array[x][y] < cost:
            continue

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            # 지도 바깥 범위 예외처리
            if 0 <= nx < n and 0 <= ny < n:
                new_cost = cost + plain[nx][ny]
                # 새로 구한 cost 보다 기존 cost 가 더 작으면 지나감
                if new_cost < cost_array[nx][ny]:
                    cost_array[nx][ny] = new_cost
                    heapq.heappush(queue, [new_cost, nx, ny])

# -- INPUT -- #
itor = 1
INF = int(1e9)
while True:
    n = int(input())
    if n == 0:
        break

    plain = [(list(map(int, input().split()))) for _ in range(n)]  # 평면
    cost_array = [[INF] * n for _ in range(n)]  # 최소 비용 어레이

    dijkstra()
    print(f'Problem {itor}: {cost_array[n - 1][n - 1]}')
    itor += 1