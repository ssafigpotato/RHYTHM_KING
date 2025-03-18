import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프에 간선 추가
        for (int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        int minDiff = Integer.MAX_VALUE; // 최소 차이를 저장할 변수

        // 각 전선을 끊어보면서 최소 차이를 찾기
        for (int[] wire : wires) {
            // 전선을 끊기
            graph.get(wire[0]).remove((Integer) wire[1]);
            graph.get(wire[1]).remove((Integer) wire[0]);

            // DFS로 두 전력망의 송전탑 개수 계산
            int count1 = dfs(wire[0], new boolean[n + 1], graph);
            int count2 = dfs(wire[1], new boolean[n + 1], graph);

            // 최소 차이 업데이트
            minDiff = Math.min(minDiff, Math.abs(count1 - count2));

            // 전선을 다시 연결
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        return minDiff;
    }

    // DFS로 송전탑 개수 세기
    static int dfs(int node, boolean[] visited, List<List<Integer>> graph) {
        visited[node] = true;
        int count = 1;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(next, visited, graph);
            }
        }

        return count;
    }
}