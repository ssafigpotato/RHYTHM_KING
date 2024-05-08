package BOJ_16234_인구이동;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> list;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		L = sc.nextInt();
		R = sc.nextInt();

		map = new int[N][N];
		int[][] copy = new int[N][N];

		boolean flag = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOfRange(map[i], 0, N);
		}

		int cnt = 0;
		while (!flag) {
			cnt++;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j);
					}
				}
			}

			if (Arrays.deepEquals(map, copy)) {
				flag = true;
			}

			for (int i = 0; i < N; i++) {
				copy[i] = Arrays.copyOfRange(map[i], 0, N);
			}
		}

		System.out.println(cnt - 1);

	}

	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		list = new ArrayList<>();
		visited[x][y] = true;
		list.add(new int[] { x, y, map[x][y] });
		q.add(new int[] { x, y, map[x][y] });

		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			int num = curr[2];
			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if (check(nx, ny) && !visited[nx][ny] && check_num(Math.abs(num - map[nx][ny]))) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny, map[nx][ny] });
					list.add(new int[] { nx, ny, map[nx][ny] });
				}
			}
		}

		int sum = 0;
		int num = 0;

		if (list.size() > 1) {
//			for (int[] arr : list) {
//				System.out.println(Arrays.toString(arr));
//			}
			for (int i = 0; i < list.size(); i++) {
				sum += list.get(i)[2];
			}

			num = sum / list.size();

			for (int i = 0; i < list.size(); i++) {
				map[list.get(i)[0]][list.get(i)[1]] = num;
			}

		}

	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static boolean check_num(int x) {
		return x >= L && x <= R;
	}
}
