import java.util.*;

class Solution {

    public static final int[] dx = {-1, 1, 0, 0};  // 행 이동
    public static final int[] dy = {0, 0, -1, 1};  // 열 이동
    
    public int solution(int[][] land) {
        int n = land.length;    // 땅의 세로 길이
        int m = land[0].length; // 땅의 가로 길이
        
        // 각 석유 덩어리의 정보를 저장할 Map (덩어리 ID -> 크기)
        Map<Integer, Integer> oilSizes = new HashMap<>();
        // 각 위치가 어느 덩어리에 속하는지 저장할 배열 (-1로 초기화)
        int[][] oilGroups = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(oilGroups[i], -1);
        }
        
        // 석유 덩어리 ID
        int groupId = 0;
        
        // 모든 위치를 순회하면서 석유 덩어리 찾기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 석유가 있고 아직 그룹에 속하지 않은 경우
                if(land[i][j] == 1 && oilGroups[i][j] == -1) {
                    // BFS로 연결된 석유 덩어리 찾기
                    int size = bfs(land, oilGroups, i, j, groupId, n, m);
                    oilSizes.put(groupId, size);
                    groupId++;
                }
            }
        }
        
        // 각 열에서 얻을 수 있는 석유량 계산
        int maxOil = 0;
        for(int j = 0; j < m; j++) {
            // 현재 열에서 접근 가능한 석유 덩어리들을 Set으로 관리
            Set<Integer> groups = new HashSet<>();
            // 열의 모든 행을 확인
            for(int i = 0; i < n; i++) {
                if(oilGroups[i][j] != -1) {
                    groups.add(oilGroups[i][j]);
                }
            }
            
            // 현재 열에서 얻을 수 있는 총 석유량 계산
            int currentOil = 0;
            for(int group : groups) {
                currentOil += oilSizes.get(group);
            }
            
            // 최대값 갱신
            maxOil = Math.max(maxOil, currentOil);
        }
        
        return maxOil;
    }
    
    // BFS
    public int bfs(int[][] land, int[][] oilGroups, int startX, int startY, 
                   int groupId, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        oilGroups[startX][startY] = groupId;
        int size = 1;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                
                // 범위 check~!
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && 
                   land[nx][ny] == 1 && oilGroups[nx][ny] == -1) {
                    queue.offer(new int[]{nx, ny});
                    oilGroups[nx][ny] = groupId;
                    size++;
                }
            }
        }
        
        return size;
    }
}
