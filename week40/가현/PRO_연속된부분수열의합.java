class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;

        // 투 포인터 시작 위치
        int start = 0, end = 0;

        // 현재 부분 수열의 합
        int sum = sequence[0];

        // 현재까지 찾은 가장 짧은 수열의 길이
        int minLen = Integer.MAX_VALUE;

        // 정답으로 반환할 시작 인덱스, 끝 인덱스
        int[] answer = new int[2];

        // end가 수열 끝까지 도달할 때까지 반복
        while (start < n && end < n) {
            if (sum == k) {
                // 부분합이 정확히 k일 경우
                int len = end - start + 1;

                // 더 짧은 수열을 발견하면 업데이트
                if (len < minLen) {
                    minLen = len;
                    answer[0] = start;
                    answer[1] = end;
                }

                // 다음 탐색 위해 왼쪽 포인터 이동 (길이 줄이기)
                sum -= sequence[start++];
            } else if (sum < k) {
                // 아직 k보다 작으면 오른쪽 확장해서 더해줌
                end++;
                if (end < n) sum += sequence[end];
            } else {
                // k보다 크면 왼쪽 포인터 줄여서 합 줄이기
                sum -= sequence[start++];
            }
        }

        return answer;
    }
}
