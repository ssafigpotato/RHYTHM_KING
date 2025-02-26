import java.util.*;
class Solution {
   
    static int[] server; // 각 시간대별 서버 수 
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        server = new int[24];
        
        for(int i =0; i < players.length; i++){
//             // m명 미만이면 서버 증설 필요 없으므로 m보다 작으면 신경 쓸 필요 없음!! 
            
//             if(players[i] >= m){ // 현시각 이용자수가 m보다 크다면
                // players[i] / m -> 이만큼 서버가 필요!
                // 따라서 필요서버 - 현재 운영되고 있는 서버 = 증설된 서버! 
                // i시간대에 서버를 증설하면 i ~ i+k-1까지 +증설된 서버 
                int need = players[i] / m;
                int add = Math.max(0,need-server[i]);
                answer += add;  // 갱신되기 전 값을 빼줘야함! 
                // System.out.println("server[i]: "+server[i]+"이고, "+i+"시간대에: "+Math.max(0,need-server[i])+"만큼 증설해서 현재까지 증설한 서버는: "+answer);
                
                for(int j = i; j <i+k; j++){
                    if(j <= 23){ // 범위를 넘지않는 선에서 
                        server[j] += add;
                    }
                }
            // }
        }
        return answer;
    }
}