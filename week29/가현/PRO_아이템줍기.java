/*
2배수의 이유유
Original coordinates (1x)
   □□□
   □□□
□□□□□
□□□□□

여기서 □는 이동 가능한 테두리를 나타냅니다. 중간에 겹치는 부분에서 테두리를 구분하기가 모호합니다.

Expanded coordinates (2x)
      □□□□□
      □□□□□
      □  □□
      □  □□
□□□□□□  □□
□□□□□□  □□
□□□□□□  □□
□□□□□□  □□

이제 테두리 사이에 공간이 생겨서 명확하게 구분이 가능합니다.
**/

import java.util.*;

class Solution {
    static class Point {
        int x, y;
        double distance;
        
        Point(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    // 방향 배열 (상하좌우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 좌표를 2배로 확장하여 테두리 판별을 용이하게 함
        int[][] map = new int[101][101];
        
        // 직사각형 영역을 2배로 확장하여 표시
        for(int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            // 직사각형 영역 채우기
            for(int i = x1; i <= x2; i++) {
                for(int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }
        
        // 직사각형 내부 영역을 0으로 표시
        for(int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for(int i = x1 + 1; i < x2; i++) {
                for(int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 0;
                }
            }
        }
        
        // BFS로 최단 경로 찾기
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        
        queue.offer(new Point(characterX * 2, characterY * 2, 0));
        visited[characterX * 2][characterY * 2] = true;
        
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            
            // 목표 지점에 도달한 경우
            if(current.x == itemX * 2 && current.y == itemY * 2) {
                return (int)(current.distance / 2);
            }
            
            // 상하좌우 이동
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                // 범위 체크 및 테두리인지 확인
                if(nx >= 0 && ny >= 0 && nx <= 100 && ny <= 100 
                   && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, current.distance + 1));
                }
            }
        }
        
        return 0;
    }
}