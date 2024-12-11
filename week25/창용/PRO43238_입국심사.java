import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long start = 0;
        long end = (long)n * times[times.length - 1];
        long mid;
        while(start <= end){
            mid = (start + end) / 2;
            long complete = 0;
            for(int i = 0; i < times.length; i++){
                complete += mid / times[i];
            }
            if(complete < n){
                start = mid + 1;
            }else{
                end = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}
