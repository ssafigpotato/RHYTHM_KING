import java.util.*;

class PRO42627_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int currentTime = 0;
        int totalTime = 0;
        int jobCount = jobs.length;
        int jobIndex = 0;
        int processedJobs = 0;

        while(processedJobs < jobCount){
            while(jobIndex < jobCount && jobs[jobIndex][0] <= currentTime){
                pq.offer(jobs[jobIndex++]);
            }

            if(pq.isEmpty()){
                currentTime = jobs[jobIndex][0];
            }else{
                int[] job = pq.poll();
                currentTime += job[1];
                totalTime += currentTime - job[0];
                processedJobs++;
            }

        }

        answer = totalTime / jobCount;

        return answer;
    }
}
