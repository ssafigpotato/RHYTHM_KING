class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        double start = h1 * 3600 + m1 * 60 + s1;
        double end = h2 * 3600 + m2 * 60 + s2;
        
        if(start == 0 || start == 12 * 3600) {
            answer += 1;
        }
        
        while (start < end) {
            // 각도를 기준으로 계산
            double h = start / 120 % 360;
            double m = start / 10 % 360;
            double s = start * 6 % 360;
            
            // 다음 각도 계산
            double nh = calc((start+1) / 120 % 360);
            double nm = calc((start+1) / 10 % 360);
            double ns = calc((start+1) * 6 % 360);
            
            // 초침과 시침이 겹치는 경우
            if((s<h) && (nh<=ns)) {
                answer += 1;
            }
            
            // 초침과 분침이 겹치는 경우
            if((s<m) && (nm<=ns)) {
                answer += 1;
            }
            
            // 초침, 시침, 분침이 모두 겹치는 경우
            if((ns == nh) && (nh == nm)) {
                answer -= 1;
            }
            
            start++;
        }
        
        return answer;
    }
    
    public static double calc(double x) {
        if(x == 0) {
            return 360;
        }
        
        return x;
    }
}