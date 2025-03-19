import java.util.*;

class Solution {
    
    static int n;
    static int[][] connect;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        
        // 1. 모든 전선 연결
        connect = new int [n+1][n+1];
        for(int i=0; i<wires.length; i++) {
            int[] wire = wires[i];
            connect[wire[0]][wire[1]] = connect[wire[1]][wire[0]] = 1;
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<wires.length; i++) {
            visited = new boolean [n+1];
            
            // 2. 하나씩 전선 끊기
            int[] wire = wires[i];
            connect[wire[0]][wire[1]] = connect[wire[1]][wire[0]] = 0;
            
            // 3. 두 전력망이 가지고 있는 송전탑 개수 차이 계산
            int cnt1 = count();
            int cnt2 = count();
            int gap = Math.abs(cnt1 - cnt2);
            
            // 4. 최솟값 갱신
            min = Math.min(min, gap);
            
            // 5. 끊은 전선 다시 붙이기
            connect[wire[0]][wire[1]] = connect[wire[1]][wire[0]] = 1;
        }
        
        return min;
    }
    
    /**
        각 전력망이 갖게 되는 송전탑 개수 계산
    */
    private int count() {
        int cnt = 0;
        
        // 시작 지점 찾기 (방문한 적이 없는 송전탑 번호)
        int start = 0;
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                start = i;
                break;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        cnt++;
        
        while(!queue.isEmpty()) {
            int p1 = queue.poll();
            
            for(int p2=1; p2<=n; p2++) {
                if(connect[p1][p2] != 1 || visited[p2]) continue;
                
                queue.add(p2);
                visited[p2] = true;
                cnt++;
            }
        }
        
        return cnt;
    }
}