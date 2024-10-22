import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 작업을 요청 시간 기준으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 우선순위 큐: 작업 소요 시간이 짧은 순으로 처리하기 위함
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int time = 0; // 현재 시간
        int index = 0; // jobs 배열에서 처리할 작업의 인덱스
        int total = 0; // 총 대기 시간
        int count = 0; // 완료된 작업 수
        
        // 모든 작업이 완료될 때까지 반복
        while (count < jobs.length) {
            // 현재 시간까지 요청된 작업들을 큐에 넣음
            while (index < jobs.length && jobs[index][0] <= time) {
                pq.add(jobs[index++]);
            }
            
            // 처리할 작업이 있을 경우
            if (!pq.isEmpty()) {
                int[] currentJob = pq.poll(); // 소요 시간이 가장 짧은 작업
                time += currentJob[1]; // 현재 시간에 작업 소요 시간 더함
                total += time - currentJob[0]; // 요청부터 종료까지 걸린 시간
                count++;
            } else {
                // 처리할 작업이 없으면 시간을 다음 작업의 요청 시간으로 점프
                time = jobs[index][0];
            }
        }
        
        // 평균 대기 시간 계산
        return total / jobs.length;
    }
}
