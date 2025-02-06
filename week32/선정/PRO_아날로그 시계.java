class Solution {
    // 각도가 0도일 경우 360도로 변환하는 함수
    public static double calc(double x) {
        // 0도일 경우 360도로 변환 (0도와 360도는 같은 위치이므로)
        if (x == 0) {
            return 360;
        }
        return x;
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 시계의 바늘이 움직이는 속도
        // 초침: 1초에 360 / 60 = 6도 이동
        // 분침: 1분에 360 / 60 = 6도 → 1초에 6 / 60 = 0.1도 이동
        // 시침: 12시간에 360도 → 1시간에 360 / 12 = 30도 이동
        //      1시간 = 60분 → 1분에 30 / 60 = 0.5도 이동 → 1초에 0.5 / 60 = 1/120도 이동

        int answer = 0;
        // 시작 시간을 초 단위로 변환
        double start = h1 * 3600 + m1 * 60 + s1;
        // 종료 시간을 초 단위로 변환
        double end = h2 * 3600 + m2 * 60 + s2;

        // 0시 0분 0초 또는 12시 0분 0초인 경우 알람이 한 번 울림
        if (start == 0 || start == 12 * 3600) {
            answer += 1;
        }

        // start부터 end까지 1초씩 증가하며 확인
        while (start < end) {
            // 현재 초, 분, 시침의 각도를 계산
            double h = start / 120 % 360;  // 시침 각도 (1초에 1/120도)
            double m = start / 10 % 360;   // 분침 각도 (1초에 0.1도)
            double s = start * 6 % 360;    // 초침 각도 (1초에 6도)

            // 다음 초의 초침, 분침, 시침 각도를 계산
            double nh = calc((start + 1) / 120 % 360); // 다음 초의 시침 각도
            double nm = calc((start + 1) / 10 % 360);  // 다음 초의 분침 각도
            double ns = calc((start + 1) * 6 % 360);   // 다음 초의 초침 각도

            // 초침과 시침이 만나는 경우 체크
            if (s < h && nh <= ns) { 
                answer += 1;
            }

            // 초침과 분침이 만나는 경우 체크
            if (s < m && nm <= ns) { 
                answer += 1;
            }

            // 초침, 분침, 시침이 동시에 겹치는 경우
            if (ns == nh && nh == nm) { 
                answer -= 1; // 위 두 개의 조건에서 중복으로 세어졌으므로 한 번 빼줌
            }

            // 1초 증가
            start += 1;
        }

        return answer;
    }
}
