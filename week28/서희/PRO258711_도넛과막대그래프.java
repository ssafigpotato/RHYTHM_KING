import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        // 연결한 수, 연결받은 수를 각각의 HashMap에 저장
        HashMap<Integer, Integer> out = new HashMap<>();    // 연결한 수를 저장하기 위한 HashMap
        HashMap<Integer, Integer> in = new HashMap<>();     // 연결받은 수를 저장하기 위한 HashMap
        
        for(int i=0; i<edges.length; i++) {
            int point = edges[i][0];
            int outPoint = edges[i][1];
            
            out.put(point, out.getOrDefault(point, 0) + 1);
            in.put(outPoint, in.getOrDefault(outPoint, 0) + 1);
        }
        
        // 생성된 정점 파악 및 각 그래프 수 세기
        int makePoint = 0;      // 생성된 정점 (연결 받은 간선이 없으면서 연결한 간선은 2 이상)
        int barGraph = 0;       // 막대 모양 그래프 수 (마지막 부분은 연결받은 간선만 1, 연결한 간선의 수는 0)
        int eightGraph = 0;     // 8자 모양 그래프 수 (한 정점은 연결한 간선 2, 연결받은 간선 2)
        int DonutGraph = 0;     // 도넛 모양 그래프 수 (모든 정점이 연결한 간선 1, 연결받은 간선 1) <- 전체 그래프에서 남은 수로 계산
        
        for(int point : out.keySet()) {
            if(out.get(point) >= 2) {
                if(!in.containsKey(point)) {
                    makePoint = point;
                } else {
                    eightGraph++;
                }
            }
        }
        
        for(int point : in.keySet()) {
            if(!out.containsKey(point)) {
                 barGraph++;
            }
        }
        
        DonutGraph = out.get(makePoint) - (barGraph + eightGraph);
        
        int[] answer = {makePoint, DonutGraph, barGraph, eightGraph};
        return answer;
        
    }
}