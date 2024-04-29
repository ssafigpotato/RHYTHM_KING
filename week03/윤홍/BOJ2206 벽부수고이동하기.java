package BOJ_2206_벽부수고이동하기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * dfs와 bfs모두 접근했지만 시간초과 때문에 해결이 되지 않는다.
 * 여러번의 과정을 반복하는 것보다 한번에 돌아서 확인하는 방법을 요구하는거 같은데
 * 벽을 만났을때 나는 이걸 깰지 말지를 정해야 한다.
 * 결국 힌트를 얻어 풀었는데 여기서 핵심은 바로 visited를 따로 돌려야하는 것이었다.
 * 벽을 한개 부셨을 때의 visited와 벽을 부수지 않았을 때의 visited를 따로 돌린다.
 * 
 */

public class Main {

	static int N, M;
	static int ans;
	static int[][] map;
	static int[][] dis;
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static boolean[][][] visited;
	static boolean flag = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		dis = new int[N][M];
		// 0일때 벽을 부순 경우 , 1일 때 벽을 부수지 않은 경우
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		dis[0][0] = 1;
		bfs(0, 0, 1);

		if(N == 1 && M == 1) {
			System.out.println(1);
		}else if(ans == 0) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dis[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	static void bfs(int x, int y, int z) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y, z });
		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			int canCrash = curr[2];

			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];

				if (!check(nx, ny)) {
					continue;
				}

				if (map[nx][ny] == 1) {
					if (!visited[nx][ny][1] && canCrash == 1) {
						visited[nx][ny][1] = true;
						dis[nx][ny] = dis[now_x][now_y] + 1;
						q.add(new int[] { nx, ny, 0 });
					}
				} else {
					if (!visited[nx][ny][0] && canCrash == 1) {
						visited[nx][ny][0] = true;
						dis[nx][ny] = dis[now_x][now_y] + 1;
						q.add(new int[] { nx, ny, canCrash });
					}else if(!visited[nx][ny][1] && canCrash == 0) {
						visited[nx][ny][1] = true;
						dis[nx][ny] = dis[now_x][now_y] + 1;
						q.add(new int[] { nx, ny, canCrash });
					}
				}

				if (nx == N - 1 && ny == M - 1) {
					ans = dis[nx][ny];
					return;
				}
			}
		}
	}

	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
