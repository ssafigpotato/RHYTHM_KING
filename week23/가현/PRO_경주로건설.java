import java.util.*;

class Solution {
    // 상, 하, 좌, 우 방향 정의
    private static final int[] dx = {-1, 1, 0, 0}; // x 좌표 변화
    private static final int[] dy = {0, 0, -1, 1}; // y 좌표 변화

    public int solution(int[][] board) {
        int n = board.length; // 격자의 크기 (NxN)
        int INF = Integer.MAX_VALUE; // 초기 비용을 무한대로 설정

        // 비용 테이블 (상, 하, 좌, 우 각각의 방향에 대한 최소 비용 저장)
        int[][][] cost = new int[n][n][4];
        for (int[][] arr : cost) {
            for (int[] row : arr) {
                Arrays.fill(row, INF); // 모든 방향의 초기 비용을 무한대로 설정
            }
        }

        // BFS 탐색을 위한 큐 초기화
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0; // 시작 지점 비용을 0으로 설정
        }
        queue.offer(new Node(0, 0, -1, 0)); // 시작 노드: (x, y, 이전 방향, 현재 비용)

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            Node curr = queue.poll(); // 현재 노드를 큐에서 꺼냄
            int x = curr.x;
            int y = curr.y;
            int prevDir = curr.direction; // 이전 방향
            int currCost = curr.cost; // 현재까지의 비용

            // 상, 하, 좌, 우 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 새로운 x 좌표
                int ny = y + dy[i]; // 새로운 y 좌표

                // 격자 내부에 있고, 벽이 아닌 경우에만 진행
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == 0) {
                    // 새로운 비용 계산
                    int newCost = currCost + 100; // 직선 도로 비용 추가
                    if (prevDir != -1 && prevDir != i) { // 이전 방향과 다른 경우 코너 비용 추가
                        newCost += 500;
                    }

                    // 새로운 비용이 기존 비용보다 작으면 갱신
                    if (newCost < cost[nx][ny][i]) {
                        cost[nx][ny][i] = newCost; // 비용 갱신
                        queue.offer(new Node(nx, ny, i, newCost)); // 새로운 상태를 큐에 추가
                    }
                }
            }
        }

        // 도착점에서 최소 비용 계산
        int answer = INF;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, cost[n - 1][n - 1][i]); // 모든 방향 중 최소 비용 선택
        }
        return answer;
    }

    // BFS 탐색에 사용할 Node 클래스 정의
    private static class Node {
        int x, y; // 현재 좌표
        int direction; // 이전 방향 (상: 0, 하: 1, 좌: 2, 우: 3)
        int cost; // 현재까지의 비용

        Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }
}
