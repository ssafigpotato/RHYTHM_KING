import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        int index = 0;
        int time = 0;
        int waitingTime = 0;
        int completed = 0;
        
        Arrays.sort(jobs, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        
        while(completed < jobs.length){
            
            // 현재 시간까지 요청이 들어온 애들 pq에 넣기, 1번 조건을 넣는 이유는 배열에러가 날 수 있음
            while(index < jobs.length && jobs[index][0] <= time){
                pq.offer(jobs[index]);
                index++;
            }
            
            // 처리할 게 있다.
            if(!pq.isEmpty()){
                int[] job = pq.poll();
                // waitingTime += time - job[0]; // 처리 시간을 뺀 ㄹㅇ 기다린 시간인줄
                time += job[1];
                waitingTime += time - job[0];
                completed++;
            } 
            // 처리할 게 없다.
            else {
                time = jobs[index][0];
            }
            
            
        }
        
        return waitingTime / jobs.length;
    }
}