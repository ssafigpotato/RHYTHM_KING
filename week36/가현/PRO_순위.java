class Solution {
    public int solution(int n, int[][] results) {
        // 승패 관계를 저장할 2차원 배열 생성
        int[][] graph = new int[n + 1][n + 1];
        
        // 주어진 경기 결과
        for (int[] result : results) {
            // result[0]이 result[1]을 이김
            graph[result[0]][result[1]] = 1;  // 승리
            graph[result[1]][result[0]] = -1; // 패배
        }
        
        // 모든 선수 간 관계 추론
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // 간접적인 승패 관계 확인
                    // i가 k를 이기고, k가 j를 이기면 i는 j도 이긴다
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                    // i가 k에게 지고, k가 j에게 지면 i는 j에게 진다
                    if (graph[i][k] == -1 && graph[k][j] == -1) {
                        graph[i][j] = -1;
                    }
                }
            }
        }
        
        // 순위를 확정할 수 있는 선수 카운트
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int knownMatches = 0;
            for (int j = 1; j <= n; j++) {
                // 본인-다른 선수 간의 관계를 알면 카운트
                if (graph[i][j] != 0 && i != j) {
                    knownMatches++;
                }
            }
            
            // 모든 다른 선수와의 관계를 알면 순위 확정
            if (knownMatches == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}