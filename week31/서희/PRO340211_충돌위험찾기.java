import java.util.*;

class Solution {
    
    static int num;                 // 로봇의 수
    static Queue<int[]>[] path;     // 이동 경로 관리
    
    public int solution(int[][] points, int[][] routes) {
        num = routes.length;
        path = new LinkedList[num];
        for(int i=0; i<num; i++) {
            path[i] = new LinkedList<>();
        }
        
        move(points, routes);
        int answer = risk();
        
        return answer;
    }
    
    /**
        경로 계산
    */
    public static void move(int[][] points, int[][] routes) {
        for(int i=0; i<num; i++) {
            // 시작위치
            int[] point = points[routes[i][0] - 1];
            int x = point[0];
            int y = point[1];
            path[i].add(new int []{x, y});
            
            for(int j=1; j<routes[0].length; j++) {
                // 이동해야 되는 위치
                int[] np = points[routes[i][j] - 1];
                int nx = np[0];
                int ny = np[1];
                
                // 이동 간격 계산
                int xp = nx - x;
                int yp = ny - y;
                
                // 계속 이동해주면서 path(이동 경로)에 저장
                // r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 진행
                while(xp != 0) {
                    if(xp > 0) {
                        xp--;
                        x++;
                        path[i].add(new int[]{x, y});
                    } else {
                        xp++;
                        x--;
                        path[i].add(new int[]{x, y});
                    }
                }
                
                while(yp != 0) {
                    if(yp > 0) {
                        yp--;
                        y++;
                        path[i].add(new int[]{x, y});
                    } else {
                        yp++;
                        y--;
                        path[i].add(new int[]{x, y});
                    }
                }
            }
        }
    }
    
    /**
        충돌 계산
    */
    public static int risk() {
        int answer = 0;
        
        while(true) {
            int[][] map = new int [101][101];
            int cnt = 0;        // 이동 완료한 로봇의 수
            
            for(int i=0; i<num; i++) {
                if(path[i].isEmpty()) {
                    cnt++;
                    continue;
                }
                
                int[] temp = path[i].poll();
                map[temp[0]][temp[1]]++;
            }
            
            // 충돌된 경우 찾기
            for(int i=0; i<101; i++) {
                for(int j=0; j<101; j++) {
                    if(map[i][j] > 1) {
                        answer++;
                    }
                }
            }
            
            // 모든 로봇이 이동을 완료했다면 종료
            if(cnt == num) break;
        }
        
        return answer;
    }
}