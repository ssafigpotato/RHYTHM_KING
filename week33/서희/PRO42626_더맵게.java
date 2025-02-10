import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // heap에 모든 음식의 스코빌 지수 저장
        for(int food : scoville) {
            pq.offer(food);
        }
        
        int answer = 0;
        while(pq.peek() < K) {
            // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
            if(pq.size() < 2) {
                return -1;
            }
            
            // 새로운 음식으로 만들기
            answer++;
            int food1 = pq.poll();
            int food2 = pq.poll();
            
            pq.offer(food1 + (food2 * 2));
        }

        return answer;
    }
}