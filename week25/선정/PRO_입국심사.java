import java.util.*;
class Solution {
    static long answer;
    public long solution(int n, int[] times) {
       
        // 이분탐색을 위해 오름차순 정렬
        Arrays.sort(times);
        
        long min = 1; // 입국심사 최소 시간
        long max = (long) times[times.length -1] * (long) n; // 입국심사 최대 시간
        
        answer = max; 
        binarySearch(min, max, n, times);
        
        return answer;
    }
    
    public static void binarySearch(long left, long right, int target, int [] arr){
        while(left <= right){
            // 입국 심사를 완료하는 데 필요한 예상 시간
            long mid = (left + right)/2;
            
            // 각 심사관이 mid 시간 동안 처리할 수 있는 사람 수를 계산
            long num = 0;
            for(int i = 0; i < arr.length; i++){
                num += mid / arr[i];
            }
            
            if (target <= num) { // 처리할 수 있으면
                answer = Math.min(answer, mid); // 해당 시간(mid)을 answer로 갱신
                right = mid - 1; // 더 최소인 시간을 찾기 위해 범위 좁히기
            } else { // 처리할 수 없으면 
                left = mid + 1; // 더 큰 시간에서 찾아보기 
            }
        }
    }
}