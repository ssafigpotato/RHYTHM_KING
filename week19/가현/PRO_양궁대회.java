class Solution {
    // maxDiff: 라이언과 어피치의 점수 차이 중 최대 값을 저장
    private int maxDiff = Integer.MIN_VALUE;

    // bestShot: 라이언이 가장 큰 점수 차이로 우승할 수 있는 화살 배분 상태를 저장. 
    // 초기값은 [-1]로 설정해 라이언이 우승할 수 없을 경우를 처리.
    private int[] bestShot = {-1};

    // solution 함수: n은 라이언이 쏠 화살의 개수, info는 어피치의 화살 배분 상태를 의미
    public int[] solution(int n, int[] info) {
        // ryanShot: 라이언의 화살 배분 상태를 저장할 배열 (0~10점까지 총 11개의 점수)
        int[] ryanShot = new int[11];

        // 백트래킹 시작: 남은 화살의 개수 n, 탐색 시작 index 0, 초기 라이언의 화살 배분 상태 ryanShot, 어피치의 상태 info 전달
        backtrack(n, 0, ryanShot, info);

        // 가장 큰 점수 차이로 우승할 수 있는 최적의 화살 배분 상태를 반환
        return bestShot;
    }

    // backtrack 함수: 백트래킹을 통해 가능한 모든 화살 배분을 시도하고, 그 중 최적의 상태를 찾아냄
    // remainingArrows는 남은 화살의 개수를 의미, index는 현재 탐색하고 있는 점수대 (0~10점), 
    // ryanShot은 현재 라이언의 화살 배분 상태, apeachInfo는 어피치의 화살 배분 상태를 나타냄
    private void backtrack(int remainingArrows, int index, int[] ryanShot, int[] apeachInfo) {
        // 기저 조건: 모든 점수대를 다 탐색한 경우 (index == 11일 때)
        if (index == 11) {
            // 남은 화살이 있다면, 이를 모두 0점(가장 낮은 점수대)에 쏨
            if (remainingArrows > 0) {
                ryanShot[10] += remainingArrows; // 남은 화살을 모두 0점에 추가
            }

            // 현재 상태의 라이언과 어피치의 점수를 계산하고, 최적의 화살 배분 상태를 갱신할지 결정
            calculateScore(ryanShot, apeachInfo);

            // 남은 화살을 모두 쏘았던 상태를 원래대로 복구 (백트래킹 과정)
            if (remainingArrows > 0) {
                ryanShot[10] -= remainingArrows; // 복구
            }
            return; // 탐색 종료
        }

        // 라이언이 현재 점수대 (index)에서 어피치보다 더 많은 화살을 맞히는 경우
        // 남은 화살의 개수가 어피치가 쏜 화살보다 더 많을 때만 이 선택이 가능
        if (remainingArrows > apeachInfo[index]) {
            // 라이언이 어피치보다 한 발 더 많이 쏨
            ryanShot[index] = apeachInfo[index] + 1;

            // 백트래킹으로 다음 점수대를 탐색, 남은 화살 개수는 줄어듦
            backtrack(remainingArrows - ryanShot[index], index + 1, ryanShot, apeachInfo);

            // 탐색 후 복구 (백트래킹 과정)
            ryanShot[index] = 0;
        }

        // 라이언이 현재 점수대를 포기하고 넘어가는 경우 (화살을 쏘지 않음)
        // 다음 점수대를 탐색
        backtrack(remainingArrows, index + 1, ryanShot, apeachInfo);
    }

    // calculateScore 함수: 현재 라이언의 화살 배분 상태로 어피치와 점수를 비교하여 점수를 계산
    // 각 점수대를 탐색하며, 라이언과 어피치 중 누가 해당 점수대를 가져가는지를 판단함
    private void calculateScore(int[] ryanShot, int[] apeachInfo) {
        // 라이언과 어피치의 최종 점수를 기록할 변수
        int ryanScore = 0, apeachScore = 0;

        // 각 점수대(10점부터 0점까지)를 순차적으로 비교
        for (int i = 0; i < 11; i++) {
            // 라이언이 어피치보다 더 많은 화살을 맞혔을 경우, 해당 점수대를 라이언이 가져감
            if (ryanShot[i] > apeachInfo[i]) {
                ryanScore += 10 - i; // 해당 점수대를 라이언의 점수에 추가
            }
            // 어피치가 화살을 맞혔고, 라이언과 맞힌 개수가 같거나 적은 경우, 어피치가 점수를 가져감
            else if (apeachInfo[i] > 0) {
                apeachScore += 10 - i; // 해당 점수대를 어피치의 점수에 추가
            }
            // 둘 다 화살을 맞히지 못한 경우는 어느 쪽도 점수를 가져가지 않음
        }

        // 라이언과 어피치의 최종 점수 차이를 계산
        int scoreDiff = ryanScore - apeachScore;

        // 라이언이 어피치보다 더 큰 점수로 이겼을 경우, 또는 더 나은 조건으로 이겼을 경우 최적의 상태를 갱신
        if (scoreDiff > maxDiff) {
            maxDiff = scoreDiff;  // 최대 점수 차이를 갱신
            bestShot = ryanShot.clone();  // 현재 라이언의 화살 배분 상태를 최적의 상태로 갱신
        }
        // 점수 차이가 동일할 경우, 더 낮은 점수를 많이 맞힌 경우를 선택
        else if (scoreDiff == maxDiff) {
            // 배열을 뒤에서부터 (낮은 점수부터) 비교하면서 우선순위를 결정
            for (int i = 10; i >= 0; i--) {
                if (ryanShot[i] > bestShot[i]) {
                    bestShot = ryanShot.clone();  // 더 많은 낮은 점수를 맞힌 상태로 갱신
                    break;
                } else if (ryanShot[i] < bestShot[i]) {
                    break;  // 이 경우는 낮은 점수를 덜 맞혔으므로 더 이상 비교할 필요가 없음
                }
            }
        }
    }
}
