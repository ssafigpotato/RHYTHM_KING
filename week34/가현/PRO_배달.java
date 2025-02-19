import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        // [1] 문제 요약
        // - 1번 마을에서 K시간 이하로 배달할 수 있는 마을 개수를 구하는 문제
        // - 그래프의 최단 경로 문제로 변환 가능

        // [2] 해결 방법
        // - 1번 마을을 시작점으로 다익스트라 알고리즘을 사용해 최단 거리를 계산
        // - 최단 거리가 K 이하인 마을 개수를 반환
        
        // [3] 그래프 데이터 구조 정의
        // - 각 마을과 연결된 도로 정보를 저장하기 위해 인접 리스트(List<List<int[]>>) 사용
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // [4] 도로 정보 입력 (양방향 그래프)
        // - road 배열의 정보를 graph에 저장 (a 마을과 b 마을이 c 시간 거리로 연결됨)
        for (int[] r : road) {
            int a = r[0], b = r[1], c = r[2];
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        
        // [5] 다익스트라 알고리즘 초기화
        // - 최단 거리 배열(dist) 생성 및 최대값(Integer.MAX_VALUE)으로 초기화
        // - 1번 마을(시작점)의 거리는 0으로 설정
        int[] dist = new int[N + 1]; // 각 마을까지의 최단 거리 저장
        Arrays.fill(dist, Integer.MAX_VALUE); // 최댓값으로 초기화
        dist[1] = 0; // 시작점(1번 마을)의 거리는 0
        
        // 우선순위 큐 (최소 힙) 사용 → {거리, 노드} 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 1});
        
        // [6] 다익스트라 알고리즘 실행
        // - 큐에서 가장 거리가 짧은 노드를 선택해 탐색
        // - 현재 노드에서 갈 수 있는 모든 마을을 확인하며 최단 거리 갱신
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0], curNode = cur[1];
            
            // 이미 처리된 거리보다 길다면 무시
            if (curDist > dist[curNode]) continue;
            
            // 현재 노드에서 갈 수 있는 모든 마을 확인
            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0], nextDist = curDist + next[1];
                
                // 더 짧은 거리 발견 시 갱신
                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    pq.add(new int[]{nextDist, nextNode});
                }
            }
        }
        
        // [7] K 이하 거리의 마을 개수 세기
        // - dist 배열에서 K 이하의 값을 가지는 마을 개수를 계산
        int count = 0;
        for (int d : dist) {
            if (d <= K) count++;
        }
        
        return count;
    }
}
