import java.util.*;

class Solution {
    
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        for(int i=0; i<info.length; i++) {
            dfs(info[i].split(" "), "", 0);
        }
        
        int n = query.length;
        int[] answer = new int [n];
        
        // 코딩 테스트 점수를 기준으로 오름차순 정렬
        for(ArrayList<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        for(int i=0; i<n; i++) {
            answer[i] = binarySearch(query[i]);
        }
        
        return answer;
    }
    
    /**
        가능한 모든 문자열 조합하기 (key: 문자열 / value : 코딩 테스트 점수)
    */
    private void dfs(String[] info, String str, int depth) {
        if(depth == 4) {
            int score = Integer.parseInt(info[depth]);
            if(map.containsKey(str)) {
                map.get(str).add(score);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(str, list);
            }
            return;
        }
        
        dfs(info, str + "-", depth + 1);
        dfs(info, str + info[depth], depth + 1);
    }
    
    /**
        코딩테스트 점수를 이분탐색하여 조건을 만족하는 인원수 찾기
    */
    private int binarySearch(String query) {
        String[] arr = query.split(" and ");
        int score = Integer.parseInt(arr[3].split(" ")[1]);
        query = arr[0] + arr[1] + arr[2] + arr[3].split(" ")[0];
        
        if(!map.containsKey(query)) {
            return 0;
        }
        
        ArrayList<Integer> list = map.get(query);
        int start = 0;
        int end = list.size();
        
        // score 이상인 값이 처음으로 나타나는 인덱스 찾기
        while(start < end) {
            int mid = (start + end) / 2;
            
            if(list.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return list.size() - start;
    }
}