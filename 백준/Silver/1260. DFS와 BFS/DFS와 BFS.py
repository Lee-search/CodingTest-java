# 정점, 간선, 시작노드
n, m, start = map(int, input().split())
graph = []
for _ in range(n + 1):
    graph.append([])

for _ in range(m):
    v1, v2 = list(map(int, input().split()))
    graph[v1].append(v2)
    graph[v2].append(v1)

for i in range(n + 1):
    graph[i].sort()

def dfs(graph: list, v: int, visited: list):
    visited[v] = True
    print(v, end=' ')

    for next in graph[v]:
        if not visited[next]:
            dfs(graph, next, visited)


from collections import deque

def bfs(graph: list, v: int, visited: list):
    q = deque([v])
    visited[v] = True
    print(v, end=' ')

    while q:
        now = q.popleft()
        for near in graph[now]:
            if not visited[near]:
                q.append(near)
                visited[near] = True
                print(near, end=' ')

visited = [False] * (n + 1)
dfs(graph, start, visited)
print()
visited = [False] * (n + 1)
bfs(graph, start, visited)
