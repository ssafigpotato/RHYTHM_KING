import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        // 작업 시간이 작은 것부터 처리하기 위한 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[1]));

        int K = jobs.length; // 작업의 총 개수

        int completed = 0; // 완료된 작업의 수
        int now = 0; // 현재 시간
        int cnt = 0; // 현재 처리할 작업의 인덱스

        // 요청 시간을 기준으로 정렬하기 위한 리스트
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            list.add(jobs[i]);
        }

        // 요청 시간을 기준으로 오름차순 정렬
        Collections.sort(list, Comparator.comparingInt((int[] o) -> o[0]));

        // 완료된 작업이 전체 작업 수와 같을 때까지 반복
        while (completed < K) {
            // 현재 시간보다 이전에 요청된 작업을 큐에 추가
            while (cnt < K && list.get(cnt)[0] <= now) {
                pq.add(list.get(cnt));
                cnt++;
            }

            if (!pq.isEmpty()) {
                // 처리할 작업을 꺼내서 실행
                int[] curr = pq.poll();
                now += curr[1]; // 작업이 끝나는 시간으로 now 갱신
                answer += now - curr[0]; // 요청부터 완료까지 걸린 시간 계산
                completed++;
            } else {
                // 처리할 작업이 없을 경우, 다음 요청 시간이 될 때까지 now를 증가시킴
                now = list.get(cnt)[0];
            }
        }

        // 평균 시간을 계산하여 반환
        return answer / K;
    }
}
