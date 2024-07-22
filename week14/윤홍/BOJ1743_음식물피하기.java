package BOJ_1743;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int N, M, ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //세로 길이
        M = sc.nextInt(); //가로 길이

        int K = sc.nextInt(); //음식물 갯수

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            int A = sc.nextInt() - 1;
            int B = sc.nextInt() - 1;

            map[A][B] = 1;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    bfs(i, j);
                }
            }
        }


        System.out.println(ans);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            var curr = q.poll();
            int now_x = curr[0];
            int now_y = curr[1];

            cnt++;

            for (int d = 0; d < 4; d++) {
                int nx = now_x + di[d];
                int ny = now_y + dj[d];

                if (check(nx, ny) && !visited[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        ans = Math.max(cnt, ans);
    }

    static boolean check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && map[x][y] == 1;
    }
}
