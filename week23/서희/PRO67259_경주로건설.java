import java.util.*;

class Solution {

    static class Path {
        int r, c, d, cost;

        Path(int r, int c, int d, int cost) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] visited = new int[n][n][4];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<Path> queue = new LinkedList<>();
        queue.add(new Path(0, 0, -1, 0));  // 초기 비용 0, 방향 -1로 설정
        visited[0][0][0] = 0;
        
        int[] dr = {-1, 1, 0, 0};  // 상, 하, 좌, 우
        int[] dc = {0, 0, -1, 1};

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Path path = queue.poll();

            if (path.r == n - 1 && path.c == n - 1) {
                answer = Math.min(answer, path.cost);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = path.r + dr[d];
                int nc = path.c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 1) continue;

                int newCost = path.cost + (path.d == d || path.d == -1 ? 100 : 600);
                
                if (newCost < visited[nr][nc][d]) {
                    visited[nr][nc][d] = newCost;
                    queue.add(new Path(nr, nc, d, newCost));
                }
            }
        }

        return answer;
    }
}
