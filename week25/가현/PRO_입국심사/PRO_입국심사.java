class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long low = 1; // 최소 시간 (그냥 제일 작은 단위)
        long high = (long)times[times.length-1]  * n; // 최대 시간 (제일 느린 사람이 모두를 검사하는 시간)
        
        while(low <= high) {
            long mid = (low + high) / 2; //long 주의
            
            long total = 0; //검사 인원
            
            for(int time: times){
                total += mid / time; //각 심사관이 mid 타임까지 검사할 수 있는 인원
            }
            
            if(total >= n){
                answer = mid;
                high = mid-1;
            } else {
                low = mid +1;
            }
        }
        
        
        return answer;
    }
}