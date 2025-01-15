package BOJ2146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int cnt = 1;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static List<int[]> edges = new ArrayList<>(); // 가장자리 좌표 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Step 1: 대륙 구분 및 가장자리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs1(i, j); // 대륙 구분
                    cnt++;
                }
            }
        }

        // Step 2: 최소 다리 길이 계산
        int minBridge = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            minBridge = Math.min(minBridge, bfs2(edge[0], edge[1]));
        }

        System.out.println(minBridge);
    }

    // 대륙 구분 및 가장자리 저장
    static void bfs1(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = cnt;

        while (!q.isEmpty()) {
            var curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            boolean isEdge = false;

            for (int i = 0; i < 4; i++) {
                int nr = r + di[i];
                int nc = c + dj[i];

                if (check(nr, nc)) {
                    if (!visited[nr][nc] && map[nr][nc] != 0) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                        map[nr][nc] = cnt;
                    } else if (map[nr][nc] == 0) {
                        isEdge = true; // 가장자리 조건
                    }
                }
            }

            if (isEdge) {
                edges.add(new int[]{r, c}); // 가장자리 좌표 저장
            }
        }
    }

    // 가장자리에서 다른 대륙까지의 최소 거리 계산
    static int bfs2(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visitedLocal = new boolean[map.length][map.length];
        q.add(new int[]{x, y, 0}); // {x, y, 거리}
        visitedLocal[x][y] = true;

        int origin = map[x][y]; // 시작 대륙 번호

        while (!q.isEmpty()) {
            var curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + di[i];
                int nc = c + dj[i];

                if (check(nr, nc) && !visitedLocal[nr][nc]) {
                    visitedLocal[nr][nc] = true;

                    if (map[nr][nc] == 0) {
                        q.add(new int[]{nr, nc, dist + 1});
                    } else if (map[nr][nc] != origin) {
                        return dist; // 다른 대륙 도달 시 거리 반환
                    }
                }
            }
        }

        return Integer.MAX_VALUE; // 도달 불가능한 경우 (이론상 발생하지 않음)
    }

    // 범위 체크
    static boolean check(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map.length;
    }
}
