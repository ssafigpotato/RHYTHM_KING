// test result: 3/7

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // (1) 정답을 저장할 변수
        int count = 0;
        
        // (2) 입력값을 초 단위로 변환 (시 * 3600 + 분 * 60 + 초)
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        // (3) 이전 바늘의 각도를 저장할 변수 (초기값 -1)
        double prevSecAngle = -1;
        double prevMinAngle = -1;
        double prevHourAngle = -1;

        // (4) 초 단위로 탐색
        for (int t = startTime; t < endTime; t++) {
            // (5) 현재 시, 분, 초 계산
            int hour = (t / 3600) % 12;  // 12시간 형식 유지
            int min = (t / 60) % 60;
            int sec = t % 60;

            // (6) 현재 각도 계산
            double secAngle = sec * 6.0;  // 초침: 1초당 6도 이동
            double minAngle = min * 6.0 + sec * 0.1;  // 분침: 1분당 6도 + 1초당 0.1도 이동
            double hourAngle = hour * 30.0 + min * 0.5 + sec * (1.0 / 120);  // 시침: 1시간당 30도 + 1분당 0.5도 + 1초당 (1/120)도 이동

            // (7) 초침이 시침과 겹치는지 확인
            boolean secMatchesHour = (prevHourAngle <= secAngle && secAngle < hourAngle) || 
                                     (prevHourAngle >= secAngle && secAngle > hourAngle);

            // (8) 초침이 분침과 겹치는지 확인
            boolean secMatchesMin = (prevMinAngle <= secAngle && secAngle < minAngle) || 
                                    (prevMinAngle >= secAngle && secAngle > minAngle);
            
            // (9) 알람이 울리는 경우 카운트 증가
            if (secMatchesHour || secMatchesMin) {
                count++;
            }

            // (10) 12:00:00 정각일 경우, 모든 바늘이 겹치므로 1번만 카운트
            if (t == startTime && secAngle == minAngle && secAngle == hourAngle) {
                count++;
            }

            // (11) 현재 초침, 분침, 시침의 위치를 저장하여 다음 반복에서 비교
            prevSecAngle = secAngle;
            prevMinAngle = minAngle;
            prevHourAngle = hourAngle;
        }

        // (12) 최종 알람 울린 횟수 반환
        return count;
    }
}
