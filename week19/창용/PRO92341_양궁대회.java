import java.util.*;

// 기본적인 백트래킹 조합이 필요한 문제
// 단, 어디서 끊어야할지 파악할 수 있어야 한다.

class Solution {
    private static int[] lionTarget = new int[11];
    private static int[] lion = {-1};
    private static int max = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        combination(0, n, info);

        if(max == -1){
            lion = new int[1];
            lion[0] = -1;
        }

        return lion;
    }

    // 조합 메서드
    public static void combination(int depth, int n, int[] info){
        if(depth == n){
            // 점수 계산 메서드를 사용해서 점수 차이를 도출
            int diff = calc(info);
            // 점수 차이가 최대라면 배열의 clone 메서드를 쓰자.
            if(max <= diff){
                max = diff;
                lion = lionTarget.clone();
            }
            return;
        }

        for(int i = 0; i < info.length && lionTarget[i] <= info[i]; i++){
            lionTarget[i] += 1;
            combination(depth + 1, n, info);
            lionTarget[i] -= 1;
        }
    }

    // 점수 계산 메서드
    public static int calc(int[] info){
        int apeach = 0, lion = 0;
        for(int i = 0; i < lionTarget.length; i++){
            if(info[i] == 0 && lionTarget[i] == 0){
                continue;
            }
            if(info[i] >= lionTarget[i]){
                apeach += (10 - i);
            }else{
                lion += (10 - i);
            }
        }

        int diff = lion - apeach;
        if(diff <= 0){
            return - 1;
        }
        return diff;

    }

}