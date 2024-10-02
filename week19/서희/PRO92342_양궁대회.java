import java.util.*;

/** 풀이
* 같은 점수 과녁에 라이언이 어피치보다 화살 한 개만 더 꽂으면 라이언이 점수를 얻을 수 있다.
*  => 라이언이 n개의 화살을 꽂을 때마다 라이언과 어피치 점수를 구하고 차이값을 계산한다.
*  => 이 차이가 최대가 될 때마다 최댓값을 갱신해주고, 정답배열에 현재 배열을 복사해둔다.
*/

class Solution {
    
    static private int[] res = new int [11];    // 점수차가 최대일때 라이언이 쏜 화살배열
    static private int[] lion = {-1};           // 정답배열
    static private int max = Integer.MIN_VALUE; // 최댓값
    
    public int[] solution(int n, int[] info) {
        back(0, n, info);   // 라이언이 쏜 화살에 대한 조합
        
        if(max == -1) {
            lion = new int [1];
            lion[0] = -1;
        }
        
        return lion;
    }
    
    public static void back(int depth, int n, int[] info) {
        // 화살 다 꽂았을 때 (종료조건)
        if(depth == n) {
            int diff = score(info);     // 점수차 구하기
            if(max <= diff) {           // 점수차 최댓값 갱신
                max = diff;
                lion = res.clone();
            }
            return;
        }
        
        // res[i] <= info[i] -> i 과녁에 라이언이 화살을 더 많이 맞추면 반복문 종료
        for(int i=0; i<info.length && res[i]<=info[i]; i++) {
            res[i] += 1;
            back(depth+1, n, info);
            res[i] -= 1;
        }
    }
    
    // 점수차 구하기
    public static int score(int[] info) {
        int apeach = 0;
        int lion = 0;
        
        for(int i=0; i<res.length; i++) {
            if(info[i] == 0 && res[i] == 0) {
                // i 원소에 둘 다 0개 맞췄을 땐 무시
                continue;   
            }
            
            if(info[i] >= res[i]) {
                apeach += (10-i);
            } else {
                lion += (10-i);
            }
        }
        
            
        int diff = lion - apeach;
            
        if(diff <= 0) {
            return -1;
        }
            
        return diff;
    }
}