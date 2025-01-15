import java.util.*;
class Solution {
    // Sol) land배열의 열 각각에 대해서 
    // land[i][j]가 1일 때 dfs 한 것들의 합을 구하고, 
    // 그 최댓값 반환하기 
    static boolean[][]visited;
    static int[]dr = {-1,0,1,0};
    static int[]dc = {0,1,0,-1};
    static int cnt;
    public int dfs(int r, int c, int n, int m, int[][]land){
        visited[r][c] = true;
        cnt++; // 재귀한 횟수 = 방문횟수 = 한 번 dfs햇을 때 얻을 수 있는 덩어리 크기 
        
        for(int d = 0; d <4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            // 1) 범위 벗어났으면 continue
            if(nr<0 || nr >= n || nc <0 || nc >= m) continue;
            // 2) 값이 1이 아니면 continue
            if(land[nr][nc] == 0) continue;
            if(!visited[nr][nc]){ // 방문하지 않았으면 
                dfs(nr,nc, n, m, land);
            }
        }
        return cnt;
    }
    public long solution(int[][] land) {
        
        int n = land.length;
        int m = land[0].length;
        
        
        
        long max = 0;
        
        // 1. land의 열에 대해서 순회 
        for(int j = 0; j <m; j++){ 
            // cnt = 0; <- 여기서 초기화 시켜주면 안됨!! 
            // List<Integer> list = new ArrayList<>(); // 각 열에서 얻을 수 있는 덩어리 집합
            long total = 0; 
            visited = new boolean[n][m]; // 각 열에서는 새로 탐색해야하니까 초기화해줘야지!
            
            // 2. 해당 열에 속하는 행을 순회하면서 
            for(int i = 0; i <n; i++){
                cnt = 0; // 여기서 덩어리 크기 초기화
                if(land[i][j] == 1 && !visited[i][j]){ // 석유이면서 방문하지 않았을 경우
                    int curr = dfs(i,j,n,m,land); // 현재 덩어리 
                    // list.add(curr); // 리스트에 추가 
                    total += curr; 
                    // System.out.println(j+"번째 열, "+i+"번째 행에서 curr: "+curr);
                }
                
            }
            
            // 3. list에 있는 덩어리들을 모두 합하면
            // 해당 열에서 얻을 수 있는 총 석유량 
            // int total = 0;
            // for(int i = 0; i < list.size(); i++){
            //     total += list.get(i);
            // }
            
            // 4. 석유량 중 가장 큰 것을 max로 갱신 
            // System.out.println(j+"번째 열에서 total 덩어리: "+total);
            max = Math.max(max, total);
        }
        
        // System.out.println(max);
        
        return max;
    }
}