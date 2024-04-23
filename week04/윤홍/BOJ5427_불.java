package BOJ_5427_불;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int M, N;
	static char[][] map;
	static Queue<int[]> fire;
	static Queue<int[]> safe;
	static boolean[][] visited_f;
	static boolean[][] visited_s;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] safemap;
	static int[][] firemap;
	static List<int[]> exit;
	static boolean flag;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();

			map = new char[N][M];

			fire = new LinkedList<>();
			safe = new LinkedList<>();

			firemap = new int[N][M];
			safemap = new int[N][M];

			exit = new ArrayList<>();

			visited_f = new boolean[N][M];
			visited_s = new boolean[N][M];

			flag = false;

			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '*') {
						fire.add(new int[] { i, j, 1 });
						firemap[i][j] = 1;
						visited_f[i][j] = true;
					} else if (map[i][j] == '@') {
						safe.add(new int[] { i, j, 1 });
						safemap[i][j] = 1;
						visited_s[i][j] = true;
					}
					if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
						if (map[i][j] == '.') {
							exit.add(new int[] { i, j });
						}
						if (map[i][j] == '@') {
							flag = true;
						}
					}
				}
			}

			bfs_safe();

			bfs_fire();

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < exit.size(); i++) {
				int[] curr = exit.get(i);
				int x = curr[0];
				int y = curr[1];
				if (safemap[x][y] < firemap[x][y] && safemap[x][y] != 0) {
					if (safemap[x][y] < min) {
						min = safemap[x][y];
					}
				} else if (firemap[x][y] == 0 && safemap[x][y] != 0) {
					if (safemap[x][y] < min) {
						min = safemap[x][y];
					}
				}
			}

			if (flag) {
				System.out.println(1);
			} else {
				if (min == Integer.MAX_VALUE) {
					System.out.println("IMPOSSIBLE");
				} else {
					System.out.println(min);
				}
			}
		}

	}

	public static void bfs_safe() {
		while (!safe.isEmpty()) {
			int[] curr = safe.poll();
			int x = curr[0];
			int y = curr[1];
			int run = curr[2];

			for (int i = 0; i < 4; i++) {

				int nx = x + di[i];
				int ny = y + dj[i];

				if (check_safe(nx, ny) && !visited_s[nx][ny]) {
					visited_s[nx][ny] = true;
					safemap[nx][ny] = run + 1;
					safe.add(new int[] { nx, ny, run + 1 });
				}
			}
		}
	}

	public static void bfs_fire() {
		while (!fire.isEmpty()) {
			int[] curr = fire.poll();
			int x = curr[0];
			int y = curr[1];
			int danger = curr[2];

			for (int i = 0; i < 4; i++) {
				int nx = x + di[i];
				int ny = y + dj[i];

				if (check_fire(nx, ny) && !visited_f[nx][ny]) {
					visited_f[nx][ny] = true;
					fire.add(new int[] { nx, ny, danger + 1 });
					firemap[nx][ny] = danger + 1;
				}
			}
		}
	}

	static boolean check_safe(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M && map[x][y] == '.';
	}

	static boolean check_fire(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M && map[x][y] != '#';
	}
}
