class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0, totalSum = 0;
        for (int num : queue1) sum1 += num; // queue1의 합 계산
        for (int num : queue2) sum2 += num; // queue2의 합 계산
        totalSum = sum1 + sum2; // 두 큐의 총합

        // 총합이 홀수라면 두 큐의 합을 같게 만들 수 없음
        if (totalSum % 2 != 0) return -1;

        long targetSum = totalSum / 2; // 두 큐가 가져야 할 목표 합
        int n = queue1.length; // 두 큐의 길이
        int[] mergedQueue = new int[n * 2]; // queue1과 queue2를 합친 배열 생성

        System.arraycopy(queue1, 0, mergedQueue, 0, n); // queue1 복사
        System.arraycopy(queue2, 0, mergedQueue, n, n); // queue2 복사

        int left = 0, right = n; // 투 포인터 사용 (queue1은 [0, n-1], queue2는 [n, 2n-1])
        long currentSum = sum1; // 현재 첫 번째 큐의 합
        int operations = 0; // 작업 횟수 카운트

        while (left <= right && right < mergedQueue.length) {
            if (currentSum == targetSum) { // 두 큐의 합이 같아진 경우
                return operations;
            } else if (currentSum < targetSum) {
                // currentSum이 작으면 오른쪽에서 원소를 추가 (queue2에서 queue1으로 이동)
                currentSum += mergedQueue[right];
                right++;
            } else {
                // currentSum이 크면 왼쪽에서 원소를 제거 (queue1에서 queue2로 이동)
                currentSum -= mergedQueue[left];
                left++;
            }
            operations++; // 작업 횟수 증가
        }

        return -1; // 두 큐의 합을 같게 만들 수 없는 경우
    }
}