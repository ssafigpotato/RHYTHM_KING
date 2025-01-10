import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        // Solution)
        // 구해야할 것 : 
        // 모든 점을 연결하는 허브? 라우터 같은 존재(정점)의 번호
        // 도넛, 막대, 8자 그래프 수
        // 의미 : 
        // 막대는 한 방향으로 쭉 가서 돌아오지 않는 그래프
        // 도넛은 출발해서 결국 자신으로 돌아오는 그랲
        // 8자 그래프는 자신으로 돌아오는데 중간에 한 번 더 거치는 지점(정점)이 있음 
        
        // 1. 시간초과날듯
        // 정점의 번호를 이분탐색으로 찾고
        // 그 정점에서 출발해서 여러 그래프를 돌면서 어떤 그래프인지 탐색하면 되지 않을까? 
        
        // 2. 다른 방법: 정점은 보통 진출차수가 가장 높음 적어도 2개 이상
        // 진출차수를 구해서 가장 높은 노드를 정점으로 삼고 거기서부터 출발해서 아래 내용 검증 
        // 도넛이나 막대면 진출차수 1개, 8자면 2개 
        // 도넛: 노드 == 간선, 막대: 노드 == 간선 + 1, 8자: 이외   
        
        
        // 1. 진출차수 개수 구하기 
        // 시작하는 정점과 그 때 도달하는 노드들 저장하는 List배열
        int hub = 0; // 정점인 노드
        int hub_max = 0;  // 그 때의 진출 차수 
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        // edges 배열의 원소 e를 돌면서 
        for(int[] e :  edges){
            int from = e[0];
            int to = e[1];
            
            // putIfAbsent 대신 put을 써버리면 List가 계속 초기화 됨
            // 진출 정점 리스트 초기화 
            map.putIfAbsent(from, new ArrayList<>());
            // 각 from에 to 추가 
            // map.get(from)으로 List가 호출되고, 거기에 to 계속 추가  
            map.get(from).add(to);
            
            // List의 사이즈(진출 차수) 제일 큰 값으로 갱신
            if(hub_max <= map.get(from).size()){
                hub = from;
                hub_max =  map.get(from).size();
            } 
        }
       System.out.println(hub);
        int[] answer = {};
        return answer;
    }
}