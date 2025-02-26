import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
      // pq: 서버의 만료 시간과 개수를 저장하는 우선순위 큐 (시간 기준으로 정렬)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[0] - o2[0]);
        int size = 0;  // 현재 서버의 개수 
        int count = 0; // 증설된 서버 횟수  
        for(int i = 0; i < 24; i++){
            // 만료된 서버 내리기 
            // 현재 시간(i)에 만료되는 서버들을 우선순위 큐에서 제거하고, 전체 서버 개수에서 차감합니다.
            while(!pq.isEmpty() && pq.peek()[0] == i){
                size -= pq.poll()[1];
            }
            int need = players[i] / m;  // 현재 필요한 서버의 개수 
            int more = size - need;     // 현재 서버와 필요 서버의 차이
            
            if(more < 0) {
                more = -more;           // 부족한 서버 수의 절댓값
                size += more;           // 현재 서버 개수 증가
                count += more;          // 총 증설 횟수 증가
                pq.add(new int[]{i + k, more}); // k시간 후 만료될 서버 정보 추가
            }
        }
        return count;
    }
}
