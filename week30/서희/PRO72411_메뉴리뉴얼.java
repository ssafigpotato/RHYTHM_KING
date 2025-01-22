import java.util.*;
import java.util.Map.Entry;

class Solution {
    
    static HashMap<String,Integer> map; 
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        // 1. 각 문자열을 오름차순으로 정렬하여 저장
        for(int i=0; i<orders.length; i++) {
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr);
        }
        
        // 2. course 길이만큼 반복하여 가능한 조합 계산
        for(int i=0; i<course.length; i++) {
            map = new HashMap<>();
            int max = Integer.MIN_VALUE;    // 만들어진 조합들 중 가장 많이 주문된 횟수를 저장
            
            // 3. 각 사람들의 조합을 구하기 위해 탐색
            for(int j=0; j<orders.length; j++) {
                StringBuilder sb = new StringBuilder(); 
                if(course[i] <= orders[j].length()) {
                    combi(orders[j], sb, 0, 0, course[i]);
                }
            }
            
            // 4. 가장 많이 주문한 횟수를 max에 저장
            for(Entry<String,Integer> entry : map.entrySet()){
                max = Math.max(max,entry.getValue());
            }
            
            // 5. 최소 2번 이상 주문된 조합이면서, 해당 횟수와 일치하는 조합은 ArrayList에 삽입
            for(Entry<String, Integer> entry : map.entrySet()) {
                if(max >= 2 && entry.getValue() == max) {
                    answer.add(entry.getKey());
                }
            }
        }
        
        Collections.sort(answer);
        
        return answer;
    }
    
    /** 
        조합을 구하는 메소드
    */
    public static void combi(String str, StringBuilder sb, int idx, int cnt, int n) {
        // 각 코스요리의 개수만큼 조합이 되면, map에 해당 조합을 삽입하고 카운팅
        if(cnt == n) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }
        
        // 조합 구하기
        for(int i=idx; i<str.length(); i++) {
            sb.append(str.charAt(i));
            combi(str, sb, i+1, cnt+1, n);
            sb.delete(cnt, cnt+1);  // 돌려놓기
        }
    }
}