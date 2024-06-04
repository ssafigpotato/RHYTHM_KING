package BOJ3184;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int R, C;
	static char[][] map;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static boolean[][] visited_sheep;
	static boolean[][] visited_wolf;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		map = new char[R][C];
		visited_sheep = new boolean[R][C];
		visited_wolf = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int total_sheep = 0;
		int total_wolf = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited_sheep[i][j] && !visited_wolf[i][j] && map[i][j] != '#') {
					int now_sheep = bfs_sheep(i, j);
					int now_wolf = bfs_wolf(i, j);
//					System.out.println(now_sheep + " " + now_wolf);
					if (now_sheep > now_wolf) {
						total_sheep += now_sheep;
					} else {
						total_wolf += now_wolf;
					}
				}
			}
		}

		System.out.println(total_sheep + " " + total_wolf);

	}

	static int bfs_sheep(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited_sheep[x][y] = true;
		int sheep = 0;
		if (map[x][y] == 'o') {
			sheep++;
		}

		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];

			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];

				if (check(nx, ny) && !visited_sheep[nx][ny]) {
					q.add(new int[] { nx, ny });
					visited_sheep[nx][ny] = true;
					if (map[nx][ny] == 'o') {
						sheep++;
					}
				}
			}
		}
		return sheep;
	}

	static int bfs_wolf(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited_wolf[x][y] = true;
		int wolf = 0;

		if (map[x][y] == 'v') {
			wolf++;
		}

		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];

			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];

				if (check(nx, ny) && !visited_wolf[nx][ny]) {
					q.add(new int[] { nx, ny });
					visited_wolf[nx][ny] = true;
					if (map[nx][ny] == 'v') {
						wolf++;
					}
				}
			}
		}
		return wolf;
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C && map[x][y] != '#';
	}
}
