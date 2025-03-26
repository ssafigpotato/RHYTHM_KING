import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);  

        int left = 0;
        int right = d.length;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int sum = 0;
            for (int i = 0; i < mid; i++) {
                sum += d[i];
            }

            if (sum <= budget) {
                answer = mid;     // mid개 부서를 지원 가능 → 더 늘려보ㅏ
                left = mid + 1;
            } else {
                right = mid - 1;  // 예산 초과 → 줄여야 함
            }
        }

        return answer;
    }
}
