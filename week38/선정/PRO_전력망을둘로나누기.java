import java.util.*;
class Solution {
    static HashMap<Integer, List<Integer>> graph; // 간선 정보 저장 
    static boolean[]visited;
    public int solution(int n, int[][] wires) {
       // 1. 그래프 구성 (HashMap을 사용한 인접 리스트)
        graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] wire : wires) {
            // start <-> end이므로 무방향 그래프 
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        } // 입력 끝 

        int minDiff = Integer.MAX_VALUE; // 차이 최솟값 

        // 2. 모든 전선 하나씩 제거해보기 
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            // 간선 제거
            graph.get(v1).remove(Integer.valueOf(v2));
            graph.get(v2).remove(Integer.valueOf(v1));

            // 3. 한쪽 네트워크 크기 탐색 (DFS)
            visited = new boolean[n + 1]; // 매 간선마다 새로 초기화!!! 
            int size = dfs(v1, visited);

            // 두 네트워크 크기 차이 계산
            int diff = Math.abs((n - size) - size); // size와 n-size한 것 비교
            minDiff = Math.min(minDiff, diff); // 최솟값 갱신 

            // 간선 복구
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        return minDiff;
    }

    private int dfs(int node, boolean[] visited) { // 한 그룹의 수 저장 
        visited[node] = true;
        int count = 1; // 현재 노드 포함
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, visited);
            }
        }
        return count;
    
    }
}