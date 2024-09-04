import java.util.*;

class Solution {
    static class location{
        int r;
        int c;
        location(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int [5];
        for(int i = 0; i <5; i++){
            // places[i]를 원소로 하는 배열 선언
            String []arr = places[i];
            
            // flag의 값을 초기에 false로 두면 안됨!!! 
            int flag = 1; 
            // System.out.println("arr은: " + Arrays.toString(arr)); // 확인
            
            for(int j = 0; j <5; j++){
                String str = arr[j];
                // System.out.println("str은: "+ str); // 확인
                
                // 현 위치가 있는 곳이 P일 때, bfs로 탐색
                for(int k = 0; k <5; k++){
                    if(str.charAt(k) == 'P'){
                        if(bfs(j, k, arr) == 0){
                            flag = 0;
                        };
                    }
                }
            }
            answer[i] = flag;
        }

        
        return answer;
    }
    
    static int bfs(int r, int c, String[] arr) {
        int []dr = {-1,0,1,0};
        int []dc = {0,1,0,-1};
        
        Queue<location> que = new LinkedList<>();
        que.offer(new location(r,c));
        boolean[][] visited  = new boolean[5][5];
        visited[r][c] = true;
        
        
        while(!que.isEmpty()){
            location curr = que.poll();
            
            for(int d = 0; d < 4; d++){
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                
                // 맨하튼 거리 선언
                // 두 점 사이의 수평 및 수직 이동 거리의 합. ∣p1−q1∣+∣p2−q2∣
                int len = Math.abs(r-nr) + Math.abs(c-nc);
                
                // 맨하튼 거리가 2 초과면 통과
                if(len > 2) continue;
                // 범위를 벗어나면 통과
                if(nr<0 || nr >=5 || nc <0 || nc >=5) continue;
                // 방문했던 곳이면 통과
                if(visited[nr][nc] == true) continue;
              
                
                // 새위치(nr,nc)가 있는 곳이 P이면서 거리가 2이하면
                if(arr[nr].charAt(nc) == 'P' && len <= 2) {
                    return 0;    
                // 또는 새위치가 O이면서 바로 옆에 있는 경우에는 다시 한번 탐색해보기!
                }else if(arr[nr].charAt(nc) == 'O' && len < 2){
                    visited[nr][nc] = true;
                    que.offer(new location(nr,nc));
                }
                // X일때는 뭐가 와도 괜찮으니까 굳이 탐색 x
                // else { return 1; } // 이거 하면 틀리게 나옴... 탐색 임의로 멈추게하니까! 그냥 아무 작업도 하지 않고 다음 반복문으로 넘어가게 해야함
             
                
            }
        }
        return 1;
    }
    
}