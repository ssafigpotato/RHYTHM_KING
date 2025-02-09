import java.util.*;
class Solution {
    static class Scoville implements Comparable<Scoville>{
        int s;
        // 생성자 
        Scoville(int s){
            this.s = s;
        }
        // 오름차순 정렬
        @Override
        public int compareTo(Scoville o){
            return Integer.compare(this.s, o.s);
        }
    }
    static boolean check(int K, PriorityQueue<Scoville> que){
        for(Scoville sc : que){ // PriorityQueue 또한 Iterable 인터페이스를 구현 -> for-each문 사용가능! 
            if(sc.s < K ){
                return false; 
            }
        }
        return true; 
    }
    public int solution(int[] scoville, int K) {
        
        // 가장 작은 스코빌 음식들 부터 섞기
        PriorityQueue<Scoville> que = new PriorityQueue<>();
        for(int s : scoville){
            que.add(new Scoville(s)); 
        }
        
        int answer = 0;
        while(true){ 
            if(check(K, que)){ // que의 모든 요소가 K보다 크다면
                break; 
            }
            Scoville min = que.poll();
            
            if(que.size() <1){
                answer = -1;
                break; 
            }
            Scoville min2 = que.poll();
            
            int mix = min.s + min2.s*2;
            que.offer(new Scoville(mix));
            answer++; 
            // System.out.println(answer+"번째 섞었을 때: "+que);
        }
        
        return answer;
    }
}