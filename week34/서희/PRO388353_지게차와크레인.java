import java.util.*;

class Solution {
    
    static class Path {
        int r, c;
        
        Path(int r, int c) { 
            this.r = r;
            this.c = c;
        }
    }
    
    static int n;
    static int m;
    static String[][] storage;
    
    public int solution(String[] inputStorage, String[] requests) {
        n = inputStorage.length + 2;
        m = inputStorage[0].length() + 2;
        storage = new String [n][m];
        
        // inputStorage를 storage 배열 형식에 맞게 저장
        for(int r=0; r<n-2; r++) {
            String[] split = inputStorage[r].split("");
            
            for(int c=0; c<m-2; c++) {
                storage[r+1][c+1] = split[c];
            }
        }
        
        // 요청에 따라 출고 진행
        for(String request : requests) {
            if(request.length() == 1) {
                forklift(request);
            } else {
                crane(request.charAt(0)+"");
            }
        }
        
        int answer = count();
        return answer;
    }
    
    /**
        지게차로 창고에서 접근이 가능한 해당 종류의 컨테이너를 모두 꺼낸다.
    */
    public void forklift(String type) {
        String[][] originalStorage = copyStorage();
        boolean[][] visited = new boolean [n][m];
        
        Queue<Path> queue = new LinkedList<>();
        queue.add(new Path(0, 0));
        visited[0][0] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!queue.isEmpty()) {
            Path path = queue.poll();
            
            for(int d=0; d<4; d++) {
                int nr = path.r + dr[d];
                int nc = path.c + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                
                // 외부인 부분이 있으면 queue에 추가
                if(originalStorage[nr][nc] == null) {
                    queue.add(new Path(nr, nc));
                    continue;
                }
                
                // 외부와 연결된 컨테이너를 찾으면 출고 처리
                if(originalStorage[nr][nc].equals(type)) {
                    storage[nr][nc] = null;
                }
            }
        }
    }
    
    /**
        storage 배열 복사
    */
    public String[][] copyStorage() {
        String[][] copiedStorage = new String [n][m];
        for(int r=0; r<n; r++) {
            System.arraycopy(storage, 0, copiedStorage, 0, storage.length);
        }
        return copiedStorage;
    }
        
    /**
        크레인을 사용해서 창고 외부와 연결되지 않은 컨테이너도 꺼낼 수 있다.
    */
    public void crane(String type) {
        for(int r=0; r<n; r++) { 
            for(int c=0; c<m; c++) {
                if(storage[r][c] != null && storage[r][c].equals(type)) {
                    storage[r][c] = null;
                }
            }
        }
    }
    
    /**
        남은 컨테이너 수 세기
    */
    public int count() {
        int count = 0;
        
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                if(storage[r][c] == null) continue;
                
                count++;
            }
        }
        
        return count;
    }
}