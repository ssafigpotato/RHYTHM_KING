import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        // 바위들을 정렬
        Arrays.sort(rocks);

        int low = 1; // 가능한 거리의 최소값
        int high = distance; // 가능한 거리의 최대값 (시작~도착)
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2; // 중간값(최소 거리 기준 / 임의의 값)
            int removed = 0; // 제거한 바위 개수
            int prev = 0; // 이전 지점

            for (int rock : rocks) {
                if (rock - prev < mid) {
                    // 최소 거리(mid)보다 짧다면 바위 제거
                    removed++;
                } else {
                    // 최소 거리(mid)를 만족하면 현재 바위로 갱신(=이동)
                    prev = rock;
                }
            }

            // 마지막 도착지점과의 거리 확인
            if (distance - prev < mid) {
                removed++;
            }

            if (removed > n) {
                // 제거 가능한 바위 수(n)를 초과하면 거리(mid)를 줄임
                high = mid - 1;
            } else {
                // 거리(mid)가 유효하면 결과를 저장하고, 거리(mid)를 늘림
                answer = mid;
                low = mid + 1;
            }
        }

        return answer;
    }
}
