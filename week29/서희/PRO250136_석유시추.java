import java.util.*;

class Solution {
    
    static int[][] land;
    static boolean[][] visited;
    
    class Path {
        int r, c;
        
        Path(int r, int c) { 
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] land) {
        this.land = land;
        int landR = land.length;
        int landC = land[0].length;
        
        int[] amountArr = new int [landC];
        
        // 1. 석유가 있는 위치 찾기
        visited = new boolean [landR][landC];
        
        for(int r=0; r<landR; r++) {
            for(int c=0; c<landC; c++) {
                // 2. 석유 위치를 찾았으면 석유 덩어리 크기 구하기
                if(land[r][c] == 1 && !visited[r][c]) {
                    boolean[] visitedCol = new boolean[landC];
                    int amount = bfs(r, c, visitedCol);
                    
                    // 3. 석유 덩어리가 포함된 열이라면 석유 덩어리 크기만큼 더해주기
                    for(int i=0; i<landC; i++) {
                        if(visitedCol[i]) {
                            amountArr[i] += amount;
                        }
                    }
                }
            }
        }
        
        // 4. 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량 구하기
        int max = 0;
        for(int amount : amountArr) {
            max = (amount > max) ? amount : max;
        }
        
        return max;
    }
    
    /**
        석유 덩어리 크기 구하기 (BFS)
    */
    public int bfs(int r, int c, boolean[] visitedCol) {
        visited[r][c] = true;
        visitedCol[c] = true;
        
        Queue<Path> queue = new LinkedList<>();
        queue.add(new Path(r, c));
        int amount = 1;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!queue.isEmpty()) {
            Path path = queue.poll();
            
            for(int d=0; d<4; d++) {
                int nr = path.r + dr[d];
                int nc = path.c + dc[d];
                
                // 땅 크기를 벗어났을 경우 pass
                if(nr < 0 || nr >= visited.length || nc < 0 || nc >= visited[0].length || visited[nr][nc]) continue;
                
                // 이미 방문했던 위치거나 석유가 없다면 pass
                if(visited[nr][nc] || land[nr][nc] == 0) continue;
                
                amount++;
                visited[nr][nc] = true;
                visitedCol[nc] = true;
                queue.add(new Path(nr, nc));
            }
        }
        
        return amount;
    }
}