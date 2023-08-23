import heapq
INF = int(1e9)

n, k = map(int, input().split())

def dijkstra(start: int, dest: int) -> int:
    # 우선순위 큐 생성, [비용, 위치]
    heap = []

    # 0부터 100,000까지의 비용 리스트 생성
    cost_array = [INF for _ in range(1000001)]

    # 시작지점으로 가는 비용은 0
    cost_array[start] = 0
    heapq.heappush(heap, [0, start])

    while heap:
        # 현재 위치와 비용 pop
        cost, pos = heapq.heappop(heap)

        # 이미 처리된 좌표인 경우 continue
        if cost_array[pos] < cost:
            continue

        # 갱신된 위치가 최대치를 넘는 경우 continue
        # 앞 뒤로 한칸씩 가는 비용은 1
        if 0 <= pos - 1 <= 100000 and cost + 1 < cost_array[pos - 1]:
            cost_array[pos - 1] = cost + 1
            heapq.heappush(heap, [cost + 1, pos - 1])

        if 0 <= pos + 1 <= 100000 and cost + 1 < cost_array[pos + 1]:
            cost_array[pos + 1] = cost + 1
            heapq.heappush(heap, [cost + 1, pos + 1])

        # 순간이동 하는 비용은 0
        if 0 <= 2 * pos <= 100000 and cost < cost_array[2 * pos]:
            cost_array[2 * pos] = cost
            heapq.heappush(heap, [cost, 2 * pos])
    
    # 도착지점의 비용 리턴
    return cost_array[dest]

print(dijkstra(n, k))