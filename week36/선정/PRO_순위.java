import java.util.*;

// 플로이드-워셜 알고리즘 사용! 
// 모든 노드 간의 최단 경로 구하기 
// 특징: 그래프에서 모든 정점 간의 관계를 한 번에 계산 가능
// 응용: 직접 연결되지 않은 노드 간의 관계를 유추할 수 있음

// 선수 간의 승패 관계를 간접적으로 유추해야 하므로 
// A > B 이고, B > C 이면 -> A > C
// 플로이드-워셜을 사용하면 "연결된 승패(대소) 관계"를 찾을 수 있음!

class Solution {
    public int solution(int n, int[][] results) {
        // 1. 그래프 초기화 
        // graph[i][j] 에 i 선수가 j선수를 이기는지 저장 
        // 이긴 경우 1, 진 경우 -1, 모르면 0
        int[][] graph = new int[n + 1][n + 1];

        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];
            graph[win][lose] = 1;  // winner가 loser를 이김
            graph[lose][win] = -1; // loser는 winner에게 짐
        }

        // 2. 플로이드-워셜 알고리즘 수행
        for (int k = 1; k <= n; k++) {       // 중간 선수 (경유)
            for (int i = 1; i <= n; i++) {   // 출발 선수
                for (int j = 1; j <= n; j++) { // 도착 선수
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;  // i가 k를 이기고, k가 j를 이기면 → i가 j를 이김
                        graph[j][i] = -1; // 반대로 j는 i에게 짐
                    }
                }
            }
        }

        // 3. 정확한 순위를 매길 수 있는 선수 찾기
        int answer = 0;

        for (int i = 1; i <= n; i++) { // 각각의 선수에 대해서 
            int cnt = 0; // 승패관계를 아는 경우의 수 
            
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != 0) {  // 승패 관계를 안다면(0이 아니라면)
                    cnt++;
                }
            }
            if (cnt == n - 1) { // 자신과 나머지 모든 선수와의 승패를 안다 -> 순위 확정
                answer++;
            }
        }

        
        
        return answer;
    }
}
