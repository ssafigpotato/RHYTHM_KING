import java.util.*;
class Solution {
    // 한 번의 pop +  한 번의 insert  => 작업 1회 수행
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // 0. 각 큐 선언
        Queue<Integer>que1 = new LinkedList<>();
        Queue<Integer>que2 = new LinkedList<>();
        
        // 1. 각 큐의 원소들의 합을 알아보기
        // 각 큐에 집어넣기
        // 카드 섞기 같은 문제
        long hap1 = 0;
        for(int i = 0; i < queue1.length; i++){
            hap1 += queue1[i];
            que1.offer(queue1[i]);
        }
        long hap2 = 0;
        for(int i = 0; i <queue2.length; i++){
            hap2 += queue2[i];
            que2.offer(queue2[i]);
        }
        long hap = 0; 
        hap = (hap1 + hap2)/2;
        // System.out.println(hap); // 확인
        // System.out.println(que1); // 확인
    
        // 2. 각 큐
        while(hap1 != hap2){
            // 1. answer이 (큐1의 길이 + 큐2의 길이)*2 보다 커진다는 것은
            // 모든 원소를 서로 교환하여도 두 큐의 합을 같게 만들 수 없는 경우
            // 따라서 두 큐 합 같게 만드는 것이 불가능한 경우니까 return 
            if(answer > (que1.size() + que2.size())*2 ) return -1;
             // System.out.println("처음!!!! hap1: "+hap1+", hap2: "+hap2+", answer: "+answer);
            
            // 2. 합이 더 큰 큐에서 합이 더 작은 큐로 옮겨주기 
            if(hap1 > hap2){
                int curr = que1.poll();
                que2.offer(curr);
                hap2 += curr;
                hap1 -= curr;
                answer++;
                // System.out.println("hap1: "+hap1+", hap2: "+hap2+", answer: "+answer);
                
            }else if(hap2 > hap1){
                int curr = que2.poll();
                que1.offer(curr);
                hap1 += curr;
                hap2 -= curr;
                answer++;
             // System.out.println("hap1: "+hap1+", hap2: "+hap2+", answer: "+answer);
            }else { // hap1 == hap2일 때
                return answer;
            }
        }
        
        
        
        return answer;
    }
    
    
    
}