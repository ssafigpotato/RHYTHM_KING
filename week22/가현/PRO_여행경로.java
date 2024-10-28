import java.util.*;
class Solution {
    
    private List<String> path = new ArrayList<>();
    private Map<String, PriorityQueue<String>> destination = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        
        for(String[] ticket: tickets ){
            String departure = ticket[0];
            String arrival = ticket[1];
            destination.putIfAbsent(departure, new PriorityQueue<>());
            destination.get(departure).offer(arrival);
        }
        
        dfs("ICN");
        
        Collections.reverse(path);
        // toArray() 사용법 문제: toArray() 메서드를 사용할 때는 배열의 타입을 명시해야 합니다. path.toArray(new String[0])로 수정해야 합니다.
        return path.toArray(new String[0]);
        
    }
    
    // dfs 메서드에서 path와 destination에 접근하려면, private 메서드로 변경하고, 클래스 필드에 접근할 수 있도록 수정해야 합니다.
    private void dfs(String airport){
        
        while(destination.containsKey(airport) && !destination.get(airport).isEmpty()){
            String next = destination.get(airport).poll();
            dfs(next);
        }
        
        path.add(airport);
    }
}