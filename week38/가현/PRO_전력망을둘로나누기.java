import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int minDiff = n; // 최대 n까지 가능
        
        for (int i = 0; i < wires.length; i++) {
            // 그래프 초기화
            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new ArrayList<>());
            }
            
            // 간선 추가 (i번째 간선 제외)
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue; // 간선 끊기
                int v1 = wires[j][0];
                int v2 = wires[j][1];
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }
            
            // BFS로 한 쪽 서브트리 노드 개수 세기
            boolean[] visited = new boolean[n + 1];
            int count = bfs(1, graph, visited);
            
            int otherCount = n - count;
            int diff = Math.abs(count - otherCount);
            minDiff = Math.min(minDiff, diff);
        }
        
        return minDiff;
    }
    
    private int bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }
        
        return count;
    }
}
