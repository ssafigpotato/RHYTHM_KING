package BOJ_11559_PuyoPuyo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* 
 * bfs로 연결된 것들이 4개 이상인 색의 물풍선을 터뜨리는 메서드
 * + 
 * 터진 자리로 위의 풍선들이 떨어지는 메소드
 */

public class Main {

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new char[12][6];

		char[][] copy = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String str = sc.next();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < 12; i++) {
			copy[i] = Arrays.copyOf(map[i], 6);
		}

		boolean flag = false;
		int cnt = 0;

		while (!flag) {

			cnt++;

			visited = new boolean[12][6];

			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						bfs(i, j, map[i][j]);
					}
				}
			}

			for (int i = 10; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && map[i + 1][j] == '.') {
						int num = i;
						while (num < 11 && map[num + 1][j] == '.') {
							num += 1;
						}
						map[num][j] = map[i][j];
						map[i][j] = '.';
					}
				}

			}
			if (Arrays.deepEquals(map, copy)) {
				flag = true;
			} else {
				for (int k = 0; k < 12; k++) {
					copy[k] = Arrays.copyOf(map[k], 6);
				}
			}
		}

		System.out.println(cnt - 1);
	}

	private static void bfs(int i, int j, char c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		visited[i][j] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if (check(nx, ny) && !visited[nx][ny] && map[nx][ny] == c) {
					visited[nx][ny] = true;
					cnt++;
					q.add(new int[] { nx, ny });
				}
			}
		}

		if (cnt >= 4) {
			visited = new boolean[12][6];
			Queue<int[]> que = new LinkedList<>();
			que.add(new int[] { i, j });
			visited[i][j] = true;
			map[i][j] = '.';

			while (!que.isEmpty()) {
				var curr = que.poll();
				int now_x = curr[0];
				int now_y = curr[1];
				for (int d = 0; d < 4; d++) {
					int nx = now_x + di[d];
					int ny = now_y + dj[d];
					if (check(nx, ny) && !visited[nx][ny] && map[nx][ny] == c) {
						visited[nx][ny] = true;
						que.add(new int[] { nx, ny });
						map[nx][ny] = '.';
					}
				}
			}
		}

	}

	private static boolean check(int x, int y) {
		return x >= 0 && x < 12 && y >= 0 && y < 6;
	}

}
